def f(x: Int) = x + 1

f(3)
// 调用的时候可以用 {} 代替 ()
f { 3 }
// f.apply(3); will cause a error

val x = f _
x.apply(3)


class Rational(val a: Float, val b: Float) {
    def add(y: Rational): Rational = new Rational(this.a + y.a, this.b + y.b)
    def show() = println("show")
}
// method 有不同于一般函数的调用方式
val r = new Rational(1, 3)
r.add(new Rational(2, 4))
r.add {new Rational(2, 4)}
r add new Rational(2, 4)
r add(new Rational(2, 4))
