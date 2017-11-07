使用 Docker Container 给出执行环境，执行spark代码。

Docker 带来了好处：

可以直接交付可执行环境，避免了因为环境不同导致代码无法执行。

开发环境，基本上只要装上 maven, jdk, scala-idea-plugin 就 ok 了。其他资源和服务，可以用 Docker 虚拟出来。

更进一步，

JetBrains 家出的 IDE 支持远程调 debug 的功能,
可以把 Docker Container 配置成配备了可执行环境的服务器，然后就可以就着这个可执行环境，用 IDE 作 debug。
我知道 IDEA 支持对于 Java 的远程 debug 和 test， 但是我不熟，所以在下一个例子中，以 Pycharm 为例子，演示如何操作。
