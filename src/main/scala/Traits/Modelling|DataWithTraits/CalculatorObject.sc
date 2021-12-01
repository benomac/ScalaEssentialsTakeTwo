object CalculatorObject {

  sealed trait Calculation

  final case class Success(result: Int) extends Calculation
  final case class Failure(reason: String) extends Calculation

  object Calculator {
    def + (calculation: Calculation, i: Int): Calculation = calculation match {
      case Success(result) => Success(result + i)
      case Failure(reason) => Failure(reason)
    }
    def - (calculation: Calculation, i: Int): Calculation = calculation match {
      case Success(result) => Success(result - i)
      case Failure(reason) => Failure(reason)
    }

    def / (calculation: Calculation, i: Int): Calculation = calculation match {
      case Success(result) => if (i == 0) Failure("Division by zero!") else Success(result / i)
      case Failure(reason) => Failure(reason)
    }
    // OR
    def divide (calculation: Calculation, i: Int): Calculation = calculation match {
      case Success(result) =>
        i match {
          case 0 => Failure("Division by zero!")
          case _ => Success(result / i)
        }
      case Failure(reason) => Failure(reason)
    }
  }
  val foo = Calculator.+(Success(1), 1)
  val bar = Calculator.+(Failure("Badness"), 1)

  val baz = Calculator./(Success(4), 2)
  val boo = Calculator./(Success(4), 0)
  val faz = Calculator./(Failure("Badness"), 0)

  val haz = Calculator.divide(Success(4), 2)
  val hoo = Calculator.divide(Success(4), 0)
  val jaz = Calculator.divide(Failure("Badness"), 0)
}
CalculatorObject.foo
CalculatorObject.bar

CalculatorObject.baz
CalculatorObject.boo
CalculatorObject.faz

CalculatorObject.haz
CalculatorObject.hoo
CalculatorObject.jaz
//test change