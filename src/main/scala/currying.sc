def plus(x: Int, y: Int): Int = x + y


// Curried
def plus(x: Int) = (y: Int) => x + y

// partially applied function
val foo = plus(3)

foo(10)


// different syntax

def plus(x: Int) (y: Int) = x + y

val bar = plus(3)_

bar(10)


def curriedList(x: List[Int]) (y: Int) = x.map(x => x + y)

val bar = curriedList(List(3, 2, 3))_

bar(10)

List(3, 2, 3).map(x => x + 10)