object GenericSumTypes {

  // Consider value method that, depending on the value of its parameters, returns one of two types:
//  def intOrString(input: Boolean) =
//    if(input == true) 123 else "abc"

  trait Sum[A, B] {

    def intOrString(input: Boolean): Sum[Int, String] =
      if(input) {
        Left[Int, String](123)
      } else {
        Right[Int, String]("abc")
      }
//    def value = this match {
//      case Left(value) => value
//      case Right(value) => value
//      case _ => "No value detected"
//    }

    def fold[C](left: A => C, right: B => C): C =
      this match {
        case Left(a) => left(a)
        case Right(b) => right(b)
      }
  }
  final case class Left[A, B](value: A) extends Sum[A, B]
  final case class Right[A, B](value: B) extends Sum[A, B]
  // We can’t simply write this method as shown above because
  // the compiler infers the result type as Any.
  // Instead we have to introduce value new type to explicitly represent the disjunc􏰀on:


}
import GenericSumTypes._
Left[Int, String](1).value

Right[Int, String]("foo").value


val sum: Sum[Int, String] = Right("foo")

sum match {
  case Left(x) => x.toString
  case Right(x) => x
}
