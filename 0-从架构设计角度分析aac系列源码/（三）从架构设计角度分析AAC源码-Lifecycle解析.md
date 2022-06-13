# 前言 #

前一章我们的LiveData中做了对Activity生命周期变化监听，但是实际情况下生命周期拥有者监听生命周期变化应该提炼出来，因为不仅仅对Data数据，其他情况下也可以根据生命周期变化做响应的操作，例如ViewModel、执行定期任务等。

这也是为什么将**Activity/Fragment的生命周期**单独做成一个组件的原因。

另外，Fragment的生命周期随着Activity的变化而变化，Fragment必须依附于Activity，所以，我们完全可以通过Fragment的getActivity监听Fragment所在Activity生命周期变化掌握当前Fragment的生命周期。换句话说，**无论是Activity还是Fragment，我们只需要将Activity生命周期做成组件即可**。

# 思路和架构设计 #

## 思路 ##

思路：定义一个(或多个)**观察者**，在**生命周期组件**中**观察**Activity（**Activity是生命周期拥有者**）生命的变化。


## 设计 ##

根据设计思路，我们定义：**观察者**，**生命周期组件**，**生命周期拥有者**，**观察生命周期变化**。

### 1. 观察者 ###

 定义生命周期观察者：LifecycleObserver接口，用于观察生命周期拥有者的生命周期变化。

		public interface LifecycyleObserver {}

	
		//定义一个观察生命周期拥有者全部生命的接口
		public interface FullLifecycleObserver extends LifecycleObserver{

			    void onCreate(LifecycleOwner owner);

			    void onStart(LifecycleOwner owner);
			
			    void onResume(LifecycleOwner owner);
			
			    void onPause(LifecycleOwner owner);
			
			    void onStop(LifecycleOwner owner);
			
			    void onDestroy(LifecycleOwner owner);

		}


### 2. 生命周期组件 ###

生命周期组件核心，Lifecycle。

主要三个作用：（1）感知生命周期拥有者生命周期的变化；（2）增删生命周期观察者；（3）生命周期的变化**传递**给生命周期观察者；

 - 个人比较满意上面的描述,"传递"这个词用得好！

（1）感知生命周期拥有者生命周期的变化，来给一个google提供的图片

![](https://mmbiz.qpic.cn/mmbiz_png/MrFJyDNenF9ia7BeeaGkAp7pmFLib8bx8ENIUUvaym6MAbOIibQC82cqXbFuwHgojeVdEAsRZDITdEzplM0007DVw/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)


为了方便下面的理解，我对以上图形做了一些局部调整：

>主要将states的INITIALIZED和DESTROYED对调！

![在这里插入图片描述](https://img-blog.csdnimg.cn/3a0210f9f7164649a6678fd7e0108e66.png)

>花了我半小时，改的丑陋无比，但是胜在实用！！！

解释一波，如下：

Events事件表示生命周期拥有者不同生命周期：

	public enum Event {
	       
        ON_CREATE,
       
        ON_START,
       
        ON_RESUME,
       
        ON_PAUSE,
       
        ON_STOP,
       
        ON_DESTROY
    }

State表示生命周期事件转换成当前生命周期组件状态，转换关系已表现在图上

	public enum State {
		DESTROYED，
		INITIALIZED，
		CREATED，
		STARTED，
		RESUMED，
	}

我改google原有的图片目的是对照源码中的Event和States枚举顺序，枚举使用compareTo方法比较的是枚举中item的下标。

案例如下：

	public enum Event {
	       
	        ON_CREATE,
	       
	        ON_START,
	       
	        ON_RESUME,
	       
	        ON_PAUSE,
	       
	        ON_STOP,
	       
	        ON_DESTROY
	    }

	private Event mEvent = Event.ON_START;

	//表示mEvent当前表示的枚举大于Event.ON_CREATE；
	mEvent.compareTo(Event.ON_CREATE) > 0

	//表示mEvent当前表示的枚举小于Event.ON_RESUME；
	mEvent.compareTo(Event.ON_RESUME) > 0



（2）增删生命周期观察者；

	public abstract class Lifecycle {
	 	@MainThread
	    public abstract void addObserver(@NonNull LifecycleObserver observer);
	
		@MainThread
	    public abstract void removeObserver(@NonNull LifecycleObserver observer);
	}


（3）生命周期的变化**传递**给生命周期观察者：新建LifecycleRegistry类，该类继承Lifecycle。LifecycleRegistry的作用是：①注册（或者叫添加）一个LifecycleOwner（生命周期拥有者），②并且接受该LifecycleOwner生命周期拥有者生命事件；③生命周期事件Event转换成State状态；

	//注册LifecycleOwner（生命周期拥有者）
	private final WeakReference<LifecycleOwner> mLifecycleOwner;

	//并且接受该LifecycleOwner生命周期拥有者生命事件；生命周期事件Event转换成State状态
	public void handleLifecycleEvent(@NonNull Lifecycle.Event event) {
        enforceMainThreadIfNeeded("handleLifecycleEvent");
        moveToState(event.getTargetState());
    }


### 3. 生命周期拥有者 ###

生命周期拥有者LifecycleOwner拥有生命周期。


	public interface LifecycleOwner {
	    

	    Lifecycle getLifecycle();
	}

Activity作为生命周期拥有者，继承当前LifecycleOwner接口，并且将当前Activity生命周期拥有者注册到LifecycleRegistry类中：

	public class ComponentActivity extends Activity implements LifecycleOwner {
	
	    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
	
	    @Override
	    protected void onCreate(@Nullable Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        ReportFragment.injectIfNeededIn(this);
	    }
	
	    @NonNull
	    @Override
	    public Lifecycle getLifecycle() {
	        return mLifecycleRegistry;
	    }
	}

生命周期拥有者的生命变化在ReportFragment类中实现。

 - 生命周期变化在当前ComponentActivity实现有个弊端：代码显得太松散。

调用ReportFragment类中的dispatch方法，将当前生命周期拥有者的生命事件传递给LifecycleRegistry类。

	  static void dispatch(@NonNull Activity activity, @NonNull Lifecycle.Event event) {
	        if (activity instanceof LifecycleRegistryOwner) {
	            ((LifecycleRegistryOwner) activity).getLifecycle().handleLifecycleEvent(event);
	            return;
	        }
	
	        if (activity instanceof LifecycleOwner) {
	            Lifecycle lifecycle = ((LifecycleOwner) activity).getLifecycle();
	            if (lifecycle instanceof LifecycleRegistry) {
	                ((LifecycleRegistry) lifecycle).handleLifecycleEvent(event);
	            }
	        }
	    }

### 4. 观察者观察生命周期变化 ###

在我们自己的Activity中观察生命周期的变化：

	getLifecycle().addObserver(new DefaultLifecycleObserver() {
            @Override
            public void onCreate(LifecycleOwner owner) {
                Timber.d("onCreate");
            }

            @Override
            public void onStart(LifecycleOwner owner) {
                Timber.d("onStart");
            }

            @Override
            public void onResume(LifecycleOwner owner) {
                Timber.d("onResume");
            }

            @Override
            public void onPause(LifecycleOwner owner) {
                Timber.d("onPause");
            }

            @Override
            public void onStop(LifecycleOwner owner) {
                Timber.d("onStop");
            }

            @Override
            public void onDestroy(LifecycleOwner owner) {
                Timber.d("onDestroy");
            }
        });

实际调用的是LifecycleResigtry类的addObserver方法，该方法做了3件事情：①将传递来的LifecycleObserver对象参数，转换成ObserverWithState对象，并且收集在Map中；②判断当前传递来的LifecycleObserver对象是否存在于Map中，如果存在，说明之前已经在处理，这里不需要再次处理(体现了代码的细节处理功底)；③观察生命周期变化：到底要不要回调DefaultLifecycleObserver类中的各个生命周期表示的方法。

这里为什么要使用ObserverWithState对象？

这个命名非常规范，表示携带状态的观察者。因为LifecycleRecycle的mState状态，表示注册进来的LifecycleOwner生命周期拥有者的状态，那么每一个观察者添加到LifecycleRegistry类中携带一个状态，和生命周期拥有者状态比较决定当前观察者需要调用哪些回调。

![在这里插入图片描述](https://img-blog.csdnimg.cn/3a0210f9f7164649a6678fd7e0108e66.png)


# 总结 #

上面的描述不尽如意，总感觉少了点啥，这就是我真实的水平了~后面源码学习还有很长的路要走。并没有灰心，一定要学会从架构设计的角度学习源码同时不要忽视对方源码的细节。以上细节虽然没有描述，但是我已经在[AAC-core-demo项目](https://github.com/hellogaod/aac/tree/master/AAC-core-demo)中都写出来了。

当前Lifecycle组件源码标签：`Lifecycle组件源码`。