object MapLinkedList {
  // Type F[A]
  // function A => B
  // result F[B]

  sealed trait LinkedList[A] {

    def map[B](fn: A => B): LinkedList[B] =
      this match {
        case Pair(hs, tl) => ???
        case End()        => ???
      }

  }

  final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
  final case class End() extends LinkedList[A]

}