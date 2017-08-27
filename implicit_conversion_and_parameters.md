### 概念

- rules for implicit

    - marking rule: only definitions marked implicit are available.
    - scope rule
    - one at a time rule: only one implicit is tried.
    - explicits first rule

- View Bounds

    ```scala
    T < % Ordered[T]
    ```

- when multiple conversions can apply
    - cause a compile error.
    - or use only one implicit.

### 讨论