class A(nameParam: String)
{
    val name: String = nameParam
    
}


class B(namParam: String, ageParam: Int) extends A(namParam) {
    val age: Int = ageParam
}

val b = new B("wangmeng", 27)

b.age
b.name


// private constructor
class PrivateConstructor private (val a: Int) {
    def show(): Unit = println("a is " + a.toString)
}

// 一般如果 constructor 定义为私有的，就会定义一个 factroy method
object PrivateConstructor {
    def apply(a: Int): PrivateConstructor = new PrivateConstructor(a)
}

// new PrivateConstructor(1) 将会报错，因为 PrivateConstructor 是 private 的， 所以我注释掉了
val privateConstructor = PrivateConstructor(1)


// multiple constructor
class Complex(realParam: Long, imgParam: Long)
{
    val real: Long = realParam
    val img: Long = imgParam
    
    // Auxillary Construtor the method name must be this
    def this(realNum: Long) = this(realNum, 0L)
    
    def classify(): String = {
        if(real == 0) {
            "纯虚数"
        }
        else {
            if (img == 0) {
                "实数"
            }
            else {
                "tanh is " + (real / img).toString
            }
        }
        
    }
    
}

val d = new Complex(1L, 2L)
val e = new Complex(3L)