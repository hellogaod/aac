# 前言 #

本文转自[一文搞懂Android JetPack组件原理之Lifecycle、LiveData、ViewModel与源码分析技巧](https://mp.weixin.qq.com/s/Gy7aXJZJCUzh7OyKegNjxA)。

> 文章写得足够好，就没必要班门弄斧了。如果你感觉对你启发，欢迎去给他个关注。

![在这里插入图片描述](https://img-blog.csdnimg.cn/53879d864b1e498ea9e26fd32520b68d.png)

Lifecycle、LiveData和ViewModel作为AAC架构的核心，常常被用在Android业务架构中。在京东商城Android应用中，为了事件传递等个性化需求，比如ViewModel间通信、ViewModel访问Activity等等，以及为了架构的扩展性，我们封装了BaseLiveData和BaseViewModel等基础组件，也对Activity、Fragement和ViewHolder进行了封装，以JDLifecycleBaseActivity、LifecycleBaseFragment和LifecycleBaseViewHolder等组件强化了View层功能，构建出了各业务线统一规范架构的基石。

![](https://mmbiz.qpic.cn/mmbiz_png/MrFJyDNenF9ia7BeeaGkAp7pmFLib8bx8EBWnnzErx2bicCDDkaxIqcK47qUCmIGcDnI76AjMLyAQbkviaZxqQNvFg/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

在开发过程中，我们有时还会对它们的原理细节有些疑惑，比如会有Lifecycle会不会对性能造成影响、LiveData为什么是粘性事件的、ViewModel的生命周期是什么样的等等问题，这些问题或多或少会对我们的日常开发和协作造成影响，所以对这些组件的源码分析就变得很有必要，而掌握一些分析方法更能提高效率。本文就以Lifecycle、LiveData和ViewModel三大组件的源码分析为例，探讨一下分析方法和技巧，整体目录如下图所示，希望能给大家带来收获。

![](https://mmbiz.qpic.cn/mmbiz_png/MrFJyDNenF9ia7BeeaGkAp7pmFLib8bx8EClK8kXBbMo3OUWDDls45I0yw1BzMfbxnqyq1sQlF1uPfv2lrD7TtXA/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

# 源码下载 #

以下关于androidx源码下载部分我会重新整理。这里的下载会因为墙国的原因应该是不可以的。

官方地址: https://android.googlesource.com/platform/frameworks/support/或Github: https://github.com/androidx/androidx，以上都列出了比较详细的下载步骤和编译方法，按步骤操作一般没多大问题，如果已经安装过repo工具，可以跳过第一步。

1. 安装repo

		mkdir ~/bin
		
		PATH=~/bin:$PATH
		curl https://storage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
		chmod a+x ~/bin/repo

2. 配置git中的姓名和邮箱，已配置可忽略


		git config --global user.name "Your Name"
		
		git config --global user.email "you@example.com"

3. 创建一个文件夹存放要下载的源码


		mkdir androidx-master-dev
		cd androidx-master-dev

4. 使用repo命令下载源码仓库

		repo init -u https://android.googlesource.com/platform/manifest -b androidx-master-dev
		repo sync -j8 -c

5. 使用命令以Android Studio打开源码库


		cd androidx-master-dev/frameworks/support/
		./studiow

第一次打开可能会自动下载一个最新的Android Studio，稍等一会儿，就可以看到熟悉的界面了。

![](https://mmbiz.qpic.cn/mmbiz_png/MrFJyDNenF9ia7BeeaGkAp7pmFLib8bx8EeyoZYl1GcBuUweg5ibvUGOObXKAwCY5NJhhrZvggX7Zg5GOYhjpxPrQ/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

如图，Lifecycle、LiveData和ViewModel三大组件的代码都在lifecycle包下。

# 组件介绍 #

为了对分析工作有个感性认识，先过一下各个组件的作用和简单用法。

本篇中可以去对照`AAC-core-demo`demo理解Lifecycle、LiveData、ViewModel。

## Lifecycle ##

