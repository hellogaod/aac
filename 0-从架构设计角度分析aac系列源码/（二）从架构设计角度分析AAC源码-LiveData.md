# 前言 #

本章重点是日常开发避免内存溢出，以及对该开发方式实现的迭代。

参考文献[Android 内存泄漏检测工具 LeakCanary 的使用](https://blog.csdn.net/hello_1995/article/details/120075342)

参考文献[Timber: Android日志记录](https://github.com/JakeWharton/timber)

# 工具 #

## Timber ##

其实是一个Log日志工具类，这里方便log统一管理。

gradle配置：

	repositories {
	  mavenCentral()
	}
	
	dependencies {
	  implementation 'com.jakewharton.timber:timber:5.0.1'
	}


在自定义的application oncreate方法中

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

具体使用和Log一致！e.g.

	Timber.e("error world！");

## LeakCanary ##

**LeakCanary 原理**

LeakCanary 是通过在 Application 的 registerActivityLifecycleCallbacks 方法实现对 Activity 销毁监听的，该方法主要用来统一管理所有 Activity 的生命周期。所有 Activity 在销毁时在其 OnDestory 方法中都会回调 ActivityLifecycleCallbacks 的 onActivityDestroyed 方法，而 LeakCanary 要做的就是在该方法中调用 RefWatcher.watch 方法实现对 Activity 进行内存泄漏监控。
那么，LeakCanary 是如何判断某个 Activity 可能会发生内存泄漏呢？答案是：WeakReference 和 ReferenceQueue，即 LeakCanary 利用了 Java 的 WeakReference 和 ReferenceQueue，通过将 Activity 包装到 WeakReference 中，被 WeakReference 包装过的 Activity 对象如果能够被回收，则说明引用可达，垃圾回收器就会将该 WeakReference 引用存放到 ReferenceQueue 中。假如我们要监视某个 Activity 对象，LeakCanary 就会去 ReferenceQueue 找这个对象的引用，如果找到了，说明该对象是引用可达的，能被 GC 回收，如果没有找到，说明该对象有可能发生了内存泄漏。最后，LeakCanary 会将 Java 堆转储到一个 .hprof 文件中，再使用 Shark（堆分析工具）析 .hprof 文件并定位堆转储中“滞留”的对象，并对每个"滞留"的对象找出 GC roots 的最短强引用路径，并确定是否是泄露，如果泄漏，建立导致泄露的引用链。最后，再将分析完毕的结果以通知的形式展现出来。

**使用**

在 **LeakCanary2.0 之前**我们接入的时候需要在 Application.onCreate 方法中显式调用 LeakCanary.install(this); 开启 LeakCanary 的内存监控。

从 **LeakCanary2.0 开始**通过库里注册的 ContentProvier 自己开启 LeakCanary 的内存监控，无需用户手动再添加初始化代码。

gradle添加依赖


	dependencies {
	   
	    // Leak Canary
	    debugImplementation 'com.squareup.leakcanary:leakcanary-android:2.7'
	
	}

## 日常开发避免内存溢出 ##

android中的数据应该是随着声明周期的变化而变化。例如一个Activity全局定义一个data数据：`private Data data;`，那么当前数据在oncreate方法中赋值：`data = new Data();`,在ondestroy销毁方法中销毁,并且销毁前也需要对data对象中的数据进行清除并且销毁：

	public class MyActivity extends Activity{
	
		private Data data;
	
		@Override
		protected void oncreate(){
			data = new Data();
			//UI更新
			...
		}
	
		@Override
		protected void onDestroy() {
		  data.clear();
		  data = null;
		  super.onDestroy();
		}
	}

	public class Data{

		private Item item;
		private Map maps = new HashMap();
		...
	
		public void clear(){
			item.clear;
			maps.removeall();
			maps = null;
			item = null;
		}

	}

以上（手敲）当然还有在onStart、onStop等待生命周期也可以做响应的操作，这样做是防止内存泄漏非常完美的方式：**数据随着当前组件声明周期的变化而变化，手动进行清理并且置为null。**

每个Activity都应该按照这种规则去实现，**规则：Activity中定义全局变量Data，oncreate对Data赋值并且更新到UI，ondestroy销毁Data**。

# 迭代 #

完美的代码不是一次性完成的，而是根据实际需求不断迭代，最终形成符合当前业务逻辑的代码。

根据当前业务逻辑去设计合理的架构，当当前架构不满足实际需求时，在对新需求提出一个更加合理的设计思路。这就是为什么说**思路比实现更重要，设计比代码更巧妙。**

## 迭代一 ##

我们是否可进一步迭代，**如何迭代**（方法千千万，选择一种即可，不纠结）：
**每个Activity中都会有data数据（即使有多个data，我们也可以封装成给data），data赋值以及更新到UI，data销毁；**

### 思路和设计 ###

思路：我们把data数据的赋值和销毁放在一起统一处理，赋值的同时更新UI。

设计： 定义一个BaseActivity作为Activity的基类
（1）定义一个T data全局变量；
（2）定义一个赋值方法，对当前data赋值；
（3）（java接口设计模式，非常常用的一种模式）定义更新ui方法；并且当前方法在赋值方法中调用；
（4）在Activity调用ondestroy方法是销毁当前data变量；
（5）定义一个DataClear接口，当前接口定义一个clear方法，目的在于如果data是一个比较复杂的数据类型，可以继承该接口并且在Activity对data销毁前对data对象中的数据清理。


### 实现代码 ###

根据以上迭代一实现的代码如下：


各个Activity抽离生成BaseActivity类：

	/**
	 * Copyright (C), 2019-2022, 佛生
	 * FileName: BaseActivity
	 * Author: 佛学徒
	 * Date: 2022/6/6 10:41
	 * Description:
	 * History:
	 */
	public abstract class BaseActivity<T> extends Activity {
	
	    T liveData;
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	
	    }
	
	    protected void setValue(T t) {
	        this.liveData = t;
	        updateUI(t);
	    }
	
	    protected abstract void updateUI(T t);
	
	    @Override
	    protected void onDestroy() {
	        if (liveData != null) {
	
	            if (liveData instanceof DataClear) {
	                ((DataClear) liveData).clearData();
	            }
	
	            liveData = null;
	        }
	
	        super.onDestroy();
	    }
	}


这里会定义一个DataClear接口，目的是如果Data中需要对内部进行清理，实现该接口，并且在clearData方法中实现清理工作。

	public interface DataClear {
	
	    public void clearData();
	}


嗯！本人对以上自己的设计思路非常满意。

满意归满意，问题还得解决，以上针对比较简单的页面基本可以满足需求，但是：

1. 如果页面是一个Fragment咋办（在BaseFragment再实现一次呗！）？

2. 如果页面功能比较复杂，有多个data类型咋办？（我可以把多个data类型封装成一个在这么去操作，如果是个别可以这么做，但是复杂页面较多还是建议对单个data类型进行处理）；

3. BaseActivity或BaseFragment肯定还有其他的一些实现，我们如果就这么不负责任的把data放里面：①里面的东西越来越多，代码显得非常松散；②我们完全可以使用组件化开发将data当做一个组件来设计。


## 迭代二 ##

将activity/fragment中的data数据做出一个组件。可以适用于不同场景。

### 思路与设计 ###

思路：在《迭代一》思路的基础上我们来设想，我们使用LiveData< T>来处理data数据，核心是传递activity/fragment的生命周期，观察在非ondestroy生命周期下可以做赋值和ui更新，在ondestroy生命下对data清理。

架构设计：
（1）数据使用LiveData< T>对数据data赋值，销毁；
（2）定义表示activity/fragment生命周期的枚举事件；
（3）定义一个观察者接口，观察如果数据在非ondestroy生命下发生了变化，回调更新UI；
（4）传递activity/fragment实例监听生命周期：非ondestroy下才允许data赋值；ondestroy下data销毁，传递观察者实例当前数据发生变化时回调更新UI。


### 代码实现 ###

1. 数据使用LiveData< T>对数据data赋值，销毁；


		public class LiveData<T> {
		
		    T data;
		
		    public LiveData(T data) {
		        checkValue();
		        this.data = data;
		    }
		
		    public LiveData() {
		    }
		
		    public void setValue(T t) {
		        checkValue();
		        data = t;
		    }
		
		    public T getValue() {
		
		        checkValue();
		        return data;
		    }
		
		    public void clear() {
		        checkValue();
		        if (data instanceof DataClear)
		            ((DataClear) data).clearData();
		        data = null;
		    }
		
		    public void checkValue() {
		        if (data == null) {
		            throw new NullPointerException("data必须赋值，要么通过LiveData构造函数传值，要么通过setValue方法赋值");
		        }
		    }
		}

2. 定义表示activity/fragment生命周期的枚举事件；

		public enum Event {
		    ON_CREATE,
		  
		    ON_START,
		   
		    ON_RESUME,
		    
		    ON_PAUSE,
		   
		    ON_STOP,
		    
		    ON_DESTROY
		}

3. 定义一个观察者接口，观察如果数据在非ondestroy生命下发生了变化，回调更新UI；

		public interface Observer<T> {
		    public void onChange(T t);
		}

4. 传递activity/fragment实例监听生命周期：非ondestroy下才允许data赋值，ondestroy下data销毁，传递观察者实例当前数据发生变化时回调更新UI。


		public class LiveData<T> {

		    private T data;
		    private List<Observer<T>> observers = new ArrayList<>();
		    private WeakReference<Activity> weakReference;
		
		    private int START_VERSION = -1;
		
		    private int mVersion = START_VERSION;
		
		    //防止重复操作
		    private boolean dispatchValue;
		
		    public LiveData(T data) {
		        checkValue(data, "data数据必须赋值！");
		        this.data = data;
		    }
		
		    public LiveData() {
		    }
		
		    public void setValue(T t) {
		        checkValue(data, "data数据必须赋值！");
		        data = t;
		        mVersion++;
		        dispatch(Event.ON_CREATE, null);
		
		    }
		
		    @TargetApi(Build.VERSION_CODES.Q)
		    public void observe(Activity activity, final Observer<T> observer) {
		        checkValue(activity, "activity不允许为null");
		        checkValue(observer, "observer观察者不允许为null");
		        this.observers.add(observer);
		
		        WeakReference wrActivity = new WeakReference<>(activity);
		
		        if (weakReference != null) {
		            //比较两个对象的应用地址判断是否同一个实例化对象：一个生命周期拥有者可以监听多个LiveData，一个LiveData可以被一个生命周期拥有者监听多次；但是多个生命周期拥有者不能监听同一个LiveData；
		            if (!wrActivity.get().toString() .eqauls( weakReference.get().toString())) {
		                throw new IllegalArgumentException("一个生命周期拥有者可以监听多个LiveData，一个LiveData可以被一个生命周期拥有者监听多次；但是多个生命周期拥有者不能监听同一个LiveData；");
		            }
		        }
		
		        if (weakReference == null)
		            this.weakReference = wrActivity;
		
		        if (weakReference.get() != null) {
		            weakReference.get().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
		                @Override
		                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
		                    dispatch(Event.ON_CREATE, observer);
		                }
		
		                @Override
		                public void onActivityStarted(Activity activity) {
		                    dispatch(Event.ON_START, observer);
		                }
		
		                @Override
		                public void onActivityResumed(Activity activity) {
		                    dispatch(Event.ON_RESUME, observer);
		                }
		
		                @Override
		                public void onActivityPaused(Activity activity) {
		                    dispatch(Event.ON_PAUSE, observer);
		                }
		
		                @Override
		                public void onActivityStopped(Activity activity) {
		                    dispatch(Event.ON_STOP, observer);
		                }
		
		                @Override
		                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
		                }
		
		                @Override
		                public void onActivityDestroyed(Activity activity) {
		                    dispatch(Event.ON_DESTROY, observer);
		                }
		            });
		        }
		    }
		
		    private void dispatch(Event event, Observer observer) {
		
		        if (event.equals(Event.ON_DESTROY)) {
		            clear();
		            return;
		        }
		
		        //表示数据没有更新，则不需要做观察者回调
		        if (mVersion == START_VERSION) {
		            return;
		        }
		
		        if (observer != null) {
		            observer.onChange(data);
		            return;
		        }
		
		        if (dispatchValue) {
		            return;
		        }
		
		        dispatchValue = true;
		        for (Observer<T> obs : observers) {
		            obs.onChange(data);
		        }
		
		        dispatchValue = false;
		    }
		
		    public T getValue() {
		
		        checkValue(data, "data数据必须赋值！");
		        return data;
		    }
		
		    public void clear() {
		        checkValue(data, "data数据必须赋值！");
		
		        observers.clear();
		
		        if (data instanceof DataClear)
		            ((DataClear) data).clearData();
		        data = null;
		
		        weakReference = null;
		    }
		
		    public void checkValue(Object obj, String msg) {
		        if (obj == null) {
		            if (msg == null)
		                msg = obj.getClass() + "对象必须赋值！";
		            throw new NullPointerException();
		        }
		    }
		}

以上代码存在两个问题：

（1）问题1：Activity的registerActivityLifecycleCallbacks方法只有在我们的手机系统api>=29时才存在，否则该方法是调用不了的；这个问题非常严重，比如我的手机就是28的；
（2）问题2：Activity生命周期每发生一次变化就会所有观察者都会被执行一次（我们的要求应该是观察者们在数据更新后非ON_DESTORY事件下执行一次即可）；

### 解决行问题 ###

**问题1：Activity的registerActivityLifecycleCallbacks方法只有在我们的手机系统api>=29时才存在，否则该方法是调用不了的；这个问题非常严重，比如我的手机就是28的**

手机系统>=29是采用Activity的registerActivityLifecycleCallbacks方法监听Activity生命周期；如果小于29，那么我们可以创建一个ReportFragment通过ReportFragment的生命周期变化告诉我们Activity的生命周期。如下：

	/**
	 * Copyright (C), 2019-2022, 佛生
	 * FileName: ReportFragment
	 * Author: 佛学徒
	 * Date: 2022/6/8 8:57
	 * Description:使用一个Fragment来监听activity的声明周期
	 * History:
	 */
	public class ReportFragment extends Fragment {
	
	    public static final String REPORT_FRAGMENT_TAG = "androidx.lifecycle"
	            + ".LifecycleDispatcher.report_fragment_tag";
	    private LiveData liveData;
	
	    public void setLiveData(LiveData liveData) {
	        this.liveData = liveData;
	    }
	
	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        super.onActivityCreated(savedInstanceState);
	
	        dispatch(Event.ON_CREATE);
	    }
	
	    @Override
	    public void onStart() {
	        super.onStart();
	
	        dispatch(Event.ON_START);
	    }
	
	    @Override
	    public void onResume() {
	        super.onResume();
	
	        dispatch(Event.ON_RESUME);
	    }
	
	    @Override
	    public void onPause() {
	        super.onPause();
	        dispatch(Event.ON_PAUSE);
	    }
	
	    @Override
	    public void onStop() {
	        super.onStop();
	        dispatch(Event.ON_STOP);
	    }
	
	    @Override
	    public void onDestroy() {
	        super.onDestroy();
	        dispatch(Event.ON_DESTROY);
	        // just want to be sure that we won't leak reference to an activity
	
	    }
	
	    private void dispatch(Event event) {
	        liveData.dispatch(event);
	    }
	}

在liveData类中：

	public void observe(Activity activity, final Observer<T> observer) {
	        checkValue(activity, "activity不允许为null");
	        checkValue(observer, "observer观察者不允许为null");
	        this.observers.add(observer);
	
	        WeakReference wrActivity = new WeakReference<>(activity);
	
	        if (weakReference != null) {
	            //比较两个对象的应用地址判断是否同一个实例化对象：一个生命周期拥有者可以监听多个LiveData，一个LiveData可以被一个生命周期拥有者监听多次；但是多个生命周期拥有者不能监听同一个LiveData；
	            if (!wrActivity.get().toString() .equals(weakReference.get().toString()) ) {
	                throw new IllegalArgumentException("一个生命周期拥有者可以监听多个LiveData，一个LiveData可以被一个生命周期拥有者监听多次；但是多个生命周期拥有者不能监听同一个LiveData；");
	            }
	
	            return;
	        }
	
	        this.weakReference = wrActivity;
	
	        lifecycleCallback();
	
	    }

	private void lifecycleCallback() {

        if (weakReference.get() != null) {
            //允许设备api >=29才可以使用当前方法
            if (Build.VERSION.SDK_INT >= 29) {
                weakReference.get().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                        dispatch(Event.ON_CREATE);
                    }

                    @Override
                    public void onActivityStarted(Activity activity) {
                        dispatch(Event.ON_START);
                    }

                    @Override
                    public void onActivityResumed(Activity activity) {
                        dispatch(Event.ON_RESUME);
                    }

                    @Override
                    public void onActivityPaused(Activity activity) {
                        dispatch(Event.ON_PAUSE);
                    }

                    @Override
                    public void onActivityStopped(Activity activity) {
                        dispatch(Event.ON_STOP);
                    }

                    @Override
                    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                    }

                    @Override
                    public void onActivityDestroyed(Activity activity) {
                        dispatch(Event.ON_DESTROY);
                    }
                });
            } else {
                injectIfNeededIn(weakReference.get());
            }

        }
    }

    private void injectIfNeededIn(Activity activity) {
        // Prior to API 29 and to maintain compatibility with older versions of
        // ProcessLifecycleOwner (which may not be updated when lifecycle-runtime is updated and
        // need to support activities that don't extend from FragmentActivity from support lib),
        // use a framework fragment to get the correct timing of Lifecycle events
        android.app.FragmentManager manager = activity.getFragmentManager();

        ReportFragment reportFragment = new ReportFragment();
        reportFragment.setLiveData(this);
        if (manager.findFragmentByTag(ReportFragment.REPORT_FRAGMENT_TAG) == null) {
            manager.beginTransaction().add(reportFragment, ReportFragment.REPORT_FRAGMENT_TAG).commit();
            // Hopefully, we are the first to make a transaction.
            manager.executePendingTransactions();
        }
    }

**问题2：Activity生命周期每发生一次变化就会所有观察者都会被执行一次（我们的要求应该是观察者们在数据更新后非ON_DESTORY事件下执行一次即可）；**

**解决方案**：我们给每个观察者添加一个版本属性（该版本肯定不会高于LiveData版本），和当前LiveData版本比较，如果版本 = LiveData版本说明观察者已经观察到最新数据，反之表示没有更新到最新数据进行更新。

非常完美的解决方案！！！代码如下：

    private class ObserverWrapper<T> {
        public Observer<T> observer;
        public int mVersion = START_VERSION;

        public ObserverWrapper(Observer observer) {
            this.observer = observer;
        }
    }

LiveData类代码随之更新，不在这里说明，自行在[AAC-core-demo项目](https://github.com/hellogaod/aac/tree/master/AAC-core-demo)的第一次LiveData完美针对Activity完美迭代标签下去看。

> 注：`第一次LiveData完美针对Activity完美迭代`标签下，别搞混淆了。

当前LiveData仅仅是针对Activity的使用，还有Fragment中如果要使用LiveData呢，并且如果是MVVM模式，那么我们是否对ViewModel同样有生命周期概念。

# 总结 #

以上代码虽然参考的是Lifecycle源码，但是融入了个人的架构思想，也体现个人架构设计层面的不足。

从架构角度去编写代码，感觉对自己的提升非常大。还是那句话：**思路比实现更重要，设计比代码更巧妙。**

下面会从架构角度来分析Lifecycle(Lifecycle,livedata,viemodel)源码。