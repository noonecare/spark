// 函数语言的重要特征 lambda 表达式
val x = (y: Int) => y + 1

x(3)

// 简化地写 lambda 表达式
val y: (Int => Int) = 2 * _

y(3)
// 不管这么些有个共同点，必须指明参数的类型。
// 如果编译器可以推断出参数的类型，则不需要指明参数的类型。这称为 type targeting

val a = List(1, 2, 3, 4)
// type targeting 不需要指明类型
a.map(2 * _)
