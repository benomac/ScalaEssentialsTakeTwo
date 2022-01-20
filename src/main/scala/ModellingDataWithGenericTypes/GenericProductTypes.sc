object GenericProductTypes {
  def intAndString: Pair[Int, String] = ???

  def booleanAndDouble: Pair[Boolean, Double] = ???

  case class Pair[A, B](one: A, two: B) {

  }

  val intAndStringVal = Pair(1, "hello")
  val booleanAndDoubleVal = Pair(true, 2.0)
}
import GenericProductTypes._
println(intAndStringVal.one)
println(intAndStringVal.two)
