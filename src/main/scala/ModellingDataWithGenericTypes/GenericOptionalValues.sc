object GenericOptionalValues {

  sealed trait Maybe[A] {
    def fold[B](full: A => B, empty: B): B =
      this match {
      case Full(value) => full(value)
      case Empty() => empty
    }
  }
  final case class Full[A](value: A) extends Maybe[A]
  final case class Empty[A]() extends Maybe[A]

}


import GenericOptionalValues._
val perhaps: Maybe[Int] = Empty[Int]()
val perhaps1: Maybe[Int] = Full(1)
perhaps.fold(a => Full(a), Empty())
perhaps1.fold(a => Full(a * 2), Empty())
perhaps
