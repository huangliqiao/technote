---
description: 'code coverage, jacoco, jenkins'
---

# Jacoco+Jenkins实现代码覆盖率实时统计

### 配置过程分为两步： {#Jacoco+Jenkins实现代码覆盖率实时统计-配置过程分为两步：}

* 被测应用启动Tomcat时添加javaagent参数，通过jacoccoagent.jar生成覆盖率统计文件
* 远程获取被测应用的覆盖率统计文件，生成report  

### 步骤一：被测应用启动Tomcat时添加javaagent参数 {#Jacoco+Jenkins实现代码覆盖率实时统计-步骤一：被测应用启动Tomcat时添加javaagent参数}

* 下载[Jacoco-0.8.0.zip](http://search.maven.org/remotecontent?filepath=org/jacoco/jacoco/0.8.0/jacoco-0.8.0.zip)，  解压后把 lib/jacocoagent.jar，放入被测应用所在服务器上：/export/software/jacocoagent.jar
* 修改被测应用所属Tomcat的启动脚本，如下：

![](https://cf.jd.com/download/attachments/109442784/image2018-3-14%2018%3A50%3A52.png?version=1&modificationDate=1527649989000&api=v2)

参数含义参见[这里](http://www.jacoco.org/jacoco/trunk/doc/agent.html)，port的值需要记下，远程拉取统计文件的时候要用。

（注意：实际运行中最好不要改start.sh，而是在Jenkins的脚本中通过参数化来控制JAVA\_OPTS的值）

### 步骤二：远程获取被测应用的覆盖率统计文件，生成report {#Jacoco+Jenkins实现代码覆盖率实时统计-步骤二：远程获取被测应用的覆盖率统计文件，生成report}

* 修改被测应用代码中的pom.xml文件，添加jacoco-maven-plugin，如下：

```markup
<properties>
    <jacoco.skip>true</jacoco.skip>
</properties>
<build>
        <plugins>
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.7.9</version>
                <configuration>
                    <address>127.0.0.1</address> <!-- 用于拉取统计文件的Jenkins job运行在应用所在服务器，所以填回环地址 -->
                    <destFile>${project.build.directory}/jacoco.exec</destFile> <!-- 拉取的统计文件存放路径 -->
                    <port>1235</port> <!-- jacocoagent的启动端口 -->
                    <reset>false</reset>  <!-- false:全量拉取，true:增量拉取 -->
                    <append>true</append> <!-- 如果本地已经存在jacoco.exec统计文件，则追加到文件尾部 -->
                </configuration>
                <executions>
                    <execution>
                        <phase>test</phase> <!-- 在maven build过程中的test阶段执行dump -->
                        <goals>
                            <goal>dump</goal>  <!-- 用于拉取数据的goal -->
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```



* Jenkins中先安装Jacoco插件，然后创建Jenkins Job, 如下图： ![](https://cf.jd.com/download/attachments/109442784/image2018-3-14%2019%3A15%3A23.png?version=1&modificationDate=1527649989000&api=v2) 因为pom.xml中默认jacoco.skip=true，这里需要设置为false，maven build过程才会运行jacoco-maven-plugin的配置 Maven中的phase定义查看[这里](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)，clean后的**test**是指本次build过程中运行test以及test之前的所有阶段。 这里解释下为何要配置成test，而不是package，install等。因为jacoco-maven-plugin拉取统计文件后，需要基于类文件进行分析，所以maven build至少要覆盖到compile。并且没必要进行package或install阶段，所以配成test就行了。  ![](https://cf.jd.com/download/attachments/109442784/image2018-3-14%2019%3A26%3A4.png?version=1&modificationDate=1527649989000&api=v2) 试运行的话，用默认配置就好，实际运行中最好精确一下inclusion和exclusion的范围。  
* 运行成功的日志截图

> ```text
> [[1;34mINFO[m] Connecting to /127.0.0.1:1235
> [[1;34mINFO[m] Dumping execution data to /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-web/target/jacoco.exec
> (..............)
> [[1;34mINFO[m] jshop-operations ................................... [1;32mSUCCESS[m [  0.155 s]
> [[1;34mINFO[m] operations-model ................................... [1;32mSUCCESS[m [  3.856 s]
> [[1;34mINFO[m] operations-jsf-open ................................ [1;32mSUCCESS[m [  0.497 s]
> [[1;34mINFO[m] operations-common .................................. [1;32mSUCCESS[m [  2.391 s]
> [[1;34mINFO[m] operations-dao ..................................... [1;32mSUCCESS[m [  1.249 s]
> [[1;34mINFO[m] operations-manager ................................. [1;32mSUCCESS[m [  0.701 s]
> [[1;34mINFO[m] operations-service ................................. [1;32mSUCCESS[m [  2.367 s]
> [[1;34mINFO[m] operations-web ..................................... [1;32mSUCCESS[m [  6.104 s]
> [[1;34mINFO[m] operations-jsf-web ................................. [1;32mSUCCESS[m [  0.683 s]
> [[1;34mINFO[m] [1m------------------------------------------------------------------------[m
> [[1;34mINFO[m] [1;32mBUILD SUCCESS[m
> [[1;34mINFO[m] [1m------------------------------------------------------------------------[m
> [[1;34mINFO[m] Total time: 18.673 s
> [[1;34mINFO[m] Finished at: 2018-03-14T11:01:55+08:00
> [[1;34mINFO[m] Final Memory: 81M/658M
> [[1;34mINFO[m] [1m------------------------------------------------------------------------[m
> [JaCoCo plugin] Collecting JaCoCo coverage data...
> [JaCoCo plugin] **/**.exec;**/classes;**/src/main/java; locations are configured
> [JaCoCo plugin] Number of found exec files for pattern **/**.exec: 1
> [JaCoCo plugin] Saving matched execfiles:  /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-web/target/jacoco.exec
> [JaCoCo plugin] Saving matched class directories for class-pattern: **/classes: 
> [JaCoCo plugin]  - /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-common/target/classes 82 files
> [JaCoCo plugin]  - /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-dao/target/classes 10 files
> [JaCoCo plugin]  - /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-jsf-open/target/classes 7 files
> [JaCoCo plugin]  - /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-jsf-web/target/classes 5 files
> [JaCoCo plugin]  - /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-manager/target/classes 13 files
> [JaCoCo plugin]  - /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-model/target/classes 47 files
> [JaCoCo plugin]  - /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-service/target/classes 66 files
> [JaCoCo plugin]  - /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-web/target/classes 21 files
> [JaCoCo plugin] Saving matched source directories for source-pattern: **/src/main/java: 
> [JaCoCo plugin] - /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-common/src/main/java 52 files
> [JaCoCo plugin] - /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-dao/src/main/java 10 files
> [JaCoCo plugin] - /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-jsf-open/src/main/java 7 files
> [JaCoCo plugin] - /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-jsf-web/src/main/java 5 files
> [JaCoCo plugin] - /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-manager/src/main/java 11 files
> [JaCoCo plugin] - /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-model/src/main/java 42 files
> [JaCoCo plugin] - /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-service/src/main/java 62 files
> [JaCoCo plugin] - /export/servers/.jenkins/workspace/jshop-operation-coverage/operations-web/src/main/java 20 files
> [JaCoCo plugin] Loading inclusions files..
> [JaCoCo plugin] inclusions: []
> [JaCoCo plugin] exclusions: []
> [JaCoCo plugin] Thresholds: JacocoHealthReportThresholds [minClass=0, maxClass=0, minMethod=0, maxMethod=0, minLine=0, maxLine=0, minBranch=0, maxBranch=0, minInstruction=0, maxInstruction=0, minComplexity=0, maxComplexity=0]
> [JaCoCo plugin] Publishing the results..
> [JaCoCo plugin] Loading packages..
> [JaCoCo plugin] Done.
> [JaCoCo plugin] Overall coverage: class: 46, method: 17, line: 13, branch: 3, instruction: 12
> ```



