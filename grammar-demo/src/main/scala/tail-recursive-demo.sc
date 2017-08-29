//  这不是尾递归
def f(n: Int): Int = {
    if( n == 0 )
    {
        1
    }
    else {
        2 * f(n - 1)
    }
}

println("这不是尾递归，但还是可以执行的", f(3))


def g(n: Int): Int = {
    var sum = 0
    // 这是尾递归
    def recursive_fun(m: Int): Int = {
        if (m == 0) { 0 }
        else {
            sum += m
            recursive_fun(m - 1)
        }
    }
    recursive_fun(100)
    sum
}

println("g result is: ", g(100))
