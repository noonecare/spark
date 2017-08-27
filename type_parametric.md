### 概念

- Variance
    - variance

        如果 A 属于 B, Sort[A] 是否属于 Sort[B]

    - covariant

        ```scala
        class Sort[+T] {}
        ```
        如果 A 属于 B, 那么 Sort[A] 属于 Sort[B]

    - contravariance

        ```scala
        class Sort[-T] {}
        ```

        如果 A 属于 B, 那么 Sort[B] 属于 Sort[A]

    - nonvariant

        ```scala
        class Sort[T] {}
        ```
        如果 A 属于 B, 那么 Sort[A] 不属于 Sort[B], Sort[B] 也不属于 Sort[A]

    - variance check

        variance 可能会导致逻辑错误(具体导致什么逻辑错误，我还没有想好)。scala compiler 会自动做这方面的检查（ variance check ）。如果代码可能出错
        compiler 会给出提示。

- UpperBound
    ```scala
    [U <: T]
    ```

- LowerBound

    ```scala
    [U >: T]
    ```
- Compound Type

    ```scala
    A with B
    ```
- [Type erasure](https://stackoverflow.com/questions/38570948/type-erasure-in-scala)
    ```scala
    val a = List[Int](1, 2, 3)
    a.isInstanceOf[List[Int]]
    a.isInstanceOf[List[String]]
    ```
### 讨论

这一块的概念不太好理解，但是用的也不多。借助于 IDE 的帮助，这些问题都很容易解决。




