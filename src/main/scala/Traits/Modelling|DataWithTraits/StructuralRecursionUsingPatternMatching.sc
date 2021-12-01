//Structural recursion with pattern matching proceeds along the same lines as polymorphism.
// We simply have a case for every subtype, and each pattern matching case must extract
// the fields we’re interested in.


//The Product Type Pattern Matching Pattern

//If A has a b(with typeB) and a c(with typeC),
// and we want to write a method f that accepts an A and returns an F, write

case class B() {
  val foo = "ben"
}
case class C() {
  val bar = "man"
}
case class F(b: B, c: C) {
  val baz = s"${b.foo} ${c.bar}"
}

case class A(b: B, c: C)

//In the body of the method we use b and c to construct the result of type F.

def f(a: A): F =
  a match {
    case A(b, c) => F(b, c)
  }

val x = B()
val y = C()

val d = A(x, y)
f(d).getClass
d.b.getClass



// The Sum Type Pattern Matching Pattern

// If A is a B or C, and we want to write a method f accepting A
// and returning an F, define a pattern matching cse for B and C

sealed trait A
final case class B() extends A
final case class C() extends A

def f(a: A): F =
  a match {
    case B() => ???
    case C() => ???
  }