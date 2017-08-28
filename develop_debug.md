### 概念

- 测试，调试
- maven/sbt
- Intelij IDEA

### 讨论

开发中的问题，都比较琐碎，难以分类。感觉写成遇见的问题的集合更好。

scala 的后向兼容做的非常垃圾，所以开发之前请先选好版本。

sbt 是 scala 的标准构建工具。但是使用 maven 也可以。

scala 做测试时，使用 [scalatest-maven-plugin](http://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin) 比较方便，但是使用 maven-surefire-plugin 也可以。

scala 的编译，使用 maven-scala-plugin。编译 main 目录下的代码需要执行 compile goal, 编译 test 目录下的代码需要执行 testCompile goal。我之前遇见过 执行 mvn test 时， 报 no tests to run 的错误。
发现是因为我没有执行 testCompile goal, 导致没有编译出用于测试的类，所以 no tests to run。

IDEA 的 maven plugin 会把 pom.xml 中的(scope 为 compile, test 的)依赖自动引入到当前 project 下，这样当我们写代码时，
依赖中的名称（比如包名，类名，函数名等）能自动补全。但是，我们可以手动修改 project structure -> libraries 来修改当前 project 中引入的依赖。
特别的，当我们把 spark-core 这些 artifact 设成 providied 时，IDEA 的 maven-plugin 不会引入 spark-core ，这时，我们可以手动修改 proejct structure, 把 spark-core 引入 project 的依赖，方便我们携代码，编译做测试等等。

java 中可以使用 javadoc 生成 API 文档， scala 中可以执行 maven-scala-plugin 中的 doc goal 生成 API 文档。scala 中注释文档的写法和 java 中的注释文档的写法完全一致。

[jvm](jvm.md) 方面可能需要设置 heap size。比如发生如下错误的时候
```shell
java.lang.IllegalArgumentException: System memory 259522560 must be at least 4.718592E8. Please use a larger heap size.
```

Remote Debug 和 持续集成是我的 todo 项。