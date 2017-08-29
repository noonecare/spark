// view bound 何以看成是 约等于 upperBound
// 约等 的约字体现为如果一个参数经过 implicit conversion 可以变成 upperBound 类型也可以
// 而 upperBound 要求参数必须是 upperBound 类型
// 下面是个 view bound 的 upperBound 的例子


object Demo extends App {
    // view bound demo
    def maxListViewBound[T <% Ordered[T]](elements: List[T]): T = {
        elements match {
            case List() => throw new IllegalArgumentException("empty list!")
            case List(x) => x
            case x :: rest =>
                val maxRest = maxListViewBound(rest)
                if (x > maxRest) x
                else maxRest
        }
    }
    
    // uppper bound demo
    def maxListUpperBound[T <: Ordered[T]](elements: List[T]): T = {
        elements match {
            case List() => throw new IllegalArgumentException("empty list!")
            case List(x) => x
            case x :: rest =>
                val maxRest = maxListUpperBound(rest)
                if (x > maxRest) x
                else maxRest
        }
    }
    
    
    // demonstrate difference between view bound and upper bound
    // maxListViewBound 可以通过编译，因为存在 implict conversion 把 Int 转成 Ordered[Int]
    maxListViewBound(List(1, 2, 3, 4, 5))
    // maxListUpperBound 通不过编译，因为 Int 不属于 Ordered[T]
    maxListUpperBound(List(1, 2, 3, 4, 5))
}