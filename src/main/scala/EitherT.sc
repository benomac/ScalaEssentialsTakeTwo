import cats.data.EitherT
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try

def parseDouble(s: String): Either[String, Double] =
  Try(s.toDouble).map(Right(_)).getOrElse(Left(s"$s is not a number"))

def divide(a: Double, b: Double): Either[String, Double] =
  Either.cond(test = b != 0, right = a / b, left = "Cannot divide by zero")

//def divisionProgram(inputA: String, inputB: String): Either[String, Double] = {
//  val f: Either[String, Double] = for {
//    a <- parseDouble(inputA)
//    b <- parseDouble(inputB)
//    result <- divide(a, b)
//
//  } yield result
//  f
//}
//
//divisionProgram("4", "2") // Right(2.0)
//// res0: Either[String, Double] = Right(2.0) // Right(2.0)
//divisionProgram("a", "2") // Left("a is not a number")
//// res1: Either[String, Double] = Left("a is not a number")
//
//
//
def parseDoubleAsync(s: String): Future[Either[String, Double]] =
  Future.successful(parseDouble(s))
def divideAsync(a: Double, b: Double): Future[Either[String, Double]] =
  Future.successful(divide(a, b))

def divisionProgramAsync(inputA: String, inputB: String): Future[Either[String, Double]] =
  parseDoubleAsync(inputA) flatMap { eitherA =>
    parseDoubleAsync(inputB) flatMap { eitherB =>
      (eitherA, eitherB) match {
        case (Right(a), Right(b)) => divideAsync(a, b)
        case (Left(err), _) => Future.successful(Left(err))
        case (_, Left(err)) => Future.successful(Left(err))
      }
    }
  }




def divisionProgramAsync(inputA: String, inputB: String): EitherT[Future, String, Double] =
  for {
    a <- EitherT(parseDoubleAsync(inputA))
    b <- EitherT(parseDoubleAsync(inputB))
    result <- EitherT(divideAsync(a, b))
  } yield result

divisionProgramAsync("4", "2").value
// res2: Future[Either[String, Double]] = Future(Success(Right(2.0)))
divisionProgramAsync("a", "b")
// res3: Future[Either[String, Double]] = Future(Success(Left(a is not a number)))