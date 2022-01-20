

object Fold {

  //end has no parameters (as End stores no values) and returns B.
  // Thus its type is () => B, which we can op􏰀mise to just a value of type B; and
  // pair has two parameters, one for the list head and one for the tail.
  // The argument for the head has type A,
  // and the tail is recursive and thus has type B.
  // The final type is therefore (A, B) => B.
  sealed trait LinkedList[A] {
    def fold[B](end: B, pair: (A, B) => B): B = this match {
      case Pair(hd, tl) => pair(hd, tl.fold(end, pair))
      case End() => end
    }

    def length: Int = fold[Int](0, (_, tl) => 1 + tl)
  }
  final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
  final case class End[A]() extends LinkedList[A]

}

import Fold.{End, LinkedList, Pair}

val list = Pair("Ben", Pair("dawn", End()))
list.fold[Int](0, (_, tl) => 1 + tl)