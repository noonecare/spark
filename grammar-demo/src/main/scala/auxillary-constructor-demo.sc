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
