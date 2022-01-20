

object FunctionsAndFunctionLiterals {
  sealed trait IntList {

    def fold[A](end: A, f: (Int, A) => A): A =
      this match {
        case Pair(hd, tl) => f(hd, tl.fold(end, f))
        case End => end

      }


    def length: Int =
      this match {
        case End => 0
        case Pair(hd, tl) => 1 + tl.length
      }

    def lengthFold: Int = fold[Int](0, (_, tl) => 1 + tl)

    def product: Int =
      this match {
        case End => 1
        case Pair(hd, tl) => hd * tl.product
      }

    def productFold: Int = fold[Int](1, (hd, tl) => hd * tl)

    def sum: Int =
      this match {
        case End => 0
        case Pair(hd, tl) => hd + tl.sum
      }

    def sumFold: Int = fold[Int](0, (hd, tl) => hd + tl)

    def double: IntList =
      this match {
        case End => End
        case Pair(hd, tl) => Pair(hd * 2, tl.double)
      }

    def doubleFold: IntList = fold[IntList](End, (hd, tl) => Pair(hd * 2, tl))
    // had to make `fold` generic to use it on double, because of this you now have to tell `fold`,
    // what type its using. eg `fold[IntList]` as above

  }
  case object End extends IntList
  case class Pair(hd: Int, tl: IntList) extends IntList

  val sayHi = () => "hi"
  val add1 = (x: Int) => x + 1: Int
  val sum = (x: Int, y: Int) => x + y

  val list = Pair(1, Pair(2, Pair(3, End)))
}

import FunctionsAndFunctionLiterals._
list.sum
list.product
list.productFold
list.length
list.lengthFold
list.doubleFold
list.double

//
//sayHi
//sayHi()
//
//add1
//add1(1)
//
//sum
//sum(1, 2)