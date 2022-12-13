# 前言 #

>2018年面试华米，当时人家问我可知道aac，或者对它涉及的源码有一定了解，因为对方的一句话灰溜溜的走了~

来说说aac（android architecture component）和androidx、jetpack的关系。

以下抄袭！！完完全全的抄袭，[来源](https://blog.csdn.net/vitaviva/article/details/104139034)：

google公司一开始推出的就是android architecture component，用于给开发者提供合理的架构，核心的三大组件lifecycle、lievedata和viewmodel。在2018年google I/O开发者年会首次提出了androidx，androidx是基于aac和一些基础工具并且独立于androidsdk的一个库类。androdx对外的品牌名称是jetpack。

**目的：旨在指导开发者构建出架构合理的Android 应用。**

# AAC学习梳理 #

[aac demo源码](https://github.com/android/architecture-components-samples)大家可以自行下载查看，我这里主要对aac demo理解以及涉及源码和设计模式还有更生层次的androidx源码系统来学习，但是学习需要有一个好的思路（我这里的思路不一定是好的，但是必须要有）：

1. 把当前aac涉及到的几个demo运行并且有个基本的认识，代码层面能理解多少理解多少；

 - 当前[github上的源码](https://github.com/hellogaod/aac)就是可以运行的，可自行下载去运行并且简单理解

2. aac核心组件：Lifecycle、LiveData和ViewModel源码理解；

3. aac demo 一个个去理解，具体思路如下：

 - （1）几个demo尽量从Lifecycle、LiveData和ViewModel入手，因为2已经理解过了，代入感更强；

 - （2）aac的组织架构、设计模式简单理解；

 - （3）设计到的aac源码理解并且扣到demo中（比较麻烦）

4. androidx在本地调试（ubuntu系统上运行）；

5. androidx源码系列理解；

根据以上步骤去学习androidx源码，说的好像挺轻巧！！！下面就等着呵呵吧。

# 结束 #

QQ学习交流群：575306647


1. [lifecycle系列文章](https://github.com/hellogaod/aac/tree/master/%E4%BB%8E%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1%E8%A7%92%E5%BA%A6%E5%88%86%E6%9E%90aac%E7%B3%BB%E5%88%97%E6%BA%90%E7%A0%81-%E6%96%87%E7%AB%A0%E7%B3%BB%E5%88%97/1.lifecycle%E7%B3%BB%E5%88%97)

2. [rxjava2系列文章](https://github.com/hellogaod/aac/tree/master/%E4%BB%8E%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1%E8%A7%92%E5%BA%A6%E5%88%86%E6%9E%90aac%E7%B3%BB%E5%88%97%E6%BA%90%E7%A0%81-%E6%96%87%E7%AB%A0%E7%B3%BB%E5%88%97/2.rxjava2%E7%B3%BB%E5%88%97)

3. [room系列文章](https://github.com/hellogaod/aac/tree/master/%E4%BB%8E%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1%E8%A7%92%E5%BA%A6%E5%88%86%E6%9E%90aac%E7%B3%BB%E5%88%97%E6%BA%90%E7%A0%81-%E6%96%87%E7%AB%A0%E7%B3%BB%E5%88%97/3.room%E7%B3%BB%E5%88%97)

