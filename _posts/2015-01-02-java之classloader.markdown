---
layout:         post
title:          java之classloader
subtitle:       对比jetty的classloader说明其原理
date:           2015-01-02
header-img:     img/post-bg-2015.jpg
tags:           java jetty classloader
keyword:        java,jetty,classloader,原理,介绍
description:    简单介绍java的ClassLoader原理，然后通过对比Jetty框架的ClassLoader来说明如何使用ClassLoader
---

### 何为ClassLoader？
ClassLoader从字面上可理解为类加载器，给JVM查找中间代码使用，说白了就是告诉JVM类在何处，然后JVM去加载中间代码。

3类加载器

1. Bootstrap ClassLoader
2. Extension ClassLoader
3. Application ClassLoader

#### Bootstrap ClassLoader


