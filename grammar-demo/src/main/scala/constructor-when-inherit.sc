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
