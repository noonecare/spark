scala

我非常推崇 《程序设计语言概念》 这本书，所以我希望根据《程序设计语言概念》的观点来复习一下各种语言的不同和特点。

- scala
- Java
- Python
- shell
- C/C++
- JavaScript
- C#

Lexical and Syntax Analysis:


keyword and reserved word:

- val（值不可变的变量）
- var(值可变的变量)
- def（定义函数，method, property）
- class(定义类)
- final（相当于C/C++ 中的 static）


scala 对于变量的规则和 C/C++ 语言非常类似，除了关键字的名称不同之外。同样 使用变量之前需要首先定义（only classes can have declared but undefined members，只有类可以只声明，不赋初值）。

scala 同样是 static scope, 同样支持 闭包（我还没见过不支持闭包的语言），是强类型语言。


expression and assignment statement:


数据类型和操作：

和大多数语言一样，scala中常用的数据类型也是 字符串，整数，浮点数，布尔值，序列，集合，栈等。

字符串： String
String


序列：
Array
Tuple
List
Map
对于序列，有很多其他语言不会用到的运算符：

List 中 Nil 表示空序列， 元素::List 相当于把 元素添加到 List 头上形成新的List。
::: 可以把 两个 List 链接在一起构成一个新的 List。
List 中有 head, last 和 length property。
List 不方便 append（时间复杂度高）,要 append 可以用 ListBuffer。
在定义变量（其他情况下不行）时，可以用如下方式，一次性给多个变量赋值：

val a::b::c = List(1, 2, 3)
var a::b::c = List(1, 2, 3)

val List(a, b, c) = List(1, 2, 3)
var List(a, b, c) = List(1, 2, 3)

特别的如果

val a::b = List(1, 2, 3)
var a::b = List(1, 2, 3)

你会发现 b 成了 [2, 3]。


除此之外还有一张方式：
var List(a, \_\*) = List(1, 2, 3)
val List(a, \_\*) = List(1, 2, 3)

\_\* 称为 sequence pattern, 表示变长的 sequence List。 我感觉很奇怪：

var (a, _*) = (1, 2, 3)  运行出错，报错为 bad use of sequence pattern.



ListBuffer:
+=  append 一个 element
+=: prepened 一个 element
根据下标取list中的值（这一点和主流不一样）， List(index)


Tuple： Tuple 的特点是可以容许不同类型的数据在同一个 tuple 中。用 括号括住几个元素，就会产生一个 Tuple:
根据下标去 Tuple 中的值可以 Tuple._{index}

在定义变量时（其他情况下不可以）， 可以用 tuple 的方式定义多个变量
比如：

var (a, b, c) = (1, 2, 3)
val (a, b, c) = (1, 2, 3)
与 python 不同， 在 scala 中 () 号必不可少。



Array 长度固定，元素可变。List 元素固定，长度可变。 Tuple 元素固定， 长度固定。




scala for 循环：
for(x <- List){println(x)}









Scala 语言最大的特点是简洁，Scala 中的 case class, Implicit Conversion 和 Extractor 使得 Scala 变的非常简洁。下面重点复习一下这几个特性。




Pattern match:

例子：

```scala
def f(x) = x match {
	case 1 => println("1")
    case _ => println("other")
}
```

scala 的 case 非常强大，强大到，让我有种奇怪的想法。我写了如下这段错误的代码：
```scala
def f(x: Any) = x match {
	case y + 1 => println(y)
    case _ => println("others")
}
```
明显这是不对的，也就是说 scala 的 case match 中的 pattern 只支持如下的写法：

constant pattern:

比如 case 1, case "name" 等等

typed pattern:
比如 case x: Int, case x: String 等等。

pattern guard:
比如 case x: Int  if  x > 0 等等。

List pattern:
比如 case x::y, case List(x, y, _*), case List(_, x, _*) 等

Tuple pattern:
比如 case (a, b, c)


比如
case class Person(eat: Int, drink: Int)
case Perseon(_, _)


wildcard _

variable an identifier which will bind the variable to be matched.

case e => e

Constructor pattern:
case pattern 会自动定义 apply 和 unapply 方法，而这两个方法使得 Constructor pattern 可用。没有定义这两个方法的 类 是不能用于 Constructor pattern 的。case class 是 class 同时是 object， 你简单的 class 定义的 class 只是 class 不是 object(在 python 中 class 属于 object, 在 scala 中不是)

Constructor pattern 其实是个 extractor, extractor 是个 定义了 apply 和 unapply 的 object。



Scala Implicit Conversion:
隐式类型转换

所有加了 implicit 关键词的，编译器都把他们当成默认项。

Implicit Rules:
- Marking Rule: Only Definitions marked implicit are available.
- Scope Rule: An Inserted implicit conversion must be in scope as a single identifier or be associated with the source or target type of the conversion.
- One-at-a-time Rule: Only 



scala 中 _ 的用法：

_*


