def f(x: Int) = x + 1

f(3)
// 调用的时候可以用 {} 代替 ()
f { 3 }
// f.apply(3); will cause a error

val x = f _
x.apply(3)
