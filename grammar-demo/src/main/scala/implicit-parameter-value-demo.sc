import scala.language.implicitConversions

class Complex(realParam: Long, imgParam: Long)
{
    val real: Long = realParam
    val img: Long = imgParam
    
    // Auxiliary Constructor the method name must be this
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


// 在 scala/java 中函数不是类
//sum.isInstanceOf[PartialFunction]


implicit def LongToComplex(x: Long): Complex = {
    new Complex(x)
}

3L classify


case class MyTip(tip: String)

implicit val temp = MyTip("greetings, ")

def say(name: String)(implicit greet: MyTip) =
{
    print(greet.tip + name)
}

say("wangmeng")

// implicit val temp_1 = new MyTip("hello, ")

say("wangmeng")

say("wangmeng")(MyTip("shit, "))


class ImplicitDemo {
    implicit val greet = MyTip("Hello, ")

    def say(name: String)(implicit a: MyTip): Unit =
    {
        print(a.tip)
        print(name)
    }
}

val b = new ImplicitDemo()
b say "wangmeng"
