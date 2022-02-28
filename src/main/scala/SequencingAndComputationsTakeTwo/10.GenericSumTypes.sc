object GenericSumTypes {
  def intOrString(input: Boolean) =
    if(input)
      Left[Int, String](123)
    else
      Right[Int, String]("abc")


  trait Sum[A, B]
  final case class Left[A, B](value: A) extends Sum[A, B]
  final case class Right[A, B](value: B) extends Sum[A, B]


}
import GenericSumTypes._

intOrString(true)
intOrString(false)

Left[Int, String](1).value
// res9: Int = 1
Right[Int, String]("foo").value
// res10: String = foo
val sum: Sum[Int, String] = Right("foo")
// sum: sum.Sum[Int,String] = Right(foo)
sum match {
  case Left(x) => x.toString
  case Right(x) => x
}