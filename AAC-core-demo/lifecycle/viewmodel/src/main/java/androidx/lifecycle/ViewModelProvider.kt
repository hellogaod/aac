package androidx.lifecycle

import android.app.Application
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.VIEW_MODEL_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.InitializerViewModelFactory
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.ViewModelInitializer
import java.lang.reflect.InvocationTargetException

/**
 * An utility class that provides `ViewModels` for a scope.
 *
 * Default `ViewModelProvider` for an `Activity` or a `Fragment` can be obtained
 * by passing it to the constructor: `ViewModelProvider(myFragment)`
 */
public open class ViewModelProvider

/**
 * Creates a ViewModelProvider
 *
 * @param store `ViewModelStore` where ViewModels will be stored.
 * @param factory factory a `Factory` which will be used to instantiate new `ViewModels`
 * @param defaultCreationExtras extras to pass to a factory
 */
constructor(
    private val store: ViewModelStore,
    private val factory: Factory,
    private val defaultCreationExtras: CreationExtras = CreationExtras.Empty,
) {
    /**
     * Implementations of `Factory` interface are responsible to instantiate ViewModels.
     */
    public interface Factory {
        /**
         * Creates a new instance of the given `Class`.
         *
         * Default implementation throws [UnsupportedOperationException].
         *
         * @param modelClass a `Class` whose instance is requested
         * @return a newly created ViewModel
         */
        fun <T : ViewModel> create(modelClass: Class<T>): T {
            throw UnsupportedOperationException(
                "Factory.create(String) is unsupported.  This Factory requires " +
                        "`CreationExtras` to be passed into `create` method."
            )
        }

        /**
         * Creates a new instance of the given `Class`.
         *
         * @param modelClass a `Class` whose instance is requested
         * @param extras an additional information for this creation request
         * @return a newly created ViewModel
         */
        public fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
            create(modelClass)

        companion object {
            /**
             * Creates an [InitializerViewModelFactory] using the given initializers.
             *
             * @param initializers the class initializer pairs used for the factory to create
             * simple view models
             */
            @JvmStatic
            fun from(vararg initializers: ViewModelInitializer<*>): Factory =
                InitializerViewModelFactory(*initializers)
        }
    }

    /**
     * @suppress
     */
    public open class OnRequeryFactory {
        public open fun onRequery(viewModel: ViewModel) {}
    }

    public constructor(
        owner: ViewModelStoreOwner
    ) : this(
        owner.viewModelStore,
        AndroidViewModelFactory.defaultFactory(owner),
        defaultCreationExtras(owner)
    )


    /**
     * Creates `ViewModelProvider`, which will create `ViewModels` via the given
     * `Factory` and retain them in a store of the given `ViewModelStoreOwner`.
     *
     * @param owner   a `ViewModelStoreOwner` whose [ViewModelStore] will be used to
     * retain `ViewModels`
     * @param factory a `Factory` which will be used to instantiate
     * new `ViewModels`
     */
    public constructor(owner: ViewModelStoreOwner, factory: Factory) : this(
        owner.viewModelStore,
        factory,
        defaultCreationExtras(owner)
    )

    /**
     * Returns an existing ViewModel or creates a new one in the scope (usually, a fragment or
     * an activity), associated with this `ViewModelProvider`.
     *
     *
     * The created ViewModel is associated with the given scope and will be retained
     * as long as the scope is alive (e.g. if it is an activity, until it is
     * finished or process is killed).
     *
     * @param modelClass The class of the ViewModel to create an instance of it if it is not
     * present.
     * @return A ViewModel that is an instance of the given type `T`.
     * @throws IllegalArgumentException if the given [modelClass] is local or anonymous class.
     */
    @MainThread
    public open operator fun <T : ViewModel> get(modelClass: Class<T>): T {
        val canonicalName = modelClass.canonicalName
            ?: throw IllegalArgumentException("Local and anonymous classes can not be ViewModels")
        return get("${AndroidViewModelFactory.DEFAULT_KEY}:$canonicalName", modelClass)
    }

    /**
     * Returns an existing ViewModel or creates a new one in the scope (usually, a fragment or
     * an activity), associated with this `ViewModelProvider`.
     *
     * The created ViewModel is associated with the given scope and will be retained
     * as long as the scope is alive (e.g. if it is an activity, until it is
     * finished or process is killed).
     *
     * @param key        The key to use to identify the ViewModel.
     * @param modelClass The class of the ViewModel to create an instance of it if it is not
     * present.
     * @return A ViewModel that is an instance of the given type `T`.
     */
    @Suppress("UNCHECKED_CAST")
    @MainThread
    public open operator fun <T : ViewModel> get(key: String, modelClass: Class<T>): T {
        val viewModel = store[key];
        if (modelClass.isInstance(viewModel)) {
            (factory as? OnRequeryFactory)?.onRequery(viewModel)
            return viewModel as T
        } else {
            @Suppress("ControlFlowWithEmptyBody")
            if (viewModel != null) {
                // TODO: log a warning.
            }
        }
        val extras = MutableCreationExtras(defaultCreationExtras)
        extras[VIEW_MODEL_KEY] = key
        return factory.create(
            modelClass,
            extras
        ).also { store.put(key, it) }
    }

    /**
     * Simple factory, which calls empty constructor on the give class.
     */
    // actually there is getInstance()
    @Suppress("SingletonConstructor")
    public open class NewInstanceFactory : Factory {
        @Suppress("DocumentExceptions")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return try {
                modelClass.newInstance();
            } catch (e: InstantiationException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }
        }

        public companion object {
            private var sInstance: NewInstanceFactory? = null


            /**
             * @suppress
             * Retrieve a singleton instance of NewInstanceFactory.
             *
             * @return A valid [NewInstanceFactory]
             */
            @JvmStatic
            public val instance: NewInstanceFactory
                get() {
                    if (sInstance == null) {
                        sInstance = NewInstanceFactory()
                    }
                    return sInstance!!
                }

            private object ViewModelKeyImpl : CreationExtras.Key<String>

            /**
             * A [CreationExtras.Key] to get a key associated with a requested
             * `ViewModel` from [CreationExtras]
             *
             *  `ViewModelProvider` automatically puts a key that was passed to
             *  `ViewModelProvider.get(key, MyViewModel::class.java)`
             *  or generated in `ViewModelProvider.get(MyViewModel::class.java)` to the `CreationExtras` that
             *  are passed to [ViewModelProvider.Factory].
             */
            @JvmField
            val VIEW_MODEL_KEY: CreationExtras.Key<String> = ViewModelKeyImpl
        }
    }

    /**
     * [Factory] which may create [AndroidViewModel] and
     * [ViewModel], which have an empty constructor.
     *
     * @param application an application to pass in [AndroidViewModel]
     */
    public open class AndroidViewModelFactory
    private constructor(
        private val application: Application?,
        // parameter to avoid clash between constructors with nullable and non-nullable
        // Application
        @Suppress("UNUSED_PARAMETER") unused: Int,
    ) : NewInstanceFactory() {

        /**
         * Constructs this factory.
         * When a factory is constructed this way, a component for which [ViewModel] is created
         * must provide an [Application] by [APPLICATION_KEY] in [CreationExtras], otherwise
         *  [IllegalArgumentException] will be thrown from [create] method.
         */
        @Suppress("SingletonConstructor")
        public constructor() : this(null, 0)

        /**
         * Constructs this factory.
         *
         * @param application an application to pass in [AndroidViewModel]
         */
        @Suppress("SingletonConstructor")
        public constructor(application: Application) : this(application, 0)

        @Suppress("DocumentExceptions")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            return if (application != null) {
                create(modelClass, application)
            } else {
                val application = extras[APPLICATION_KEY]
                if (application != null){
                    create(modelClass,application)
                }else{
                    // For AndroidViewModels, CreationExtras must have an application set
                    if (AndroidViewModel::class.java.isAssignableFrom(modelClass)) {
                        throw IllegalArgumentException(
                            "CreationExtras must have an application by `APPLICATION_KEY`"
                        )
                    }
                    super.create(modelClass)
                }
            }
        }

        @Suppress("DocumentExceptions")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (application == null) {
                throw UnsupportedOperationException(
                    "AndroidViewModelFactory constructed " +
                            "with empty constructor works only with " +
                            "create(modelClass: Class<T>, extras: CreationExtras)."
                )
            } else {
                create(modelClass, application)
            }
        }

        @Suppress("DocumentExceptions")
        private fun <T : ViewModel> create(modelClass: Class<T>, app: Application): T {
            return if (AndroidViewModel::class.java.isAssignableFrom(modelClass)) {
                try {
                    modelClass.getConstructor(Application::class.java).newInstance(app)
                } catch (e: NoSuchMethodException) {
                    throw RuntimeException("Cannot create an instance of $modelClass", e)
                } catch (e: IllegalAccessException) {
                    throw RuntimeException("Cannot create an instance of $modelClass", e)
                } catch (e: InstantiationException) {
                    throw RuntimeException("Cannot create an instance of $modelClass", e)
                } catch (e: InvocationTargetException) {
                    throw RuntimeException("Cannot create an instance of $modelClass", e)
                }
            } else super.create(modelClass)
        }

        public companion object {

            internal fun defaultFactory(owner: ViewModelStoreOwner): Factory =
                if (owner is HasDefaultViewModelProviderFactory)
                    owner.defaultViewModelProviderFactory else instance

            internal const val DEFAULT_KEY = "androidx.lifecycle.ViewModelProvider.DefaultKey"

             var sInstance: AndroidViewModelFactory? = null

            /**
             * Retrieve a singleton instance of AndroidViewModelFactory.
             *
             * @param application an application to pass in [AndroidViewModel]
             * @return A valid [AndroidViewModelFactory]
             */
            @JvmStatic
            public fun getInstance(application: Application): AndroidViewModelFactory {
                if (sInstance == null) {
                    sInstance = AndroidViewModelFactory(application)
                }
                return sInstance!!
            }

            private object ApplicationKeyImpl : CreationExtras.Key<Application>

            /**
             * A [CreationExtras.Key] to query an application in which ViewModel is being created.
             */
            @JvmField
            val APPLICATION_KEY: CreationExtras.Key<Application> = ApplicationKeyImpl
        }
    }

    public companion object {
        internal fun defaultFactory(owner: ViewModelStoreOwner): Factory =
            if (owner is HasDefaultViewModelProviderFactory)
                owner.defaultViewModelProviderFactory else NewInstanceFactory.instance

        internal const val DEFAULT_KEY = "androidx.lifecycle.ViewModelProvider.DefaultKey"

        private var sInstance: AndroidViewModelFactory? = null


        /**
         * Retrieve a singleton instance of AndroidViewModelFactory.
         *
         * @param application an application to pass in [AndroidViewModel]
         * @return A valid [AndroidViewModelFactory]
         */
        @JvmStatic
        public fun getInstance(application: Application): AndroidViewModelFactory {
            if (AndroidViewModelFactory.sInstance == null) {
                AndroidViewModelFactory.sInstance = AndroidViewModelFactory(application)
            }
            return AndroidViewModelFactory.sInstance!!
        }

        private object ApplicationKeyImpl : CreationExtras.Key<Application>

        /**
         * A [CreationExtras.Key] to query an application in which ViewModel is being created.
         */
        @JvmField
        val APPLICATION_KEY: CreationExtras.Key<Application> = ApplicationKeyImpl
    }
}


internal fun defaultCreationExtras(owner: ViewModelStoreOwner): CreationExtras {
    return if (owner is HasDefaultViewModelProviderFactory) {
        owner.defaultViewModelCreationExtras
    } else {
        CreationExtras.Empty
    }
}

/**
 * Returns an existing ViewModel or creates a new one in the scope (usually, a fragment or
 * an activity), associated with this `ViewModelProvider`.
 *
 * @see ViewModelProvider.get(Class)
 */
@MainThread
public inline fun <reified VM : ViewModel> ViewModelProvider.get(): VM = get(VM::class.java)

