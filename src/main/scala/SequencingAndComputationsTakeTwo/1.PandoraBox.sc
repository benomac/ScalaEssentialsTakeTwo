// TYPE PARAMETERS

object PandorasBoxTakeTwo {

  final case class Box[A](value: A)

  def generic[A](in: A): String =
    s"${in.getClass.toString} is the type"

}
import PandorasBoxTakeTwo._

Box(2)
res0.value

Box("hello")
res2.value

generic[String]("foo")

generic(1)

// Type parameters work in a way analogous to method parameters.
// When we call a method we bind the method’s parameter names to
// the values given in the method call. For example, when we call
// generic(1) the name in is bound to the value 1 within the body of generic.
// When we call a method or construct a class with a type parameter,
// the type parameter is bound to the concrete type within the method or class body.
// So when we call generic(1) the type parameter A is bound to Int in the body of generic.

// Generic types can be declared in a class or trait declara􏰀on in which case
// they are visible throughout the rest of the declara􏰀on.

case class Name[A](...){...}
trait Name[A]{ ... }

// Alterna􏰀vely they may be declared in a method declara􏰀on,
// in which case they are only visible within the method.

def name[A](...){ ... }