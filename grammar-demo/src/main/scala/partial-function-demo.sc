//Partial Function

def sum(a: Int, b: Int, c: Int) = a + b + c

// sum.apply(1, 2, 3)
(sum _).apply(1, 2, 3)

val p = sum(1, _: Int, 3)

p.apply(2)
