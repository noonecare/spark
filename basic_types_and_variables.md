### 概念
- Collections API
    - mutable and unmutable
    - Seq
        - Array
        - Tuple
            ```scala
            // 使用 ._{index} 去访问 tuple 中的元素
            val a = (1, 2, 3)
            a._1
            a._2
            a._3
            ```
            ```scala
            // 同时给多个变量赋予初值
            // 与 python 不同, () 必不可少
            var (a, b, c) = (1, 2, 3)
            val (a, b, c) = (1, 2, 3)
            ```
        - List
            - Nil 表示空的 list
            - List 不方便做 append, 要 append 需要使用 ListBuffer

            ```scala
            val a::b::c = List(1, 2, 3)
            var a::b::c = List(1, 2, 3)

            val List(a, b, c) = List(1, 2, 3)
            var List(a, b, c) = List(1, 2, 3)

            val a::b = List(1, 2, 3)
            var a::b = List(1, 2, 3)

            val List(a, _*) = List(1, 2, 3)
            var List(a, _*) = List(1, 2, 3)
            ```
        - Map

- Hierarchy
    - Any, Any Ref, Any Value
    - Nothing, null, unit


