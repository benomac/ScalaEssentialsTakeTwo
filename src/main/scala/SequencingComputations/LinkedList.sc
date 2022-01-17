object LinkedList {




  sealed trait Result[A]
  case class Success[A](result: A) extends Result[A]
  case class Failure[A](reason: String) extends Result[A]


  sealed trait LinkedList[A] {
    def length: Int = this match {
      case Pair(_, tail) => 1 + tail.length
      case End() => 0
    }

    def contains(thing: A): Boolean = this match {
      case Pair(head, tail) =>
        if (thing == head) true
      else
        tail.contains(thing) // <- Don't forget, to call the function recursively,
                              // for a `this match case` to do it like this.
      case End() => false
    }
//     THIS WAS MY WAY, BELOW IS BETTER!!(PROPER)
//    def apply(n: Int, acc: Int = 0): A = this match {
//      case Pair(head, tail) => if (n == acc) head else tail.apply(n, acc + 1)
//      case End() => throw new Exception("Bad things happened")
//    }
def apply(index: Int): Result[A] =
  this match {
    case Pair(hd, tl) =>
      if(index == 0)
        Success(hd)
      else
        tl.apply(index - 1) // Don't have to HAVE to have the `apply` here
    case End() =>
      Failure("Index out of bounds")
  } }

  final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A] {
   }

  final case class End[A]() extends LinkedList[A] {
  }


  val stringList = Pair("hello", Pair("ben", End()))
  val intList = Pair(1, Pair(2, Pair(3, End())))
  }

LinkedList.intList(0)
LinkedList.intList.apply(0) // Same as line 51
LinkedList.stringList.apply(5)
