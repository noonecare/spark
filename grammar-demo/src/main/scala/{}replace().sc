def sum(a: Int, b: Int, c: Int) = a + b + c

val b = sum _

// 构造了新的函数
val c = sum(1, _: Int, 3)

c(4)


// scala 只有一个参数的函数，可以装成 control 语句（for  try catch）来使用（调用参数时，用 {} 替代 ()），比如下面的例子。
def f(a: Int) = a + 1

f(1)

f { 2 }

// a normal function cannot use the  statement below
//f 2


class A {
    def g(a: Int): Int = a + 1
    
    def g_multi(a: Int, b: Int): Int = a + b
}

val a = new A

// a method can use the the statement below, comparing f and g.
a g 1
a g(2)
a g { 2 }


def f_multi(a: Int, b: Int) = a + b


f {
    print("hello")
    3
}

f {
    print("hello"); 3
}

f {
    2
    3
}