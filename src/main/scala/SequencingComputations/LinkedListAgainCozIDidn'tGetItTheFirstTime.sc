object LinkedListAgain {

  sealed trait LinkedList[A] {

    // Previous version of `fold` for IntList was
    // def fold[A](end: A, f: (Int, A) => A): A = this match {
    //    case End => end
    //    case Pair(hd, tl) => f(hd, tl.fold(end, f))
    // }

    def fold[B](end: B, f: (A, B) => B): B = this match {
      case End() => end
      case Pair(hd, tl) => f(hd, tl.fold(end, f))
    }
    //Fold is just an adapta􏰀on of structural recursion where we allow the user to
    // pass in the func􏰀ons we apply at each case. As structural recursion is the
    // generic pa􏰁ern for wri􏰀ng any func􏰀on that transforms an algebraic datatype,
    // fold is the concrete realisa􏰀on of this generic pa􏰁ern. That is, fold is the
    // generic transforma􏰀on or itera􏰀on method. Any func􏰃on you care to write on an
    // algebraic datatype can be wri􏰁en in terms of fold.
  }
  final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
  final case class End[A]() extends LinkedList[A]






}