

object GFoldsForGData {

  sealed trait LinkedList[A] {

    // PAGE: 135
    //Fold Pa􏰁ern
    //For an algebraic datatype A, fold converts it to a generic type B. Fold is a
    // structural recursion with:
    //• one function parameter fore ach case in A;
    //• each func􏰀on takes as parameters the fields for its associated
    //class;
    //• if A is recursive, any function parameters that refer to a recursive
    //field take a parameter of type B.
    //The right-hand side of pa􏰁ern matching cases, or the polymorphic meth-
    // ods as appropriate, consists of calls to the appropriate func􏰀on.


    //• end has no parameters (as End stores no values) and returns B.
    // Thus its type is () => B, which we can op􏰀mise to just a value of type B; and
    //• pair has two parameters, one for the list head and one for the tail.
    // The argument for the head has type A, and the tail is recursive and
    // thus has type B. The final type is therefore (A, B) => B.

    def fold[B](end: B, pair: (A, B) => B): B =
      this match {
        case End() => end
        case Pair(hd, tl) => pair(hd, tl.fold(end, pair))
      }

    def lengthFold: Int =
      fold[Int](0, (_, tl) => 1 + tl)

  }
  final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
  final case class End[A]() extends LinkedList[A]

  // Placeholder syntax, while wonderfully terse, can be confusing for
  // large expressions and should only be used for very small functions.
  val foo = (_: Int) * 2 //this is the same as
  val bar = (a: Int) => a * 2


}
import GFoldsForGData.{End, Pair}

val list = Pair(1, Pair(2, End()))
list.lengthFold

