/*
 * Copyright (C) 2016 The Android Open Source Project
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

package androidx.room.solver

import androidx.room.compiler.processing.XType
import androidx.room.compiler.processing.isArray
import androidx.room.compiler.processing.isEnum
import androidx.room.ext.CollectionTypeNames.ARRAY_MAP
import androidx.room.ext.CollectionTypeNames.INT_SPARSE_ARRAY
import androidx.room.ext.CollectionTypeNames.LONG_SPARSE_ARRAY
import androidx.room.ext.CommonTypeNames
import androidx.room.ext.GuavaBaseTypeNames
import androidx.room.ext.isEntityElement
import androidx.room.ext.isNotByte
import androidx.room.ext.isNotKotlinUnit
import androidx.room.ext.isNotVoid
import androidx.room.ext.isNotVoidObject
import androidx.room.ext.isUUID
import androidx.room.parser.ParsedQuery
import androidx.room.parser.SQLTypeAffinity
import androidx.room.processor.Context
import androidx.room.processor.EntityProcessor
import androidx.room.processor.FieldProcessor
import androidx.room.processor.PojoProcessor
import androidx.room.processor.ProcessorErrors.DO_NOT_USE_GENERIC_IMMUTABLE_MULTIMAP
import androidx.room.processor.ProcessorErrors.valueCollectionMustBeListOrSet
import androidx.room.solver.binderprovider.CoroutineFlowResultBinderProvider
import androidx.room.solver.binderprovider.CursorQueryResultBinderProvider
import androidx.room.solver.binderprovider.DataSourceFactoryQueryResultBinderProvider
import androidx.room.solver.binderprovider.DataSourceQueryResultBinderProvider
import androidx.room.solver.binderprovider.GuavaListenableFutureQueryResultBinderProvider
import androidx.room.solver.binderprovider.InstantQueryResultBinderProvider
import androidx.room.solver.binderprovider.LiveDataQueryResultBinderProvider
import androidx.room.solver.binderprovider.PagingSourceQueryResultBinderProvider
import androidx.room.solver.binderprovider.RxCallableQueryResultBinderProvider
import androidx.room.solver.binderprovider.RxQueryResultBinderProvider
import androidx.room.solver.prepared.binder.PreparedQueryResultBinder
import androidx.room.solver.prepared.binderprovider.GuavaListenableFuturePreparedQueryResultBinderProvider
import androidx.room.solver.prepared.binderprovider.InstantPreparedQueryResultBinderProvider
import androidx.room.solver.prepared.binderprovider.PreparedQueryResultBinderProvider
import androidx.room.solver.prepared.binderprovider.RxPreparedQueryResultBinderProvider
import androidx.room.solver.prepared.result.PreparedQueryResultAdapter
import androidx.room.solver.query.parameter.ArrayQueryParameterAdapter
import androidx.room.solver.query.parameter.BasicQueryParameterAdapter
import androidx.room.solver.query.parameter.CollectionQueryParameterAdapter
import androidx.room.solver.query.parameter.QueryParameterAdapter
import androidx.room.solver.query.result.ArrayQueryResultAdapter
import androidx.room.solver.query.result.EntityRowAdapter
import androidx.room.solver.query.result.GuavaImmutableMultimapQueryResultAdapter
import androidx.room.solver.query.result.GuavaOptionalQueryResultAdapter
import androidx.room.solver.query.result.ImmutableListQueryResultAdapter
import androidx.room.solver.query.result.ImmutableMapQueryResultAdapter
import androidx.room.solver.query.result.ListQueryResultAdapter
import androidx.room.solver.query.result.MapQueryResultAdapter
import androidx.room.solver.query.result.MultimapQueryResultAdapter.Companion.validateMapTypeArgs
import androidx.room.solver.query.result.OptionalQueryResultAdapter
import androidx.room.solver.query.result.PojoRowAdapter
import androidx.room.solver.query.result.QueryResultAdapter
import androidx.room.solver.query.result.QueryResultBinder
import androidx.room.solver.query.result.RowAdapter
import androidx.room.solver.query.result.SingleColumnRowAdapter
import androidx.room.solver.query.result.SingleEntityQueryResultAdapter
import androidx.room.solver.query.result.SingleNamedColumnRowAdapter
import androidx.room.solver.shortcut.binder.DeleteOrUpdateMethodBinder
import androidx.room.solver.shortcut.binder.InsertMethodBinder
import androidx.room.solver.shortcut.binderprovider.DeleteOrUpdateMethodBinderProvider
import androidx.room.solver.shortcut.binderprovider.GuavaListenableFutureDeleteOrUpdateMethodBinderProvider
import androidx.room.solver.shortcut.binderprovider.GuavaListenableFutureInsertMethodBinderProvider
import androidx.room.solver.shortcut.binderprovider.InsertMethodBinderProvider
import androidx.room.solver.shortcut.binderprovider.InstantDeleteOrUpdateMethodBinderProvider
import androidx.room.solver.shortcut.binderprovider.InstantInsertMethodBinderProvider
import androidx.room.solver.shortcut.binderprovider.RxCallableDeleteOrUpdateMethodBinderProvider
import androidx.room.solver.shortcut.binderprovider.RxCallableInsertMethodBinderProvider
import androidx.room.solver.shortcut.result.DeleteOrUpdateMethodAdapter
import androidx.room.solver.shortcut.result.InsertMethodAdapter
import androidx.room.solver.types.BoxedBooleanToBoxedIntConverter
import androidx.room.solver.types.BoxedPrimitiveColumnTypeAdapter
import androidx.room.solver.types.ByteArrayColumnTypeAdapter
import androidx.room.solver.types.ByteBufferColumnTypeAdapter
import androidx.room.solver.types.ColumnTypeAdapter
import androidx.room.solver.types.CompositeAdapter
import androidx.room.solver.types.CursorValueReader
import androidx.room.solver.types.EnumColumnTypeAdapter
import androidx.room.solver.types.PrimitiveBooleanToIntConverter
import androidx.room.solver.types.PrimitiveColumnTypeAdapter
import androidx.room.solver.types.StatementValueBinder
import androidx.room.solver.types.StringColumnTypeAdapter
import androidx.room.solver.types.TypeConverter
import androidx.room.solver.types.UuidColumnTypeAdapter
import androidx.room.vo.BuiltInConverterFlags
import androidx.room.vo.MapInfo
import androidx.room.vo.ShortcutQueryParameter
import androidx.room.vo.isEnabled
import com.google.common.annotations.VisibleForTesting
import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableListMultimap
import com.google.common.collect.ImmutableMap
import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.ImmutableSetMultimap
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeName
import kotlin.contracts.ExperimentalContracts

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
/**
 * Holds all type adapters and can create on demand composite type adapters to convert a type into a
 * database column.
 *
 * 包含所有类型适配器，并且可以按需创建复合类型适配器以将类型转换为数据库列。
 */
class TypeAdapterStore private constructor(
    val context: Context,
    /**
     * first type adapter has the highest priority
     */
    private val columnTypeAdapters: List<ColumnTypeAdapter>,
    @VisibleForTesting
    internal val typeConverterStore: TypeConverterStore,
    private val builtInConverterFlags: BuiltInConverterFlags
) {

    companion object {
        fun copy(context: Context, store: TypeAdapterStore): TypeAdapterStore {
            return TypeAdapterStore(
                context = context,
                columnTypeAdapters = store.columnTypeAdapters,
                typeConverterStore = store.typeConverterStore,
                builtInConverterFlags = store.builtInConverterFlags
            )
        }

        fun create(
            context: Context,
            builtInConverterFlags: BuiltInConverterFlags,
            vararg extras: Any//这个值表示所有的自定义类型转换
        ): TypeAdapterStore {
            val adapters = arrayListOf<ColumnTypeAdapter>()
            val converters = arrayListOf<TypeConverter>()

            fun addAny(extra: Any?) {
                when (extra) {
                    is TypeConverter -> converters.add(extra)
                    is ColumnTypeAdapter -> adapters.add(extra)
                    is List<*> -> extra.forEach(::addAny)
                    else -> throw IllegalArgumentException("unknown extra $extra")
                }
            }
            extras.forEach(::addAny)

            fun addTypeConverter(converter: TypeConverter) {
                converters.add(converter)
            }

            fun addColumnAdapter(adapter: ColumnTypeAdapter) {
                adapters.add(adapter)
            }

            //7种基本类型转换成PrimitiveColumnTypeAdapter对象
            val primitives = PrimitiveColumnTypeAdapter
                .createPrimitiveAdapters(context.processingEnv)
            primitives.forEach(::addColumnAdapter)

            //7种基本类型转换成包装类
            BoxedPrimitiveColumnTypeAdapter
                .createBoxedPrimitiveAdapters(primitives)
                .forEach(::addColumnAdapter)

            //String类型字段适配
            StringColumnTypeAdapter.create(context.processingEnv).forEach(::addColumnAdapter)
            //byte数组类型字段适配
            ByteArrayColumnTypeAdapter.create(context.processingEnv).forEach(::addColumnAdapter)
            //ByteBuffer类型字段适配
            ByteBufferColumnTypeAdapter.create(context.processingEnv).forEach(::addColumnAdapter)


            //boolean转换成int转换器
            PrimitiveBooleanToIntConverter.create(context.processingEnv).forEach(::addTypeConverter)
            // null aware converter is able to automatically null wrap converters so we don't
            // need this as long as we are running in KSP
            //Boolean转换成Integer转换器
            BoxedBooleanToBoxedIntConverter.create(context.processingEnv)
                .forEach(::addTypeConverter)

            //type类型存储
            return TypeAdapterStore(
                context = context,
                columnTypeAdapters = adapters,
                //type类型转换存储
                typeConverterStore = TypeConverterStore.create(
                    context = context,
                    typeConverters = converters,
                    knownColumnTypes = adapters.map { it.out }
                ),
                builtInConverterFlags = builtInConverterFlags
            )
        }
    }

    //dao方法返回结果适配
    val queryResultBinderProviders: List<QueryResultBinderProvider> =
        mutableListOf<QueryResultBinderProvider>().apply {
            add(CursorQueryResultBinderProvider(context))//android.database.Cursor
            add(LiveDataQueryResultBinderProvider(context))//androidx.lifecycle.LiveData
            //com.google.common.util.concurrent.ListenableFuture
            add(GuavaListenableFutureQueryResultBinderProvider(context))
            //Rxjava2和Rxjava3的Flowable和Observable两个类适配
            addAll(RxQueryResultBinderProvider.getAll(context))
            //Rxjava2和Rxjava3的Single和Mybe两个类适配
            addAll(RxCallableQueryResultBinderProvider.getAll(context))
            //androidx.paging.PositionalDataSource
            add(DataSourceQueryResultBinderProvider(context))
            //androidx.paging.DataSource.Factory并且两个泛型参数
            add(DataSourceFactoryQueryResultBinderProvider(context))
            //androidx.paging.PagingSource
            add(PagingSourceQueryResultBinderProvider(context))
            //kotlinx.coroutines.flow.Flow
            add(CoroutineFlowResultBinderProvider(context))
            //其他任意类型都可
            add(InstantQueryResultBinderProvider(context))
        }

    //insert、update和delete
    val preparedQueryResultBinderProviders: List<PreparedQueryResultBinderProvider> =
        mutableListOf<PreparedQueryResultBinderProvider>().apply {
            //Rxjava2,single类、maybe类、completable类；Rxjava3，single类、maybe类、completable类；
            addAll(RxPreparedQueryResultBinderProvider.getAll(context))
            //com.google.common.util.concurrent.ListenableFuture
            add(GuavaListenableFuturePreparedQueryResultBinderProvider(context))
            //随意类型
            add(InstantPreparedQueryResultBinderProvider(context))
        }

    val insertBinderProviders: List<InsertMethodBinderProvider> =
        mutableListOf<InsertMethodBinderProvider>().apply {
            addAll(RxCallableInsertMethodBinderProvider.getAll(context))
            add(GuavaListenableFutureInsertMethodBinderProvider(context))
            add(InstantInsertMethodBinderProvider(context))
        }

    val deleteOrUpdateBinderProvider: List<DeleteOrUpdateMethodBinderProvider> =
        mutableListOf<DeleteOrUpdateMethodBinderProvider>().apply {
            addAll(RxCallableDeleteOrUpdateMethodBinderProvider.getAll(context))
            add(GuavaListenableFutureDeleteOrUpdateMethodBinderProvider(context))
            add(InstantDeleteOrUpdateMethodBinderProvider(context))
        }

    /**
     * Searches 1 way to bind a value into a statement.
     *
     * 搜索1种将值绑定到语句中的方法。
     *
     * （1）将input转换成表字段支持的类型，
     * （2）如果input类型不在表字段支持的类型中，再去自定会转换器转换成表字段支持类型；
     * （3）如果还不支持，那么input是枚举或者uuid类型；
     */
    fun findStatementValueBinder(
        input: XType,
        affinity: SQLTypeAffinity?
    ): StatementValueBinder? {
        if (input.isError()) {
            return null
        }
        //1. 使用的是当前表字段支持的类型，不包含自定义转化类型
        val adapter = findDirectAdapterFor(input, affinity)
        if (adapter != null) {
            return adapter
        }

        fun findTypeConverterAdapter(): ColumnTypeAdapter? {
            val targetTypes = affinity?.getTypeMirrors(context.processingEnv)
            val binder = typeConverterStore.findConverterIntoStatement(
                input = input,
                columnTypes = targetTypes
            ) ?: return null
            // columnAdapter should not be null but we are receiving errors on crash in `first()` so
            // this safeguard allows us to dispatch the real problem to the user (e.g. why we couldn't
            // find the right adapter)
            val columnAdapter = getAllColumnAdapters(binder.to).firstOrNull() ?: return null
            return CompositeAdapter(input, columnAdapter, binder, null)
        }

        //2. @TypeConverters自定义的转换类型，根据to类型最终转换成表字段支持的类型
        val adapterByTypeConverter = findTypeConverterAdapter()
        if (adapterByTypeConverter != null) {
            return adapterByTypeConverter
        }

        //3. 一般情况下，如果类型是枚举或uuid类型。
        val defaultAdapter = createDefaultTypeAdapter(input)
        if (defaultAdapter != null) {
            return defaultAdapter
        }
        return null
    }

    /**
     * Searches 1 way to read it from cursor
     *
     * 根据给定类型，确定游标值读取
     *
     * 1. 给定类型是表字段支持类型，或能够通过转换器to方法转换成表字段支持类型，或是uuid或枚举类型；返回游标值读取器；
     * 2. 给定类型，通过转换器from方法转换成表字段支持类型，返回游标值读取器；
     * 3. 给定类型，是uuid或枚举类型；
     */
    fun findCursorValueReader(output: XType, affinity: SQLTypeAffinity?): CursorValueReader? {
        if (output.isError()) {
            return null
        }
        val adapter = findColumnTypeAdapter(output, affinity, skipDefaultConverter = true)
        if (adapter != null) {
            // two way is better
            return adapter
        }

        fun findTypeConverterAdapter(): ColumnTypeAdapter? {
            val targetTypes = affinity?.getTypeMirrors(context.processingEnv)
            val converter = typeConverterStore.findConverterFromCursor(
                columnTypes = targetTypes,
                output = output
            ) ?: return null
            return CompositeAdapter(
                output,
                getAllColumnAdapters(converter.from).first(), null, converter
            )
        }

        // we could not find a two way version, search for anything
        val typeConverterAdapter = findTypeConverterAdapter()
        if (typeConverterAdapter != null) {
            return typeConverterAdapter
        }

        val defaultAdapter = createDefaultTypeAdapter(output)
        if (defaultAdapter != null) {
            return defaultAdapter
        }

        return null
    }

    /**
     * Finds a two way converter, if you need 1 way, use findStatementValueBinder or
     * findCursorValueReader.
     *
     * 表常规字段类型适配
     */
    fun findColumnTypeAdapter(
        out: XType,
        affinity: SQLTypeAffinity?,
        skipDefaultConverter: Boolean
    ): ColumnTypeAdapter? {
        if (out.isError()) {
            return null
        }
        //如果当前字段类型是否存是表字段类型，如果存在直接返回
        val adapter = findDirectAdapterFor(out, affinity)
        if (adapter != null) {
            return adapter
        }

        fun findTypeConverterAdapter(): ColumnTypeAdapter? {
            val targetTypes = affinity?.getTypeMirrors(context.processingEnv)
            val intoStatement = typeConverterStore.findConverterIntoStatement(
                input = out,
                columnTypes = targetTypes
            ) ?: return null
            // ok found a converter, try the reverse now
            //类型转换方法一定是成对出现的：一个表示转入，一个表示转出
            val fromCursor = typeConverterStore.reverse(intoStatement)
                ?: typeConverterStore.findTypeConverter(intoStatement.to, out) ?: return null
            return CompositeAdapter(
                out, getAllColumnAdapters(intoStatement.to).first(), intoStatement, fromCursor
            )
        }
        //当前字段在自定义转换器中能转换成表字段支持类型，返回，不存在继续往下查找
        val adapterByTypeConverter = findTypeConverterAdapter()
        if (adapterByTypeConverter != null) {
            return adapterByTypeConverter
        }

        //特殊用法：
        //在判断当前字段类型是否是枚举或UUID类型
        if (!skipDefaultConverter) {
            val defaultAdapter = createDefaultTypeAdapter(out)
            if (defaultAdapter != null) {
                return defaultAdapter
            }
        }
        return null
    }

    @OptIn(ExperimentalContracts::class)
    private fun createDefaultTypeAdapter(type: XType): ColumnTypeAdapter? {
        val typeElement = type.typeElement
        return when {
            builtInConverterFlags.enums.isEnabled() &&
                    typeElement?.isEnum() == true -> EnumColumnTypeAdapter(typeElement)
            builtInConverterFlags.uuid.isEnabled() &&
                    type.isUUID() -> UuidColumnTypeAdapter(type)
            else -> null
        }
    }

    private fun findDirectAdapterFor(
        out: XType,
        affinity: SQLTypeAffinity?
    ): ColumnTypeAdapter? {
        return getAllColumnAdapters(out).firstOrNull {
            affinity == null || it.typeAffinity == affinity
        }
    }

    fun findDeleteOrUpdateMethodBinder(typeMirror: XType): DeleteOrUpdateMethodBinder {
        return deleteOrUpdateBinderProvider.first {
            it.matches(typeMirror)
        }.provide(typeMirror)
    }

    fun findInsertMethodBinder(
        typeMirror: XType,
        params: List<ShortcutQueryParameter>
    ): InsertMethodBinder {
        return insertBinderProviders.first {
            it.matches(typeMirror)
        }.provide(typeMirror, params)
    }

    fun findQueryResultBinder(
        typeMirror: XType,
        query: ParsedQuery,
        extrasCreator: TypeAdapterExtras.() -> Unit = { }
    ): QueryResultBinder {
        return findQueryResultBinder(typeMirror, query, TypeAdapterExtras().apply(extrasCreator))
    }

    fun findQueryResultBinder(
        typeMirror: XType,
        query: ParsedQuery,
        extras: TypeAdapterExtras
    ): QueryResultBinder {
        return queryResultBinderProviders.first {
            it.matches(typeMirror)
        }.provide(typeMirror, query, extras)
    }

    fun findPreparedQueryResultBinder(
        typeMirror: XType,
        query: ParsedQuery
    ): PreparedQueryResultBinder {
        return preparedQueryResultBinderProviders.first {
            it.matches(typeMirror)
        }.provide(typeMirror, query)
    }

    fun findPreparedQueryResultAdapter(typeMirror: XType, query: ParsedQuery) =
        PreparedQueryResultAdapter.create(typeMirror, query.type)

    fun findDeleteOrUpdateAdapter(typeMirror: XType): DeleteOrUpdateMethodAdapter? {
        return DeleteOrUpdateMethodAdapter.create(typeMirror)
    }

    fun findInsertAdapter(
        typeMirror: XType,
        params: List<ShortcutQueryParameter>
    ): InsertMethodAdapter? {
        return InsertMethodAdapter.create(typeMirror, params)
    }

    //solver.query.result包下
    fun findQueryResultAdapter(
        typeMirror: XType,
        query: ParsedQuery,
        extrasCreator: TypeAdapterExtras.() -> Unit = { }
    ): QueryResultAdapter? {
        return findQueryResultAdapter(typeMirror, query, TypeAdapterExtras().apply(extrasCreator))
    }

    /**
     * 根据typeMirror类型，做查询结果适配
     *
     * 1. 如果typeMirror类型错误，直接返回null；否则继续；
     *
     * 2. 如果typeMirror是非byte数组类型，先执行findRowAdapter，传递数组item类型和query，返回ArrayQueryResultAdapter(rowAdapter)；否则继续；
     *
     * 3. 如果typeMirror不存在泛型类型，先执行findRowAdapter，传递typeMirror和query，返回SingleEntityQueryResultAdapter；否则继续；
     *
     * 4. 如果typeMirror是com.google.common.base.Optional，先执行findRowAdapter，传递typeMirror泛型参数类型和query，返回
     *      GuavaOptionalQueryResultAdapter( typeArg = typeArg,  resultAdapter = SingleEntityQueryResultAdapter(rowAdapter) )
     *
     * 5. 如果typeMirror是java.util.Optional，，先执行findRowAdapter，传递typeMirror泛型参数类型和query，返回
     *      OptionalQueryResultAdapter(typeArg = typeArg,resultAdapter = SingleEntityQueryResultAdapter(rowAdapter))
     *
     * 6. 如果typeMirror是ImmutableList,先执行findRowAdapter，传递typeMirror泛型参数类型和query，返回
     *      ImmutableListQueryResultAdapter(typeArg = typeArg,rowAdapter = rowAdapter)
     *
     * 7.如果typeMirror是List,先执行findRowAdapter，传递typeMirror泛型参数类型和query，返回
     *      ListQueryResultAdapter(typeArg = typeArg,rowAdapter = rowAdapter)
     * 8.如果typeMirror是ImmutableMap<k,v>,转换成Map<k,v>,对当前Map<k,v>执行当前findQueryResultAdapter方法，MapType作为typeMirror类型查找QueryResultAdapter对象，并且返回
     *             ImmutableMapQueryResultAdapter(
     *               keyTypeArg = keyTypeArg,
     *               valueTypeArg = valueTypeArg,
     *              resultAdapter = resultAdapter
     *           )
     *
     * 9.如果typeMirror是ImmutableSetMultimap，ImmutableListMultimap，<k,v>中的V必须是一个类；分别对k和v配合query执行findRowAdapter方法;返回
     *          GuavaImmutableMultimapQueryResultAdapter(
     *                keyTypeArg = keyTypeArg,
     *                valueTypeArg = valueTypeArg,
     *                keyRowAdapter = keyRowAdapter,
     *                valueRowAdapter = valueRowAdapter,
     *                immutableClassName = immutableClassName
     *            )
     *当前校验@MapInfo：如果没有使用@MapInfo，或者说@MapInfo#keyColumn为空，
     * 那么k必须是表字段支持类型或者是自定义转换类型转换成表字段支持类型；v同理；
     *rawQuery方法返回类型不允许使用 ImmutableMultimap

     * 10. Map、androidx.collection.ArrayMap、androidx.collection.LongSparseArray、androidx.collection.SparseArrayCompat
     *如果typeMirror是Map、androidx.collection.ArrayMap、androidx.collection.LongSparseArray、androidx.collection.SparseArrayCompat类型，那么
     *① 如果是androidx.collection.LongSparseArray将long作为k、androidx.collection.SparseArrayCompat将int作为k；泛型参数作为v；
     *② <k,v>,v必须是一个类；如果v是一个集合，那么只能是Set或List，并且对item类型作为v校验，
     *③ 对k和v作为参数和query一起执行findRowAdapter方法，返回
     * 那么k必须是表字段支持类型或者是自定义转换类型转换成表字段支持类型；v同理；
     *                  MapQueryResultAdapter(
     *                        keyTypeArg = keyTypeArg,
     *                        valueTypeArg = valueTypeArg,
     *                        keyRowAdapter = keyRowAdapter,
     *                        valueRowAdapter = valueRowAdapter,
     *                        valueCollectionType = mapValueTypeArg,如果v不是list或set集合，当前属性为null；
     *                        isArrayMap = typeMirror.rawType.typeName == ARRAY_MAP,
     *                        isSparseArray = isSparseArray
     *                    )
     *当前校验@MapInfo：如果没有使用@MapInfo，或者说@MapInfo#keyColumn为空，
     * 那么k必须是表字段支持类型或者是自定义转换类型转换成表字段支持类型；v同理；
     */
    fun findQueryResultAdapter(
        typeMirror: XType,
        query: ParsedQuery,
        extras: TypeAdapterExtras
    ): QueryResultAdapter? {

        if (typeMirror.isError()) {
            return null
        }

        // TODO: (b/192068912) Refactor the following since this if-else cascade has gotten large
        //非byte类型数组
        if (typeMirror.isArray() && typeMirror.componentType.isNotByte()) {
            val rowAdapter =
                findRowAdapter(typeMirror.componentType, query) ?: return null
            return ArrayQueryResultAdapter(rowAdapter)
        }

        //没有泛型类型
        else if (typeMirror.typeArguments.isEmpty()) {
            val rowAdapter = findRowAdapter(typeMirror, query) ?: return null
            return SingleEntityQueryResultAdapter(rowAdapter)
        }

        //com.google.common.base.Optional
        else if (typeMirror.rawType.typeName == GuavaBaseTypeNames.OPTIONAL) {
            // Handle Guava Optional by unpacking its generic type argument and adapting that.
            // The Optional adapter will reappend the Optional type.
            val typeArg = typeMirror.typeArguments.first()
            // use nullable when finding row adapter as non-null adapters might return
            // default values
            //Optinal<T>的T处理
            val rowAdapter = findRowAdapter(typeArg.makeNullable(), query) ?: return null

            return GuavaOptionalQueryResultAdapter(
                typeArg = typeArg,
                resultAdapter = SingleEntityQueryResultAdapter(rowAdapter)
            )
        }
        //java.util.Optional
        else if (typeMirror.rawType.typeName == CommonTypeNames.OPTIONAL) {
            // Handle java.util.Optional similarly.
            val typeArg = typeMirror.typeArguments.first()
            // use nullable when finding row adapter as non-null adapters might return
            // default values
            val rowAdapter = findRowAdapter(typeArg.makeNullable(), query) ?: return null
            return OptionalQueryResultAdapter(
                typeArg = typeArg,
                resultAdapter = SingleEntityQueryResultAdapter(rowAdapter)
            )
        }
        //ImmutableList
        else if (typeMirror.isTypeOf(ImmutableList::class)) {
            val typeArg = typeMirror.typeArguments.first().extendsBoundOrSelf()
            val rowAdapter = findRowAdapter(typeArg, query) ?: return null
            return ImmutableListQueryResultAdapter(
                typeArg = typeArg,
                rowAdapter = rowAdapter
            )
        }
        //List
        else if (typeMirror.isTypeOf(java.util.List::class)) {
            val typeArg = typeMirror.typeArguments.first().extendsBoundOrSelf()
            val rowAdapter = findRowAdapter(typeArg, query) ?: return null
            return ListQueryResultAdapter(
                typeArg = typeArg,
                rowAdapter = rowAdapter
            )
        }
        //ImmutableMap
        //如果typeMirror是ImmutableMap<k,v>,转换成Map<k,v>,对当前Map<k,v>执行当前findQueryResultAdapter方法，MapType作为typeMirror类型查找QueryResultAdapter对象，并且返回
        //              ImmutableMapQueryResultAdapter(
        //                keyTypeArg = keyTypeArg,
        //                valueTypeArg = valueTypeArg,
        //                resultAdapter = resultAdapter
        //            )
        else if (typeMirror.isTypeOf(ImmutableMap::class)) {
            val keyTypeArg = typeMirror.typeArguments[0].extendsBoundOrSelf()
            val valueTypeArg = typeMirror.typeArguments[1].extendsBoundOrSelf()

            // Create a type mirror for a regular Map in order to use MapQueryResultAdapter. This
            // avoids code duplication as Immutable Map can be initialized by creating an immutable
            // copy of a regular map.
            val mapType = context.processingEnv.getDeclaredType(
                context.processingEnv.requireTypeElement(Map::class),
                keyTypeArg,
                valueTypeArg
            )

            val resultAdapter = findQueryResultAdapter(mapType, query, extras) ?: return null
            return ImmutableMapQueryResultAdapter(
                keyTypeArg = keyTypeArg,
                valueTypeArg = valueTypeArg,
                resultAdapter = resultAdapter
            )
        }

        //如果typeMirror是ImmutableSetMultimap，ImmutableListMultimap，<k,v>中的V必须是一个类；分别对k和v配合query执行findRowAdapter方法;返回
        //          GuavaImmutableMultimapQueryResultAdapter(
        //                keyTypeArg = keyTypeArg,
        //                valueTypeArg = valueTypeArg,
        //                keyRowAdapter = keyRowAdapter,
        //                valueRowAdapter = valueRowAdapter,
        //                immutableClassName = immutableClassName
        //            )
        //当前校验@MapInfo：如果没有使用@MapInfo，或者说@MapInfo#keyColumn为空，
        // 那么k必须是表字段支持类型或者是自定义转换类型转换成表字段支持类型；v同理；
        //rawQuery方法返回类型不允许使用 ImmutableMultimap
        else if (typeMirror.isTypeOf(ImmutableSetMultimap::class) ||
            typeMirror.isTypeOf(ImmutableListMultimap::class) ||
            typeMirror.isTypeOf(ImmutableMultimap::class)
        ) {
            val keyTypeArg = typeMirror.typeArguments[0].extendsBoundOrSelf()
            val valueTypeArg = typeMirror.typeArguments[1].extendsBoundOrSelf()

            //rawQuery方法返回类型如果是`ImmutableSetMultimap，ImmutableListMultimap，ImmutableMultimap`，那么<K,V>中的V必须是一个类；
            if (valueTypeArg.typeElement == null) {
                context.logger.e(
                    "Guava multimap 'value' type argument does not represent a class. " +
                            "Found $valueTypeArg."
                )
                return null
            }

            //rawQuery方法返回类型不允许使用ImmutableMultimap
            val immutableClassName = if (typeMirror.isTypeOf(ImmutableListMultimap::class)) {
                ClassName.get(ImmutableListMultimap::class.java)
            } else if (typeMirror.isTypeOf(ImmutableSetMultimap::class)) {
                ClassName.get(ImmutableSetMultimap::class.java)
            } else {
                // Return type is base class ImmutableMultimap which is not recommended.
                context.logger.e(DO_NOT_USE_GENERIC_IMMUTABLE_MULTIMAP)
                return null
            }

            // Get @MapInfo info if any (this might be null)
            val mapInfo = extras.getData(MapInfo::class)
            val keyRowAdapter = findRowAdapter(
                typeMirror = keyTypeArg,
                query = query,
                columnName = mapInfo?.keyColumnName
            ) ?: return null

            val valueRowAdapter = findRowAdapter(
                typeMirror = valueTypeArg,
                query = query,
                columnName = mapInfo?.valueColumnName
            ) ?: return null

            validateMapTypeArgs(
                keyTypeArg = keyTypeArg,
                valueTypeArg = valueTypeArg,
                keyReader = findCursorValueReader(keyTypeArg, null),
                valueReader = findCursorValueReader(valueTypeArg, null),
                mapInfo = mapInfo,
                logger = context.logger
            )

            return GuavaImmutableMultimapQueryResultAdapter(
                keyTypeArg = keyTypeArg,
                valueTypeArg = valueTypeArg,
                keyRowAdapter = keyRowAdapter,
                valueRowAdapter = valueRowAdapter,
                immutableClassName = immutableClassName
            )
        }

        //Map、androidx.collection.ArrayMap、androidx.collection.LongSparseArray、androidx.collection.SparseArrayCompat
        //如果typeMirror是Map、androidx.collection.ArrayMap、androidx.collection.LongSparseArray、androidx.collection.SparseArrayCompat类型，那么
        //① 如果是androidx.collection.LongSparseArray将long作为k、androidx.collection.SparseArrayCompat将int作为k；泛型参数作为v；
        //② <k,v>,v必须是一个类；如果v是一个集合，那么只能是Set或List，并且对item类型作为v校验，
        //③ 对k和v作为参数和query一起执行findRowAdapter方法，返回
        // 那么k必须是表字段支持类型或者是自定义转换类型转换成表字段支持类型；v同理；
        //                  MapQueryResultAdapter(
        //                        keyTypeArg = keyTypeArg,
        //                        valueTypeArg = valueTypeArg,
        //                        keyRowAdapter = keyRowAdapter,
        //                        valueRowAdapter = valueRowAdapter,
        //                        valueCollectionType = mapValueTypeArg,如果v不是list或set集合，当前属性为null；
        //                        isArrayMap = typeMirror.rawType.typeName == ARRAY_MAP,
        //                        isSparseArray = isSparseArray
        //                    )
        //当前校验@MapInfo：如果没有使用@MapInfo，或者说@MapInfo#keyColumn为空，
        // 那么k必须是表字段支持类型或者是自定义转换类型转换成表字段支持类型；v同理；
        else if (typeMirror.isTypeOf(java.util.Map::class) ||
            typeMirror.rawType.typeName == ARRAY_MAP ||
            typeMirror.rawType.typeName == LONG_SPARSE_ARRAY ||
            typeMirror.rawType.typeName == INT_SPARSE_ARRAY
        ) {
            val keyTypeArg = if (typeMirror.rawType.typeName == LONG_SPARSE_ARRAY) {
                context.processingEnv.requireType(TypeName.LONG)
            } else if (typeMirror.rawType.typeName == INT_SPARSE_ARRAY) {
                context.processingEnv.requireType(TypeName.INT)
            } else {
                typeMirror.typeArguments[0].extendsBoundOrSelf()
            }

            val isSparseArray = if (typeMirror.rawType.typeName == LONG_SPARSE_ARRAY) {
                LONG_SPARSE_ARRAY
            } else if (typeMirror.rawType.typeName == INT_SPARSE_ARRAY) {
                INT_SPARSE_ARRAY
            } else {
                null
            }

            val mapValueTypeArg = if (isSparseArray != null) {
                typeMirror.typeArguments[0].extendsBoundOrSelf()
            } else {
                typeMirror.typeArguments[1].extendsBoundOrSelf()
            }

            //如果rawQuery方法返回类型是LongSparseArray或SparseArrayCompat，判断当前泛型类型必须是一个类，作为value值；如果方法返回类型是Map或ArrayMap，判断<K,V> 的V必须是一个类，作为value值；
            if (mapValueTypeArg.typeElement == null) {
                context.logger.e(
                    "Multimap 'value' collection type argument does not represent a class. " +
                            "Found $mapValueTypeArg."
                )
                return null
            }
            // TODO: Handle nested collection values in the map

            // Get @MapInfo info if any (this might be null)
            val mapInfo = extras.getData(MapInfo::class)
            val collectionTypeRaw = context.COMMON_TYPES.READONLY_COLLECTION.rawType
            if (collectionTypeRaw.isAssignableFrom(mapValueTypeArg.rawType)) {
                // The Map's value type argument is assignable to a Collection, we need to make
                // sure it is either a list or a set.
                //如果value值是collection集合，那么只允许是List或Set集合；
                if (
                    mapValueTypeArg.isTypeOf(java.util.List::class) ||
                    mapValueTypeArg.isTypeOf(java.util.Set::class)
                ) {
                    val valueTypeArg = mapValueTypeArg.typeArguments.single().extendsBoundOrSelf()

                    val keyRowAdapter = findRowAdapter(
                        typeMirror = keyTypeArg,
                        query = query,
                        columnName = mapInfo?.keyColumnName
                    ) ?: return null

                    val valueRowAdapter = findRowAdapter(
                        typeMirror = valueTypeArg,
                        query = query,
                        columnName = mapInfo?.valueColumnName
                    ) ?: return null

                    validateMapTypeArgs(
                        keyTypeArg = keyTypeArg,
                        valueTypeArg = valueTypeArg,
                        keyReader = findCursorValueReader(keyTypeArg, null),
                        valueReader = findCursorValueReader(valueTypeArg, null),
                        mapInfo = mapInfo,
                        logger = context.logger
                    )

                    return MapQueryResultAdapter(
                        keyTypeArg = keyTypeArg,
                        valueTypeArg = valueTypeArg,
                        keyRowAdapter = keyRowAdapter,
                        valueRowAdapter = valueRowAdapter,
                        valueCollectionType = mapValueTypeArg,
                        isArrayMap = typeMirror.rawType.typeName == ARRAY_MAP,
                        isSparseArray = isSparseArray
                    )
                } else {
                    context.logger.e(
                        valueCollectionMustBeListOrSet(mapValueTypeArg.typeName)
                    )
                }
            } else {
                val keyRowAdapter = findRowAdapter(
                    typeMirror = keyTypeArg,
                    query = query,
                    columnName = mapInfo?.keyColumnName
                ) ?: return null
                val valueRowAdapter = findRowAdapter(
                    typeMirror = mapValueTypeArg,
                    query = query,
                    columnName = mapInfo?.valueColumnName
                ) ?: return null

                validateMapTypeArgs(
                    keyTypeArg = keyTypeArg,
                    valueTypeArg = mapValueTypeArg,
                    keyReader = findCursorValueReader(keyTypeArg, null),
                    valueReader = findCursorValueReader(mapValueTypeArg, null),
                    mapInfo = mapInfo,
                    logger = context.logger
                )
                return MapQueryResultAdapter(
                    keyTypeArg = keyTypeArg,
                    valueTypeArg = mapValueTypeArg,
                    keyRowAdapter = keyRowAdapter,
                    valueRowAdapter = valueRowAdapter,
                    valueCollectionType = null,
                    isArrayMap = typeMirror.rawType.typeName == ARRAY_MAP,
                    isSparseArray = isSparseArray
                )
            }
        }
        return null
    }

    /**
     * Find a converter from cursor to the given type mirror.
     * If there is information about the query result, we try to use it to accept *any* POJO.
     *
     * 根据typeMirror类型，查找Row表字段适配
     *
     * 0. 如果当前typeMirror是错误类型，直接返回null；
     *
     * 1. 如果typeMirror不是基础类型：
     * 1.1 当前typeMirror如果是泛型，则直接返回null，当前room版本不支持
     * 1.2 query查询结果为空：判断typeMirror表示的节点是否是@Entity修饰的类，如果是生成EntityRowAdapter对象，并返回；否则继续往下校验；
     * 1.3 query查询结果不为空 && sql查询语句正确 && sql查询结果正确，并且typeMirror生成pojo对象过程中没有产生错误日志：typeMirror表示的节点生成pojo对象，然后生成PojoRowAdapter对象；否则继续往下校验
     * 1.4 columnName不为空，当前columnName去query查询结果中查找，找到作为匹配类型（找不到不用管）：在① 表字段支持类型；② 自定义转换类型中去匹配，匹配成功，返回SingleNamedColumnRowAdapter；否则继续往下校验
     * 1.5 query查找结果有且仅有一个字段，当前字段类型在① 表字段支持类型；② 自定义转换类型中去匹配，匹配成功，返回SingleColumnRowAdapter；否则继续往下校验；
     * 1.6 query查询结果不为空 && sql查询语句正确 && sql查询结果正确,但是typeMirror生成pojo对象是出错：返回rawAdapter，并且返回错误信息;否则继续往下校验；
     * 1.7 如果query查询结果为空，typeMirror类型不是void && 不是voidObject && 不是kotlinUnit,当前typeMirror生成pojo对象，并且最终返回PojoRowAdapter对象；否则继续往下；
     * 1.8 以上都不满足返回null
     *
     *
     * 2. 如果typrMirror是基础类型：
     * 2.1 columnName != null，并且columnName在sql查询字段中能匹配到表字段（当前表字段类型作为偏向类型校验，如果匹配失败，则不作偏向类型校验），根据typeMirror去匹配：（1）表字段支持类型；（2）自定义类型转换表字段支持类型，（3）枚举或UUID类型
     *      匹配成功再校验偏向类型，成功则返回SingleNamedColumnRowAdapter，否则继续往下匹配；
     * 2.2 columnName = null 或 2.1中匹配失败：当前typeMirror适配（1）表字段支持类型；（2）自定义类型转换表字段支持类型，（3）枚举或UUID类型
     *      匹配成功返回SingleColumnRowAdapter；否则返回null，表示匹配失败。
     */
    fun findRowAdapter(
        typeMirror: XType,
        query: ParsedQuery,
        columnName: String? = null
    ): RowAdapter? {
        //错误类型直接返回null
        if (typeMirror.isError()) {
            return null
        }

        val typeElement = typeMirror.typeElement
        //节点类型不是基础类型
        if (typeElement != null && !typeMirror.typeName.isPrimitive) {
            //当前节点如果是泛型，则直接返回null
            if (typeMirror.typeArguments.isNotEmpty()) {
                // TODO one day support this
                return null
            }
            val resultInfo = query.resultInfo

            //query查询结果不为空 && sql查询语句正确 && sql查询结果正确：typeMirror表示的节点生成pojo对象，然后生成PojoRowAdapter对象；
            val (rowAdapter, rowAdapterLogs) = if (resultInfo != null && query.errors.isEmpty() &&
                resultInfo.error == null
            ) {
                // if result info is not null, first try a pojo row adapter
                context.collectLogs { subContext ->
                    val pojo = PojoProcessor.createFor(
                        context = subContext,
                        element = typeElement,
                        bindingScope = FieldProcessor.BindingScope.READ_FROM_CURSOR,
                        parent = null
                    ).process()

                    PojoRowAdapter(
                        context = subContext,
                        info = resultInfo,
                        query = query,
                        pojo = pojo,
                        out = typeMirror
                    )
                }
            } else {
                Pair(null, null)
            }

            //query查询结果为空：判断typeMirror表示的节点是否是@Entity修饰的类，如果是生成EntityRowAdapter对象，并返回；
            if (rowAdapter == null && query.resultInfo == null) {
                // we don't know what query returns. Check for entity.
                if (typeElement.isEntityElement()) {
                    return EntityRowAdapter(
                        EntityProcessor(
                            context = context,
                            element = typeElement
                        ).process()
                    )
                }
            }

            //rawAdapter生成过程中没有生成错误日志，返回rowAdapter
            if (rowAdapter != null && rowAdapterLogs?.hasErrors() != true) {
                rowAdapterLogs?.writeTo(context)
                return rowAdapter
            }

            //columnName不为空，当前columnName去query查询结果中查找，找到作为匹配类型（找不到不用管）：在① 表字段支持类型；② 自定义转换类型中去匹配，匹配成功，返回SingleNamedColumnRowAdapter；
            if (columnName != null) {
                val singleNamedColumn = findCursorValueReader(
                    typeMirror,
                    query.resultInfo?.columns?.find {
                        it.name == columnName
                    }?.type
                )
                if (singleNamedColumn != null) {
                    return SingleNamedColumnRowAdapter(singleNamedColumn, columnName)
                }
            }

            //query查找结果有且仅有一个字段，当前字段类型在① 表字段支持类型；② 自定义转换类型中去匹配，匹配成功，返回SingleColumnRowAdapter；
            if ((resultInfo?.columns?.size ?: 1) == 1) {
                val singleColumn = findCursorValueReader(
                    typeMirror,
                    resultInfo?.columns?.get(0)?.type
                )
                if (singleColumn != null) {
                    return SingleColumnRowAdapter(singleColumn)
                }
            }
            // if we tried, return its errors
            //query查询结果不为空 && sql查询语句正确 && sql查询结果正确,但是typeMirror生成pojo对象是出错：返回rawAdapter，并且返回错误信息
            if (rowAdapter != null) {
                rowAdapterLogs?.writeTo(context)
                return rowAdapter
            }

            // use pojo adapter as a last resort.
            // this happens when @RawQuery or @SkipVerification is used.
            //如果query查询结果为空，typeMirror类型不是void && 不是voidObject && 不是kotlinUnit,当前typeMirror生成pojo对象，并且最终返回PojoRowAdapter对象
            if (query.resultInfo == null &&
                typeMirror.isNotVoid() &&
                typeMirror.isNotVoidObject() &&
                typeMirror.isNotKotlinUnit()
            ) {
                val pojo = PojoProcessor.createFor(
                    context = context,
                    element = typeElement,
                    bindingScope = FieldProcessor.BindingScope.READ_FROM_CURSOR,
                    parent = null
                ).process()
                return PojoRowAdapter(
                    context = context,
                    info = null,
                    query = query,
                    pojo = pojo,
                    out = typeMirror
                )
            }
            //否则返回null
            return null
        } else {
            if (columnName != null) {
                val singleNamedColumn = findCursorValueReader(
                    typeMirror,
                    query.resultInfo?.columns?.find { it.name == columnName }?.type
                )
                if (singleNamedColumn != null) {
                    return SingleNamedColumnRowAdapter(singleNamedColumn, columnName)
                }
            }
            val singleColumn = findCursorValueReader(typeMirror, null) ?: return null
            return SingleColumnRowAdapter(singleColumn)
        }
    }

    //查询参数：有三种类型
    // 1. collection集合，查看集合中的泛型对象类型
    // 2. 非byte数组；数组对象类型
    // 3. 不是1，也不是2，对象类型
    //以上三种按照顺序，对对象类型适配，要么支持表字段类型；否则，支持自定义转换类型，再转换成表字段支持类型；否则，对象类型是枚举或UUID（这种情况是特例，可以忽略不计）
    fun findQueryParameterAdapter(
        typeMirror: XType,
        isMultipleParameter: Boolean//参数是否集合类型
    ): QueryParameterAdapter? {
        //如果是Collection类
        if (context.COMMON_TYPES.READONLY_COLLECTION.rawType.isAssignableFrom(typeMirror)) {
            val typeArg = typeMirror.typeArguments.first().extendsBoundOrSelf()
            // An adapter for the collection type arg wrapped in the built-in collection adapter.
            val wrappedCollectionAdapter = findStatementValueBinder(typeArg, null)?.let {
                CollectionQueryParameterAdapter(it)
            }
            // An adapter for the collection itself, likely a user provided type converter for the
            // collection.
            val directCollectionAdapter = findStatementValueBinder(typeMirror, null)?.let {
                BasicQueryParameterAdapter(it)
            }
            // Prioritize built-in collection adapters when finding an adapter for a multi-value
            // binding param since it is likely wrong to use a collection to single value converter
            // for an expression that takes in multiple values.
            //如果当前查询参数是集合
            return if (isMultipleParameter) {
                wrappedCollectionAdapter ?: directCollectionAdapter
            } else {
                directCollectionAdapter ?: wrappedCollectionAdapter
            }
        }
        //如果是非byte数组类型
        else if (typeMirror.isArray() && typeMirror.componentType.isNotByte()) {
            val component = typeMirror.componentType
            val binder = findStatementValueBinder(component, null) ?: return null
            return ArrayQueryParameterAdapter(binder)
        }
        //其他类型
        else {
            val binder = findStatementValueBinder(typeMirror, null) ?: return null
            return BasicQueryParameterAdapter(binder)
        }
    }

    private fun getAllColumnAdapters(input: XType): List<ColumnTypeAdapter> {
        return columnTypeAdapters.filter {
            input.isSameType(it.out)
        }
    }
}
