object Calculator {
  trait Calculator

  final case class Succeed(result: Int) extends Calculator

  final case class Failure(reason: String) extends Calculator

}