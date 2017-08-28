scala 和 java 编译是相同的字节码。都运行在 JVM 上，本文简单介绍[JVM](https://www.youtube.com/watch?v=ZBJ0u9MaKtM)。

- Class Loader

    Bootstrap Class Loader, Extension Class Loader, App Class Loader ， 双亲委托机制

    最常用的代码是 this.getClass.getClassLoader.getResourcesAsStream()

- Runtime DataArea

    jvm 提供了参数导致控制 Runtime DataArea 每一部分的大小

    Xms, Xmx 最小和最大 Heap 区大小， Heap 去用于保存 object

    maxPermsize, Method Area, 用于保存每个类的定义，函数方法的定义

    Xss, Java Stacks 保存着所有 Stack Frame

    PC Registers, 每个 thread 会有个寄存器保存当前 thread 执行到了那条语句，这个没有什么好调的，不常用

    Native method stacks ，没什么好调节的

- Execution Engine

    [Interperter](https://stackoverflow.com/questions/7674839/is-the-jvm-a-compiler-or-an-interpreter)

    JIT Compiler

    Hotspot profiler

    [GC](https://www.youtube.com/watch?v=LJC_2agoLuE)
