Java
~~~~


Done
^^^^

* `Effective Java`_  和书的配套 `源码`_

    语法上补充两点：

        #. Java 不支持重写操作符，比如不能重写 == 运算符。
        #. autobox 执行的是 `valueOf` 方法， unbox 执行的是 `iniValue` 方法。

* `Concepts of Programing Languages, Tenth Edition`_

    覆盖了程序言语设计的主要问题，能让我们较快的入手新语言（特别是语法层面），但是对于语言特性介绍得不够。

* `Maven 官档`_

* `junit`_

* JVM 特别是 GC

    * Java 远程调试，参见我写的 `remote-debug`_
    * GC

        GC 会影响 Java 程序的性能，在 hadoop/spark 调优时，要考虑GC 的影响。不过我对于 GC 知之甚少。

    * JVM 架构，知之甚少，主要是看了 `youtube 视频`_


Todo
^^^^

* 读 spark 源码
* 选择重要的章节，读 hadoop 源码
* 了解 JVM 架构，特别是 GC
* 读 `java.lang`, `java.util`, `java.io`, `java.util.concurrent` 的源码
* 读 `How to Write Doc Comments for the Javadoc Tool`_


.. _Concepts of Programing Languages, Tenth Edition: https://item.jd.com/11209148.html
.. _Effective Java: https://item.jd.com/26002961439.html
.. _源码: https://github.com/marhan/effective-java-examples
.. _Maven 官档: https://maven.apache.org/
.. _junit: https://github.com/junit-team/junit4
.. _How to Write Doc Comments for the Javadoc Tool: http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html
.. _remote-debug: https://github.com/noonecare/spark/tree/master/remote-debug
.. _youtube 视频: https://www.youtube.com/watch?v=ZBJ0u9MaKtM
