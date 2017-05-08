// 函数语言的重要特征 lambda 表达式
val x = (y: Int) => y + 1

x(3)

// 简化地写 lambda 表达式
val y: (Int => Int) = 2 * _

y(3)
// 不管这么些有个共同点，必须指明参数的类型
