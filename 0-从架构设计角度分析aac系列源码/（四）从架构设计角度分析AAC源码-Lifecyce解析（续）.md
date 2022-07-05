# 前言 #

当前主要针对LiveData和ViewModel两个组件源码解析。这两个组件实际是Lifecycle模块下的两个小组件，关联Lifecycle组件，下面进入正题。

# LiveData组件 #

## 需求 ##

LiveData组件：创建**观察者**去**观察**activity(**生命周期拥有者**)的生命周期变化;然后对当前LiveData**赋值**，由生命周期拥有者的生命状态决定是否通知观察者（即回调观察者的方法）。

我们回顾一下Lifecycle组件：创建**观察者**去**观察**activity(**生命周期拥有者**)的生命周期变化。

>LiveData组件中的观察者和Lifecycle组件中的观察者是同样的设计思路。

## 架构设计 ##

根据以上思路，我们做一个架构设计：

1. LiveData组件定义一个观察者，观察生命周期的变化；

 - 在 LiveData内部需要自定义一个Lifecycle组件生命周期观察者观察生命周期拥有者生命周期变化，再去通知LiveData观察者；

 - 但是我们需要注意一点：LiveData只需要感知Destroy和非Destroy两种状态即可；非Destroy状态下通知观察者；Destroy状态下切断连接关系，方便回收；

2. LiveData赋值。

 - 根据MVVM（或者MVP）模式，为了完成数据层和UI层解耦，数据赋值应该放在VM（ViewModel、或者MVP的P层），但是带来一个新的问题，数据赋值如果不在主线程中，那么回调更新UI必然报错，这个问题需要考虑到代码实现中。

## 代码实现 ##

根据以上架构设计，我们做具体代码实现。


### 1. LiveData组件定义一个观察者，观察生命周期的变化


（1）定义一个观察者Observer，用于观察，onChanged方法表示通知观察者更新。

	public interface Observer<T> {
	    /**
	     * Called when the data is changed.
	     *
	     * @param t The new data
	     */
	    void onChanged(T t);
	}

（2）在 LiveData内部需要自定义一个Lifecycle组件生命周期观察者观察生命周期拥有者生命周期变化，再去通知LiveData观察者；

	class LifecycleBoundObserver implements LifecycleEventObserver{
		@Override
        public void onStateChanged(@NonNull LifecycleOwner source,
                                   @NonNull Lifecycle.Event event) {
		}
	}

当前LifecycleBoundObserver类传递一个LiveData的Observer观察者对象，在LifecycleBoundObserver观察者回调onStateChanged方法时，调用当前Observer观察者的onChanged回调；


还需要定义一个ObserverWrapper对象，见名知意：包裹Observer对象.该对象的作用是扩展Observer观察者，扩展两个字段:（1）mActive用于判断当前Observer是否是活动状态；（2）mLastVersion表示当前Observer版本，对比LiveData版本，小于LiveData版本表示当前观察者没有被通知回调。

	private abstract class ObserverWrapper {
	        final Observer<? super T> mObserver;
	        boolean mActive;
	        int mLastVersion = START_VERSION;
	
		ObserverWrapper(Observer<? super T> observer) {
	            mObserver = observer;
	        }
	}


### 2. LiveData赋值 ###

赋值，我们这里做了2个方法，一个是setValue，一个是postValue。setValue方法必须在主线程中使用，postValue方法表示可以在子线程中做赋值工作。


    @Override
    public void postValue(T value) {
        super.postValue(value);
    }

    @Override
    public void setValue(T value) {
        super.setValue(value);
    }

> 更多细节表现在代码中，可自行查看，基于当期设计基础上，对我们从架构理解LiveData事半功倍。

代码地址和标签下面会在提供github地址。

# ViewModel组件 #

## 需求 ##

android被官方认可的是MVVM设计模式，其中M表示model数据模型，V表示view存，VM表示实际业务处理层，M和V是不会直接关联的，需要通过VM层提供M数据并且展示在V层上，换句话说，**V(view)层观察VM(viewmodel)层的M(model)数据变化，如果发生了变化则展示，如果V层生命周期已结束，那么切断V和VM层的关联，并且切断VM层和M层之间的关联。**

突然想到一个问题，model和bean(或者叫entity)之间有什么关系或者区别？？？

好像没啥区别哈！！！其实不然，model表示是数据模型，bean或entity表示实体类模型，model可以使用bean或entity代替，或者某些角度来说两者区别不大。但是bean或entity表示的是具体一个对象的特征属性，例如用户（特征属性是年龄、姓名、性别），model表示的是对外提供哪些数据,例如MVVM，MV需要向V层提供M数据模型，案例如下：

1. 根据View层视图我们判断需要哪些信息，例如需要：①用户对象信息；②用户订单信息，那么我们去设计一个model模型：

		public interface UserModel{
			public UserInfo getUserInfo();

			public UserOrderInfo getUserOrderInfo();
		}

2. 具体UserModelImpl类实现当前UserModel，用于用户信息和用户订单信息的具体实现；

		public class UserModelImpl implements UserModel {
			...
		}

3. 我们在viewmodel层定义 UserModel接口即可

		public class MyViewModel extends AndroidViewModel {
			
			private UserModel usermodel ;

			@Override
			public MyViewModel(Application application,UserModel usermodel){
				this.usermodel = usermodel;
				...
			}

			...
		}

4. 在实例化MyViewModel时，将我们实现的UerModelImpl作为构造函数参数传递过来即可。

> 使用方法千千万万，没必要过于纠结。

## 架构实现 ##

以上的需求目的在于：

1. 当前生命周期拥有者销毁时，数据model和当前数据所在的videmodel和view层（生命周期拥有者）之间切断关联，并且最好切断关联后，对viewmodel，model，view里面的对象也一个个切断关联，切断关联的目的是：

 - （1）根据内存回收机制，一旦根节点和某一个节点切断关联，那么这个节点就是被回收对象；

 - （2）activity被回收了，但是如果当前activity的关联对象中还引用了当前activity对象，那么当前activity是不会被回收的。

2. MVVM模型的实现方式，将Model-View通过ViewModel关联起来。


### 架构设计 ###

下面的步骤怎么看？下看1,2,3大步骤，再看1中的（1），（2），（3）小步骤，以此类推，这个是给读者讲的，我看别人是这么看的！

1. 新建一个ViewModel类，当前类是所有ViewModel实现的基类；

 - （1）当前类可以定义一个集合，用于收集**指定对象**，①set方法：指定对象使用时收集到Viewmodel类中，②remove方法：不用时删掉当前指定对象；③clear方法：切除和View层的关联时将当前集合清除；

 - （2）那么肯定要定义一个**指定对象**了：ClearObject接口，不一定要和源码一模一样吧，我这里是自己的设想，源码中是Closeable对象，而且**架构设计是全局性的，代码具体实现出现一点点偏差才是正常的。**

2. 新建一个ViewModelStore类，见名知意，用于收集ViewModel类，所有ViewModel实现类都存储在当前ViewModelStore类中；

 - 对viewmodel的一些操作方法，增删改查。

3. 新建一个ViewModelStoreOwner类，viewModelStore拥有者；

 - 这么设计目的在于拥有者可以拥有多个viewmodel，即拥有者和viewmodel是一对多的关系。

4. 新建一个ViewModelProvider类，viewModel提供者；

 - （1）提供工厂模式新建viewmodel类；

 - （2）将新建的viewmodel存储到viewModelStore中

 - （3）扩展性：当前会提供一些默认的创建viewmodel工厂，但是对一些不定构造函数的字段，那么用户需要自定义Factory实现create方式自己去创建ViewModel（该部分必然要采用接口设计模式，如果不理解仔细阅读代码）

5. 新建一个defaultViewModelProviderFactory，接口实现一个方法，该方法用于提供工厂模式的Factory对象，该Factory工厂模式用于创建ViewModel。

## 代码实现 ##


**1.新建一个ViewModel类，当前类是所有ViewModel实现的基类；**


代码比较简单，自行其查看，这里注意有ViewModel和AndroidViewModel两个类；


**2.新建一个ViewModelStore类，见名知意，用于收集ViewModel类，所有ViewModel实现类都存储在当前ViewModelStore类中；**

代码简单，自行查看。



**3.新建一个ViewModelStoreOwner类，viewModelStore拥有者；**

	 public interface ViewModelStoreOwner {
	    /**
	     * Returns owned {@link ViewModelStore}
	     *
	     * @return a {@code ViewModelStore}
	     */
	    @NonNull
	    ViewModelStore getViewModelStore();
	}

**4.新建一个ViewModelProvider类，viewModel提供者；**

（1）提供工厂模式新建viewmodel类；

	public interface Factory {
	       
	        fun <T : ViewModel> create(modelClass: Class<T>): T {
	            throw UnsupportedOperationException(
	                "Factory.create(String) is unsupported.  This Factory requires " +
	                        "`CreationExtras` to be passed into `create` method."
	            )
	        }
	
	       
	        public fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
	            create(modelClass)
	}


（2）将新建的viewmodel存储到viewModelStore中，这里新建ViewModel，必然调用ViewModelProvider类的get方法

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

（3）当前会提供一些默认的创建viewmodel工厂


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
	}

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
	}


**5.新建一个defaultViewModelProviderFactory接口，方便外部继承者自定义创建ViewModel的工厂**

	public interface HasDefaultViewModelProviderFactory {
	   
	    @NonNull
	    ViewModelProvider.Factory getDefaultViewModelProviderFactory();
	
	    
	    @NonNull
	    default CreationExtras getDefaultViewModelCreationExtras() {
	        return CreationExtras.Empty.INSTANCE;
	    }
	}


**6.viewmodelstoreowner拥有者创建viewmodelstore对象；**

	public class ComponentActivity extends Activity implements
	        LifecycleOwner,
	        ViewModelStoreOwner,
	        HasDefaultViewModelProviderFactory,
	        SavedStateRegistryOwner {
	
		private ViewModelStore mViewModelStore;
	
		@Override
	    public ViewModelStore getViewModelStore() {
	        if (getApplication() == null) {
	            throw new IllegalStateException("Your activity is not yet attached to the "
	                    + "Application instance. You can't request ViewModel before onCreate call.");
	        }
	        ensureViewModelStore();
	        return mViewModelStore;
	    }
	
	    @SuppressWarnings("WeakerAccess") /* synthetic access */
	    void ensureViewModelStore() {
	        if (mViewModelStore == null) {
	            NonConfigurationInstances nc =
	                    (NonConfigurationInstances) getLastNonConfigurationInstance();
	            if (nc != null) {
	                // Restore the ViewModelStore from NonConfigurationInstances
	                mViewModelStore = nc.viewModelStore;
	            }
	            if (mViewModelStore == null) {
	                mViewModelStore = new ViewModelStore();
	            }
	        }
	    }
	}

**7.实现DefaultViewModelProviderFactory接口，自定义一个SavedStateViewModelFactory工厂，用于创建ViewModel；**

	public class ComponentActivity extends Activity implements
	        LifecycleOwner,
	        ViewModelStoreOwner,
	        HasDefaultViewModelProviderFactory,
	        SavedStateRegistryOwner {
	
		private ViewModelProvider.Factory mDefaultFactory;
	
		@NonNull
	    @Override
	    public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
	        if (mDefaultFactory == null) {
	            mDefaultFactory = new SavedStateViewModelFactory(
	                    getApplication(),
	                    this,
	                    getIntent() != null ? getIntent().getExtras() : null);
	        }
	        return mDefaultFactory;
	    }
	}


**8.生命周期结束切断viewmodel、view、model之间的关联。**

在ComponentActivity类构造函数中实现，

	getLifecycle().addObserver(new LifecycleEventObserver() {
	            @Override
	            public void onStateChanged(@NonNull LifecycleOwner source,
	                                       @NonNull Lifecycle.Event event) {
	                if (event == Lifecycle.Event.ON_DESTROY) {
	
	                    // And clear the ViewModelStore
	                    if (!isChangingConfigurations()) {
	                        getViewModelStore().clear();
	                    }
	                }
	            }
	        });
	
	        getLifecycle().addObserver(new LifecycleEventObserver() {
	            @Override
	            public void onStateChanged(@NonNull LifecycleOwner source,
	                                       @NonNull Lifecycle.Event event) {
	                ensureViewModelStore();
	                getLifecycle().removeObserver(this);
	            }
	        });

## 扩展 ##

手机横竖屏切换时，如何能保证数据保持上一个状态。当手机横竖屏切换时，当前Activity的生命周期是：

	#Activity初次启动
	onCreate
	  - Activity :当前Activity实例对象1
	  - ViewModel:
	onStart
	onResume
	
	#屏幕旋转
	onPause
	onStop
	onRetainNonConfigurationInstance
	onDestroy
	onCreate
	  - Activity :当前Activity实例对象2，对象2和对象1不是同一个对象
	  - ViewModel:
	onStart
	onResume

如上所示，如果横竖屏切换，那么当前Activity使用的是新建的实例，而不是之前的实例，那么当前Activity中新建的ViewModel数据层那肯定也是新建的，所以之前的ViewModel中的状态肯定没有被带过来。

问题来了，如何保证横竖屏切换ViewModel层数据不变，即**横竖屏切换Activity实例改变，ViewModel实例不变**???

屏幕旋转时，我们在调用onRetainNonConfigurationInstance时，将当前mViewModelStore保存，在新的Activity实例中，当需要使用mViewModelStore变量时再去获取。

    @Override
    @Nullable
    @SuppressWarnings("deprecation")
    public final Object onRetainNonConfigurationInstance() {
        // Maintain backward compatibility.
        Object custom = onRetainCustomNonConfigurationInstance();

        ViewModelStore viewModelStore = mViewModelStore;
        if (viewModelStore == null) {
            // No one called getViewModelStore(), so see if there was an existing
            // ViewModelStore from our last NonConfigurationInstance
            NonConfigurationInstances nc =
                    (NonConfigurationInstances) getLastNonConfigurationInstance();
            if (nc != null) {
                viewModelStore = nc.viewModelStore;
            }
        }

        if (viewModelStore == null && custom == null) {
            return null;
        }

        NonConfigurationInstances nci = new NonConfigurationInstances();
        nci.custom = custom;
        nci.viewModelStore = viewModelStore;
        return nci;
    }




# 总结 #

以上是主体代码，具体代码实现看[AAC-core-demo项目](https://github.com/hellogaod/aac/tree/master/AAC-core-demo)，标签`lifecycle扩展之LiveData和ViewModel`