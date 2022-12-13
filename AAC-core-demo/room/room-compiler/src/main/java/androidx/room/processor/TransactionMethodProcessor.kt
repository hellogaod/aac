/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.room.processor

import androidx.room.compiler.processing.XMethodElement
import androidx.room.compiler.processing.XType
import androidx.room.compiler.processing.XTypeElement
import androidx.room.ext.DEFERRED_TYPES
import androidx.room.vo.TransactionMethod

class TransactionMethodProcessor(
    baseContext: Context,
    val containingElement: XTypeElement,
    val containingType: XType,
    val executableElement: XMethodElement
) {
    val context = baseContext.fork(executableElement)


    fun process(): TransactionMethod {
        val delegate = MethodProcessorDelegate.createFor(context, containingType, executableElement)
        val hasKotlinDefaultImpl = executableElement.hasKotlinDefaultImpl()
        // transaction方法不能使用private、final、static；如果想使用abstract修饰,那么当前transaction方法在kotlin类中必须存在默认实现
        context.checker.check(
            executableElement.isOverrideableIgnoringContainer() &&
                    (!executableElement.isAbstract() || hasKotlinDefaultImpl),
            executableElement, ProcessorErrors.TRANSACTION_METHOD_MODIFIERS
        )

        val returnType = delegate.extractReturnType()
        val rawReturnType = returnType.rawType

        //transaction方法返回类型（如果是挂起方法，判断的是最后一个参数，该参数的第一个泛型类型）不允许是
        // `androidx.lifecycle.LiveData、
        // androidx.lifecycle.ComputableLiveData、
        // io.reactivex.Flowable、
        // io.reactivex.Observable、
        // io.reactivex.Maybe、
        // io.reactivex.Single、
        // io.reactivex.Completable、
        // io.reactivex.rxjava3.core.Flowable、
        // io.reactivex.rxjava3.core.Observable、
        // io.reactivex.rxjava3.core.Maybe、
        // io.reactivex.rxjava3.core.Single、
        // io.reactivex.rxjava3.core.Completable、
        // com.google.common.util.concurrent.ListenableFuture、
        // kotlinx.coroutines.flow.Flow、
        // org.reactivestreams.Publisher`及其子类，这些表示延时或异步处理的类型；
        DEFERRED_TYPES.firstOrNull { className ->
            context.processingEnv.findType(className)?.let {
                it.rawType.isAssignableFrom(rawReturnType)
            } ?: false
        }?.let { returnTypeName ->
            context.logger.e(
                ProcessorErrors.transactionMethodAsync(returnTypeName.toString()),
                executableElement
            )
        }

        val callType = when {
            executableElement.isJavaDefault() ->
                if (containingElement.isInterface()) {
                    // if the dao is an interface, call via the Dao interface
                    TransactionMethod.CallType.DEFAULT_JAVA8
                } else {
                    // if the dao is an abstract class, call via the class itself
                    TransactionMethod.CallType.INHERITED_DEFAULT_JAVA8
                }
            hasKotlinDefaultImpl ->
                TransactionMethod.CallType.DEFAULT_KOTLIN
            else ->
                TransactionMethod.CallType.CONCRETE
        }

        return TransactionMethod(
            element = executableElement,
            returnType = returnType,
            parameterNames = delegate.extractParams().map { it.name },
            callType = callType,
            methodBinder = delegate.findTransactionMethodBinder(callType)
        )
    }
}
