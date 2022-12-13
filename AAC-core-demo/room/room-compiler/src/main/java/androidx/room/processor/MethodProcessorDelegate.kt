/*
 * Copyright 2018 The Android Open Source Project
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
import androidx.room.compiler.processing.XMethodType
import androidx.room.compiler.processing.XSuspendMethodType
import androidx.room.compiler.processing.XType
import androidx.room.compiler.processing.XVariableElement
import androidx.room.compiler.processing.isSuspendFunction
import androidx.room.ext.*
import androidx.room.parser.ParsedQuery
import androidx.room.solver.TypeAdapterExtras
import androidx.room.solver.prepared.binder.CallablePreparedQueryResultBinder.Companion.createPreparedBinder
import androidx.room.solver.prepared.binder.PreparedQueryResultBinder
import androidx.room.solver.query.result.CoroutineResultBinder
import androidx.room.solver.query.result.QueryResultBinder
import androidx.room.solver.shortcut.binder.CallableDeleteOrUpdateMethodBinder.Companion.createDeleteOrUpdateBinder
import androidx.room.solver.shortcut.binder.CallableInsertMethodBinder.Companion.createInsertBinder
import androidx.room.solver.shortcut.binder.DeleteOrUpdateMethodBinder
import androidx.room.solver.shortcut.binder.InsertMethodBinder
import androidx.room.solver.transaction.binder.CoroutineTransactionMethodBinder
import androidx.room.solver.transaction.binder.InstantTransactionMethodBinder
import androidx.room.solver.transaction.binder.TransactionMethodBinder
import androidx.room.solver.transaction.result.TransactionMethodAdapter
import androidx.room.vo.QueryParameter
import androidx.room.vo.ShortcutQueryParameter
import androidx.room.vo.TransactionMethod

/**
 *  Delegate class with common functionality for DAO method processors.
 *
 *  Dao方法处理核心：为DAO方法处理器委托具有通用功能的类。
 */
abstract class MethodProcessorDelegate(
    val context: Context,
    val containing: XType,
    val executableElement: XMethodElement
) {

    // 提取返回类型
    abstract fun extractReturnType(): XType

    // 提取参数
    abstract fun extractParams(): List<XVariableElement>

    // 提取查询参数
    fun extractQueryParams(query: ParsedQuery): List<QueryParameter> {
        //（1）非挂起方法使用当前方法参数
        //(2) 挂起方法使用除了参数是kotlin.coroutines.Continuation类型以外的其他类型
        return extractParams().map { variableElement ->
            QueryParameterProcessor(
                baseContext = context,
                containing = containing,
                element = variableElement,
                sqlName = variableElement.name,
                bindVarSection = query.bindSections.firstOrNull {
                    it.varName == variableElement.name
                }
            ).process()
        }
    }

    //查询结果，solver.query.result包下
    abstract fun findResultBinder(
        returnType: XType,
        query: ParsedQuery,
        extrasCreator: TypeAdapterExtras.() -> Unit
    ): QueryResultBinder

    //查询结果前提，solver.prepared.binder包下
    abstract fun findPreparedResultBinder(
        returnType: XType,
        query: ParsedQuery
    ): PreparedQueryResultBinder

    //插入方法，solver.shortcut.binder包下
    abstract fun findInsertMethodBinder(
        returnType: XType,
        params: List<ShortcutQueryParameter>
    ): InsertMethodBinder

    //删除或更新方法，solver.shortcut.binder包下
    abstract fun findDeleteOrUpdateMethodBinder(returnType: XType): DeleteOrUpdateMethodBinder

    //事务方法，solver.transaction.binder包下
    abstract fun findTransactionMethodBinder(
        callType: TransactionMethod.CallType
    ): TransactionMethodBinder

    companion object {
        fun createFor(
            context: Context,
            containing: XType,
            executableElement: XMethodElement
        ): MethodProcessorDelegate {
            val asMember = executableElement.asMemberOf(containing)
            //如果方法是suspend挂起方法，那么必须使用到room-ktx依赖（该依赖中包含CoroutinesRoom类）
            return if (asMember.isSuspendFunction()) {
                val hasCoroutineArtifact = context.processingEnv
                    .findTypeElement(RoomCoroutinesTypeNames.COROUTINES_ROOM.toString()) != null
                if (!hasCoroutineArtifact) {
                    context.logger.e(ProcessorErrors.MISSING_ROOM_COROUTINE_ARTIFACT)
                }
                SuspendMethodProcessorDelegate(
                    context,
                    containing,
                    executableElement,
                    asMember
                )
            } else {
                DefaultMethodProcessorDelegate(
                    context,
                    containing,
                    executableElement,
                    asMember
                )
            }
        }
    }
}

// 如果是挂起方法并且是返回延时类型
// 非挂起方法直接返回false；如果是挂起方法最后一个参数，该参数的第一个泛型类型；如果该泛型类型是DEFERRED_TYPES中的一种，返回true，否则false。
fun MethodProcessorDelegate.isSuspendAndReturnsDeferredType(): Boolean {
    if (!executableElement.isSuspendFunction()) {
        return false
    }

    //筛选出DEFERRED_TYPES集合中存在于当前项目中的类
    // androidx.lifecycle.LiveData
    // androidx.lifecycle.ComputableLiveData
    // io.reactivex.Flowable
    // io.reactivex.Observable
    // io.reactivex.Maybe
    // io.reactivex.Single
    // io.reactivex.Completable
    // io.reactivex.rxjava3.core.Flowable
    // io.reactivex.rxjava3.core.Observable
    // io.reactivex.rxjava3.core.Maybe
    // io.reactivex.rxjava3.core.Single
    // io.reactivex.rxjava3.core.Completable
    // com.google.common.util.concurrent.ListenableFuture
    // kotlinx.coroutines.flow.Flow
    // org.reactivestreams.Publisher
    val deferredTypes = DEFERRED_TYPES.mapNotNull { context.processingEnv.findType(it) }

    val returnType = extractReturnType()
    val hasDeferredReturnType = deferredTypes.any { deferredType ->
        deferredType.rawType.isAssignableFrom(returnType.rawType)
    }

    return hasDeferredReturnType
}

/**
 * Default delegate for DAO methods.
 */
class DefaultMethodProcessorDelegate(
    context: Context,
    containing: XType,
    executableElement: XMethodElement,
    val executableType: XMethodType
) : MethodProcessorDelegate(context, containing, executableElement) {

    override fun extractReturnType(): XType {
        return executableType.returnType
    }

    override fun extractParams() = executableElement.parameters

    //dao方法返回类型支持：在query.result包下适配
    //1.android.database.Cursor
    //2.androidx.lifecycle.LiveData
    //3.com.google.common.util.concurrent.ListenableFuture
    //4.Rxjava2和Rxjava3的Flowable和Observable两个类适配
    //5.Rxjava2和Rxjava3的Single和Mybe两个类适配
    //6.androidx.paging.PositionalDataSource
    //7.androidx.paging.DataSource.Factory并且两个泛型参数
    //8.androidx.paging.PagingSource
    //9.kotlinx.coroutines.flow.Flow
    //10.任意类型
    override fun findResultBinder(
        returnType: XType,
        query: ParsedQuery,
        extrasCreator: TypeAdapterExtras.() -> Unit
    ) = context.typeAdapterStore.findQueryResultBinder(returnType, query, extrasCreator)

    //Rxjava2,single类、maybe类、completable类；Rxjava3，single类、maybe类、completable类；
    //com.google.common.util.concurrent.ListenableFuture
    //随意类型
    override fun findPreparedResultBinder(
        returnType: XType,
        query: ParsedQuery
    ) = context.typeAdapterStore.findPreparedQueryResultBinder(returnType, query)

    //1. RxCallableInsertMethodBinderProvider中匹配
    //返回类型匹配：
    // （1）io.reactivex.Maybe
    // （2）io.reactivex.Single
    // （3）io.reactivex.Completable
    // （4）io.reactivex.rxjava3.core.Maybe
    // （5）io.reactivex.rxjava3.core.Single
    // （6）io.reactivex.rxjava3.core.Completable

    //2. GuavaListenableFutureInsertMethodBinderProvider中匹配
    //返回类型匹配：com.google.common.util.concurrent.ListenableFuture,并且泛型参数类型有且仅有一个

    //3. InstantInsertMethodBinderProvider中匹配
    override fun findInsertMethodBinder(
        returnType: XType,
        params: List<ShortcutQueryParameter>
    ) = context.typeAdapterStore.findInsertMethodBinder(returnType, params)

    //1. RxCallableDeleteOrUpdateMethodBinderProvider
    // （1）io.reactivex.Maybe
    // （2）io.reactivex.Single
    // （3）io.reactivex.Completable
    // （4）io.reactivex.rxjava3.core.Maybe
    // （5）io.reactivex.rxjava3.core.Single
    // （6）io.reactivex.rxjava3.core.Completable

    //2. GuavaListenableFutureDeleteOrUpdateMethodBinderProvider
    //com.google.common.util.concurrent.ListenableFutur

    //3. InstantDeleteOrUpdateMethodBinderProvider
    override fun findDeleteOrUpdateMethodBinder(returnType: XType) =
        context.typeAdapterStore.findDeleteOrUpdateMethodBinder(returnType)

    // InstantTransactionMethodBinder对象
    override fun findTransactionMethodBinder(callType: TransactionMethod.CallType) =
        InstantTransactionMethodBinder(
            //TransactionMethodAdapter对象
            TransactionMethodAdapter(executableElement.jvmName, callType)
        )
}

/**
 * Delegate for DAO methods that are a suspend function.
 */
class SuspendMethodProcessorDelegate(
    context: Context,
    containing: XType,
    executableElement: XMethodElement,
    val executableType: XSuspendMethodType
) : MethodProcessorDelegate(context, containing, executableElement) {

    //Continuation类型
    private val continuationParam: XVariableElement by lazy {
        val continuationType = context.processingEnv
            .requireType(KotlinTypeNames.CONTINUATION.toString()).rawType
        executableElement.parameters.last {
            it.type.rawType == continuationType
        }
    }

    override fun extractReturnType(): XType {
        //实际请查看SuspendMethodType类的getSuspendFunctionReturnType方法：suspend方法最后一个参数，该参数的第一个泛型类型
        return executableType.getSuspendFunctionReturnType()
    }

    //排除Continuation类型
    override fun extractParams() =
        executableElement.parameters.filterNot {
            it == continuationParam
        }

    //Continuation类型,
    override fun findResultBinder(
        returnType: XType,
        query: ParsedQuery,
        extrasCreator: TypeAdapterExtras.() -> Unit
    ) =
        CoroutineResultBinder(
            typeArg = returnType,
            adapter =
            //solver.query.result包下匹配
            context.typeAdapterStore.findQueryResultAdapter(returnType, query, extrasCreator),
            continuationParamName = continuationParam.name
        )

    //返回CallablePreparedQueryResultBinder对象
    override fun findPreparedResultBinder(
        returnType: XType,
        query: ParsedQuery
    ) = createPreparedBinder(
        returnType = returnType,
        //返回PreparedQueryResultAdapter对象
        adapter = context.typeAdapterStore.findPreparedQueryResultAdapter(returnType, query)
    ) { callableImpl, dbField ->
        addStatement(
            "return $T.execute($N, $L, $L, $N)",
            RoomCoroutinesTypeNames.COROUTINES_ROOM,
            dbField,
            "true", // inTransaction
            callableImpl,
            continuationParam.name
        )
    }

    //返回CallableInsertMethodBinder对象
    override fun findInsertMethodBinder(
        returnType: XType,
        params: List<ShortcutQueryParameter>
    ) = createInsertBinder(
        typeArg = returnType,
        //返回InsertMethodAdapter对象
        adapter = context.typeAdapterStore.findInsertAdapter(returnType, params)
    ) { callableImpl, dbField ->
        addStatement(
            "return $T.execute($N, $L, $L, $N)",
            RoomCoroutinesTypeNames.COROUTINES_ROOM,
            dbField,
            "true", // inTransaction
            callableImpl,
            continuationParam.name
        )
    }

    // CallableDeleteOrUpdateMethodBinder对象
    override fun findDeleteOrUpdateMethodBinder(returnType: XType) =
        createDeleteOrUpdateBinder(
            typeArg = returnType,
            //DeleteOrUpdateMethodAdapter对象
            adapter = context.typeAdapterStore.findDeleteOrUpdateAdapter(returnType)
        ) { callableImpl, dbField ->
            addStatement(
                "return $T.execute($N, $L, $L, $N)",
                RoomCoroutinesTypeNames.COROUTINES_ROOM,
                dbField,
                "true", // inTransaction
                callableImpl,
                continuationParam.name
            )
        }

    //CoroutineTransactionMethodBinder对象
    override fun findTransactionMethodBinder(callType: TransactionMethod.CallType) =
        CoroutineTransactionMethodBinder(
            //TransactionMethodAdapter对象
            adapter = TransactionMethodAdapter(executableElement.jvmName, callType),
            continuationParamName = continuationParam.name,
            useLambdaSyntax = context.processingEnv.jvmVersion >= 8
        )
}