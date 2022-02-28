sealed trait IntList {

  def fold[A](end: A, f: (Int, A) => A): A =
    this match {
      case End => end
      case Pair(hd, tl) => f(hd, tl.fold(end, f))
    }

  def lengthFold: Int =
    fold[Int](0, (_, tl) => 1 + tl)

  def productFold: Int =
    fold[Int](1, (hd, tl) => hd * tl)

  def sumFold: Int =
    fold[Int](0, (hd, tl) => hd + tl)

  def doubleFold: IntList =
    fold[IntList](End, f = (hd, tl) => Pair(hd * 2, tl))


  def length: Int =
    this match {
      case End => 0
      case Pair(hd, tl) => 1 + tl.length
    }
  def double: IntList =
    this match {
      case End => End
      case Pair(hd, tl) => Pair(hd * 2, tl.double)
    }
  def product: Int =
    this match {
      case End => 1
      case Pair(hd, tl) => hd * tl.product
    }

  def sum: Int =
    this match {
      case End => 0
      case Pair(hd, tl) => hd + tl.sum
    }


}
case object End extends IntList
case class Pair(hd: Int, tl: IntList) extends IntList

val list = Pair(1, Pair(2, Pair(3, End))).doubleFold



val sayHi = () => "Hi!"
val d = sayHi()
println(d)

val add1 = (x: Int) => x + 1
add1(100)

val sum = (x: Int, y: Int) => x + y: Int // YOU CAN ADD THE FUNCTIONS TYPE ANNOTATION, TO IT'S BODY, LIKE THIS: x + y: Int

sum(10000, 10000)

