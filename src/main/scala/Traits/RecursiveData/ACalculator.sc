object ACalculator {

  trait Expression {
    def eval: Double = this match {
      case Addition(left, right) => left.eval + right.eval
      case Number(value) => value
      case Subtraction(left, right) => left.eval - right.eval
    }
  }
  final case class Addition(left: Expression, right: Expression) extends Expression

  final case class Subtraction(left: Expression, right: Expression) extends Expression

  final case class Number(value: Double) extends Expression
}
  ACalculator.Addition(ACalculator.Number(1.0), ACalculator.Number(2.0)).eval




