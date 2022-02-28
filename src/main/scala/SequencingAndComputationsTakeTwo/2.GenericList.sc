object GenericList {

  sealed trait Result[A]
  case class Success[A](result: A) extends Result[A]
  case class Failure[A](reason: String) extends Result[A]
  // Sum type patter is-a or
  sealed trait LinkedList[A] {

    def length: Int = this match {
      case Pair(_, tail) => 1 + tail.length
      case End() => 0
    }

    def contains(item: A): Boolean = this match {
      case Pair(head, tail) =>
        if(head == item)
        true
      else
        tail.contains(item)
      case End() => false
    }

    def apply(index: Int): Result[A] =
      this match {
        case Pair(hd, tl) =>
          if(index == 0)
            Success(hd)
          else
            tl(index - 1)
        case End() =>
          Failure("Index out of bounds")
      }
  }
  final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
  final case class End[A]() extends LinkedList[A]


}

import GenericList._

val example = Pair(1, Pair(2, Pair(3, End())))
//assert(example.length == 3)
//assert(example.tail.length == 2)
//assert(End().length == 0)
//
//val example2 = Pair(1, Pair(2, Pair(3, End())))
//assert(example2.contains(3) == true)
//assert(example2.contains(4) == false)
//assert(End().contains(0) == false)

//val example = Pair(1, Pair(2, Pair(3, End())))//assert(example(0) == 1)
////assert(example(1) == 2)
////assert(example(2) == 3)
////assert(try {
////  example(3)
////  false
////} catch {
////  case e: Exception => true
////})

assert(example(0) == Success(1))
assert(example(1) == Success(2))
assert(example(2) == Success(3))
assert(example(3) == Failure("Index out of bounds"))