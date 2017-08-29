// 以 function 函数的方法写出 loop
val a = 1 to 100

val doubleA = {
    for (i <- a)
        yield i * 2
}
