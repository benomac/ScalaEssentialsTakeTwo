object CalculationsResult {

  sealed trait Result[A]

  case class Success[A](result: A) extends Result[A]
  case class Failure[A](reason: String) extends Result[A]

  // Invariant Generic Sum Type Pattern
  // if A of type T is a B or a C write:
   sealed trait A[T]
   final case class B[T]() extends A[T]
   final case class C[T]() extends A[T]



}