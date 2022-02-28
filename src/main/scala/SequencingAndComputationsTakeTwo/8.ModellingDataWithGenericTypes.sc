

object ModellingDataWithGenericTypes {

  case class Pair[A, B](one: A, two: B)



}
import ModellingDataWithGenericTypes.Pair

val pair = Pair[Int, String](39, "my age")
val anotherPair = Pair(true, 3.0) //you don't always have to give the types, when constructing a Pair!!

pair.one
pair.two

anotherPair.one
anotherPair.two