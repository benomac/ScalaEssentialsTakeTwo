object Mistake {

  sealed trait Maybe[A]
  final case class Full[A](value: A) extends Maybe[A]
  final case class Empty[A]() extends Maybe[A]

}

import Mistake._

val perhaps: Maybe[Int] = Empty[Int]()
val perhaps: Maybe[Int] = Full(1)