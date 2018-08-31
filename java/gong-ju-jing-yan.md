# 工具经验



### JIT相关

* [https://www-356.ibm.com/partnerworld/wps/servlet/download/DownloadServlet?id=Hvdi$ITAyXHiPCA$cnt&attachmentName=IBM\_just\_in\_time\_compiler\_for\_java.pdf&token=MTUyNDg4NDczNzczNA==&locale=en\_ALL\_ZZ](https://www-356.ibm.com/partnerworld/wps/servlet/download/DownloadServlet?id=Hvdi$ITAyXHiPCA$cnt&attachmentName=IBM_just_in_time_compiler_for_java.pdf&token=MTUyNDg4NDczNzczNA==&locale=en_ALL_ZZ)
* [http://people.inf.ethz.ch/zmajo/teaching/cd\_ss17/slides/w15\_01-hotspot-jvm-jit-compilers.pdf](http://people.inf.ethz.ch/zmajo/teaching/cd_ss17/slides/w15_01-hotspot-jvm-jit-compilers.pdf)
* [https://www.safaribooksonline.com/library/view/java-performance-the/9781449363512/ch04.html](https://www.safaribooksonline.com/library/view/java-performance-the/9781449363512/ch04.html)

### Java Mission Control

启动参数：-XX:+UnlockCommercialFeatures -XX:+FlightRecorder

生成日志命令：jcmd ${pid} JFR.start filename=/tmp/perfdata/ztjsf\_all\_profile.jfr duration=10m settings=/export/servers/jdk1.8.0\_20/jre/lib/jfr/profile.jfc compress=true

### JITWatch：

[https://github.com/AdoptOpenJDK/jitwatch/wiki](https://github.com/AdoptOpenJDK/jitwatch/wiki)

