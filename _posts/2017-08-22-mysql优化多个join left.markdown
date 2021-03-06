---
layout:         post
title:          "mysql优化多个join left"
subtitle:       "记录升级过程"
tags:           [nginx,upload-moudle,upload-proccess-module]
keyword:        "nginx"
description:    "在多线程编程中，经常需要将考虑线程之间数据同步的问题，沿着这个思路来整理多线程之间数据同步的问题。"
---

### 什么是数据同步？
1. 首先我们需要理解竞争，什么是竞争？就是某个资源被多方使用。例如停车场上有十辆车，但现在只有一个空停车位？那么这十辆车就是属于竞争关系。数据也是，在多核CPU同时往某个内存地址存储数据，如何有序地写入数据就涉及同步问题。
2. 何为同步？是指多个并发操作变成有序的线性操作。比如CPU0在写入数据时，CPU1就必须在等待CPU0写完之后才能写入数据。
综合上面两点，概括的说就是为了防止多个并发同时操作同一资源，将资源通过某种锁机制圈起来自己单独修改，完成之后再把锁去除。整个过程就是对资源的同步操作。

### 什么是锁


### 如何配置
总体来说配置好项目的pom.xml文件即可，我拿自己的项目来做示例（提供了最基础的功能）。

1. 修改pom.xml文件，创建好项目的描述关系和依赖关系;
2. 生成maven所需要的maven-metadata.xml描述文件;
3. 配置github发插件，自动发布到github;
4. 项目引用。

见如下配置

+ pom.xml

```xml
<!-- 基础信息，项目的唯一标识 -->
<groupId>me.duanyong</groupId>      <!-- 所属组织，一般用域名区分 -->
<artifactId>handswork</artifactId>  <!-- 所属项目，一般用二级域名区分 -->
<version>0.1-SNAPSHOT</version>     <!-- 版本信息，区别不同版本 -->
<packaging>jar</packaging>          <!-- 打包方式：jar、war、ear、pom（没用过ear和pom打包） -->

<modelVersion>4.0.0</modelVersion>
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
        <!-- start 打包使用的插件 -->
        <plugin>
            <artifactId>maven-deploy-plugin</artifactId>
            <version>2.8.1</version>
            <configuration>
                <!-- 将编译之后的文件放到编译目录的repository下 -->
                <altDeploymentRepository>
                    internal.repo::default::file://${project.build.directory}/repository/
                </altDeploymentRepository>
            </configuration>
        </plugin>
        <!-- end 打包使用的插件 -->

        <!-- 重点 *** 将打包之后的jar或war通过github发布插件来处理 *** -->
        <plugin>
            <groupId>com.github.github</groupId>
            <artifactId>site-maven-plugin</artifactId>
            <configuration>
                <message>Generated site for ${project.artifactId} ${project.version}</message>      <!-- git commit message -->
                <noJekyll>true</noJekyll>                                                           <!-- disable webpage processing -->
                <outputDirectory>${project.build.directory}/repository</outputDirectory>            <!-- matches distribution management repository url above -->
                <!-- 由于我们用snapshot或者release的分支来发布，就可以修改下面的分支名称 -->
                <branch>refs/heads/snapshot</branch>                                                <!-- remote branch name -->
                <includes>
                    <include>**/*</include>
                </includes>
                <repositoryName>handswork</repositoryName>      <!-- github上用于发布的仓库 -->
                <repositoryOwner>XXXXXXXX</repositoryOwner>     <!-- github上显示的昵称（不是登录名） -->
            </configuration>
            <executions>
                <!-- 参考 引用 1 -->
                <execution>
                    <goals>
                        <goal>site</goal>
                    </goals>
                    <phase>deploy</phase>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>

<properties>
    <!-- github server corresponds to entry in ~/.m2/settings.xml -->
    <!-- 使用~/.m2/settings.xml文件中的用户名和密码登录github -->
    <github.global.server>github</github.global.server>
</properties>
```

+ ~/.m2/settings.xml

```xml
<settings>
    <servers>
        <server>
            <id>github</id>
            <username>username@gmail.com</username>
            <password>password</password>
        </server>
    </servers>
</settings>
```
    
+ 其它项目依赖上面的项目（在项目的pom.xml中添加）

```xml
<repositories>
    <repository>
        <id>me.duanyong</id>
        <!-- 用户duanyong下handswork仓库的snapshot分支 -->
        <url>https://raw.github.com/duanyong/handswork/snapshot</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>
```
```xml
<dependencies>
    <dependency>
        <groupId>me.duanyong</groupId>
        <artifactId>handswork</artifactId>
        <version>0.1-SNAPSHOT</version>
    </dependency>
</dependencies>
```

### 如何发布
1. 打开IDEA右侧的**"Maven Projects"**项目的LifeCycle（生命周期）
2. 双击LifeCycle的clean，将之前的编译文件全删除
3. 双击LifeCycle的deploy发布。

```
[INFO] --- maven-deploy-plugin:2.8.1:deploy (default-deploy) @ handswork ---
[INFO] Using alternate deployment repository internal.repo::default::file:///Users/PP/workspace/handswork/target/repository/
Downloading: file:///Users/PP/workspace/handswork/target/repository/me/duanyong/handswork/0.1-SNAPSHOT/maven-metadata.xml
Uploading: file:///Users/PP/workspace/handswork/target/repository/me/duanyong/handswork/0.1-SNAPSHOT/handswork-0.1-20161213.153008-1.jar
Uploaded: file:///Users/PP/workspace/handswork/target/repository/me/duanyong/handswork/0.1-SNAPSHOT/handswork-0.1-20161213.153008-1.jar (3 KB at 434.0 KB/sec)
Uploading: file:///Users/PP/workspace/handswork/target/repository/me/duanyong/handswork/0.1-SNAPSHOT/handswork-0.1-20161213.153008-1.pom
Uploaded: file:///Users/PP/workspace/handswork/target/repository/me/duanyong/handswork/0.1-SNAPSHOT/handswork-0.1-20161213.153008-1.pom (4 KB at 1292.0 KB/sec)
Downloading: file:///Users/PP/workspace/handswork/target/repository/me/duanyong/handswork/maven-metadata.xml
Uploading: file:///Users/PP/workspace/handswork/target/repository/me/duanyong/handswork/0.1-SNAPSHOT/maven-metadata.xml
Uploaded: file:///Users/PP/workspace/handswork/target/repository/me/duanyong/handswork/0.1-SNAPSHOT/maven-metadata.xml (764 B at 373.0 KB/sec)
Uploading: file:///Users/PP/workspace/handswork/target/repository/me/duanyong/handswork/maven-metadata.xml
Uploaded: file:///Users/PP/workspace/handswork/target/repository/me/duanyong/handswork/maven-metadata.xml (278 B at 135.7 KB/sec)
[INFO]
[INFO] --- site-maven-plugin:0.12:site (default) @ handswork ---
[INFO] Creating 12 blobs
[INFO] Creating tree with 13 blob entries
[INFO] Creating commit with SHA-1: b5c52f64b8372ad773ff4331bb8586c8d0422912
[INFO] Updating reference refs/heads/snapshot from e3f4e8c38a988d3289f64b2e195311ecdb4f3f0e to b5c52f64b8372ad773ff4331bb8586c8d0422912
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 47.622s
[INFO] Finished at: Tue Dec 13 23:30:54 CST 2016
[INFO] Final Memory: 11M/168M
[INFO] ------------------------------------------------------------------------

Process finished with exit code 0
```
当发布成功之后可到看BUILD SUCCESS和发布的分支名称（refs/heads/snapshot），同时项目需要用到的各种hash值插件也会自动生成。

### 问题列表
+ 发布到github成功，但其它项目引用却发现没有在*~/.m2/repository/*中存在？<br />
  试试将项目make一下：Build -> Make Module 'xxx'，看日志是否会报错。maven编译插件只负责编译，其中的错误它不处理，造成项目无法引用却又没有任何提示。
