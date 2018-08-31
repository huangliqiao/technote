# Method.invoke比普通方法调用慢？

本文主要是针对“Method.invoke比普通方法调用慢？”这个问题的一次探索，主要记录研究过程和相关技术细节。

### 【问题分析】

先不管JAVA API层面的因素，两种调用最终体现到JVM中都是字节码，那第一个问题应该先要弄清楚普通方法在JVM中是如何分派的？这里只讨论invokevirtual和invokeinterface两种情况（invokespecial、invokestatic最简单，invokedynamic和主题无关）。在弄清第一个问题的基础上再比较Method.invoke和普通方法调用在字节执行上的区别，然后再从Java API层面入手比较两者的具体区别（这里涉及到各种安全检查，类型检查，MethodAccessor、native方法和Java方法切换等），结合前面的分析假设再借助工具做测试验证，最后看能否得出比较合理的解释。【路线图】本次探索的路线图大致整理如下：![](https://cf.jd.com/download/attachments/103543069/image2018-3-1%2017%3A27%3A40.png?version=1&modificationDate=1519896645000&api=v2)

相关资料索引整理如下：

1、动态方法的分派机制

Virtual method table: [https://en.wikipedia.org/wiki/Virtual\_method\_table](https://en.wikipedia.org/wiki/Virtual_method_table)

[s](https://en.wikipedia.org/wiki/Virtual_method_table)tackoverflow 上关于hotspot实现invokeinterface的一个回答：[https://stackoverflow.com/questions/4423968/how-are-java-interfaces-implemented-internally-vtables](https://stackoverflow.com/questions/4423968/how-are-java-interfaces-implemented-internally-vtables)

Jalapeno VM实现interface dispatch的论文：[http://www.research.ibm.com/people/d/dgrove/papers/oopsla01.pdf](http://www.research.ibm.com/people/d/dgrove/papers/oopsla01.pdf)

各种常见动态方法分派的机制：![](https://cf.jd.com/plugins/servlet/view-file-macro/placeholder?type=PowerPoint+Presentation&name=Interface+Method+Dispatch.ppt&attachmentId=103543013&version=1&mimeType=application%2Fvnd.ms-powerpoint&height=250&thumbnailStatus=415)

什么是inline caching? [https://en.wikipedia.org/wiki/Inline\_caching](https://en.wikipedia.org/wiki/Inline_caching)

HotSpot中inline caching的实现：Call-site caching  [https://blog.h2o.ai/2010/04/inline-caches-and-call-site-optimization/](https://blog.h2o.ai/2010/04/inline-caches-and-call-site-optimization/)

OpenJDK官网关于调优技术的索引（查看Methods一节）：[https://wiki.openjdk.java.net/display/HotSpot/PerformanceTechniques](https://wiki.openjdk.java.net/display/HotSpot/PerformanceTechniques)

2、字节码执行分析字节码中的符号引用如何被解析为直接引用？可以参考这个帖子：[https://www.zhihu.com/question/30300585?sort=created](https://www.zhihu.com/question/30300585?sort=created)

但这里要注意下回答中的这个部分，见截图：

![](https://cf.jd.com/download/attachments/103543069/image2018-3-1%2017%3A29%3A19.png?version=1&modificationDate=1519896645000&api=v2)

这里有点问题，可以假设这样一段代码：

![](https://cf.jd.com/download/attachments/103543069/image2018-3-1%2017%3A29%3A38.png?version=1&modificationDate=1519896645000&api=v2)

ClassC和ClassD都继承自ClassA，如果在执行第一个invokevirtual的时候把常量池中\#22偏移量的内容替换成ClassC的methodblock\*, 那第二个invokevirtual就无法正确执行了。所以常量池\#22和操作码后面的2字节内容所代表的直接引用，都应该是vtable的偏移量，这里是利用了继承的单一性和vtable前置加载父类方法的特性，来达到不同子类调用相同父类方法的时候，该方法在vtable偏移量相同的目的。但是invokeinterface由于接口的多态性，如果只采用vtable方式，无法在相同接口的不同实现中固定接口方法的偏移量，在无法解析invokeinterface为直接引用的情况下，只能每次调用都重新解析，这显然是不能接受的。所以才有了后来的itable、SIT等机制。

3、Java API调用链分析

    TBC

4、测试/验证方法

    TBC

