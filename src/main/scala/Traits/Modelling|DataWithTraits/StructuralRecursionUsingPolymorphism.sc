// Structural Recursion using Polymorphism

// Polymorphic dispatch, or just polymorphism for short, is a fundamental oop technique.
// If we define a method in a trait, and have different implementations in classes extending that trait,
// when we call that method, the implementation on the actual concrete instance will be used.

// simple ex

object WorkingWithData {

  sealed trait A {
    def foo: String = "It's A"
    //Remember that if you provide a default implementation in a trait,
    // you should ensure that implementation is valid for all subtypes.
  }
  final case class B() extends A {
    override def foo: String = "It's B!" //only need to override if the method is 'implemented' in trait
  }
  final case class C() extends A {
    override def foo: String = "It's C" //only need to override if the method is 'implemented' in trait
  }
}
WorkingWithData.B().foo

// The Product Type Polymorphism Pattern

// If a has a b ( with type B) and a c (with type C), and we want to write a
// method f returning an F, simply write the method in the usual way:

case class A(b: B, c: C) {
  def f: F = ???
}
// In the body of the method we must use b and c and any method parameters
// to construct the result type of F.



// The Sum Type Polymorphism Pattern

// If A is a B or C, and we want to write a method f returning an F,
// define f as an abstract method on A and provide concrete implementation in
// B and C


sealed trait A {
  def f: F
}
final case class B extends A {
  def f: F = ???
}
final case class C extends A {
  def f: F = ???
}
