object DivisionExercise {

  object Divide {
    def apply(number: Int, divider: Int): DivisionResult =
      if (divider == 0) Infinite
      else Finite(number / divider)
  }

  sealed trait DivisionResult

  // this is a case class as it needs to hold the result of number / divider
  final case class Finite(res: Int) extends DivisionResult

  // could be a final case class but would need empty parentheses,
  // so can simply be a case object
  case object Infinite extends DivisionResult // could be a final case class but would need empty parentheses

}

DivisionExercise.Divide(2, 2) match {
  case DivisionExercise.Finite(res) => s"It's Finite, $res"
  case DivisionExercise.Infinite => "It's Infinite mofo!"
}