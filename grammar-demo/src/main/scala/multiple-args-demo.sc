def f(a: String*): Unit =
{
    print(a)
}

def g(a: Array[String]) = {
    print(a)
}

f("hello", "every", "one")
//g("hello", "every", "one")
