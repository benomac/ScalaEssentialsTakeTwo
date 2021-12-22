object ACalculator {

  trait Expression {
    def eval: Calculation = this match {

      case Addition(left, right) =>
        left.eval match {
        case Failure(reason) => Failure(reason)
        case Success(result1) =>
          right.eval match {
          case Success(result2) => Success(result1 + result2)
          case Failure(reason) => Failure(reason)
        }
      }

      case Subtraction(left, right) =>
        left.eval match {
          case Failure(reason) => Failure(reason)
          case Success(result1) =>
            right.eval match {
              case Success(result2) => Success(result1 - result2)
              case Failure(reason) => Failure(reason)
            }
        }

      case Division(left, right) =>
        right.eval match {
          case Failure(reason) => Failure(reason)
          case Success(result1) => if (result1 == 0)
            Failure("zero division error")
          else
            left.eval match {
              case Success(result2) => Success(result2 / result1)
              case Failure(reason) => Failure(reason)
            }
        }

      case SquareRoot(number) => number.eval match {
        case Success(result1) => if (result1 < 0)
          Failure("Can't apply to negative number")
        else
          Success(Math.sqrt(result1))
        case Failure(reason) => Failure(reason)
      }

      case Number(value) => Success(value)

    }
  }

  sealed trait Calculation

  final case class Success(result: Double) extends Calculation

  final case class Failure(reason: String) extends Calculation


  final case class Addition(left: Expression, right: Expression) extends Expression

  final case class Subtraction(left: Expression, right: Expression) extends Expression

  final case class Division(left: Expression, right: Expression) extends Expression

  final case class SquareRoot(value: Expression) extends Expression

  final case class Number(value: Double) extends Expression
}
ACalculator.Division(ACalculator.Number(1.0), ACalculator.Number(3.0)).eval
ACalculator.SquareRoot(ACalculator.Number(4)).eval
ACalculator.Subtraction(ACalculator.Number(1), ACalculator.Number(2)).eval
ACalculator.Addition(ACalculator.Number(2), ACalculator.Number(2)).eval





