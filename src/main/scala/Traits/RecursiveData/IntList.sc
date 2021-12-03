import scala.annotation.tailrec

sealed trait IntList {
  def getHead: Int
  def getTail: IntList
}

case object End extends IntList {
  override def getHead: Int = 0
  override def getTail: IntList = End
}

final case class Pair(head: Int, tail: IntList) extends IntList {
  override def getHead: Int = head
  override def getTail: IntList = tail
}



val d = End
val c = Pair(3, d)
val b = Pair(2, c)
val a = Pair(1, b)


//implement all the below as trait methods not separate methods.
@tailrec
def createList(length: Int, acc: IntList = End): IntList = {
  if (length == 0) acc
  else createList(length - 1, Pair(length, acc))
}



@tailrec
def sum(intList: IntList, acc: Int = 0): Int = intList match {
  case End => acc
  case Pair(head, tail) => sum(tail, acc + head)
}

@tailrec
def product(intList: IntList, acc: BigInt = 1): BigInt = intList match {
  case End => acc
  case Pair(head, tail) => product(tail, acc * head)
}

@tailrec
def listLength(intList: IntList, acc: Int = 0): Int = intList match {
  case End => acc
  case Pair(_, tail) => listLength(tail, acc + 1)
}

//def double(intList: IntList, acc: IntList = End): IntList = intList match {
//  case End => acc
//  case Pair(head, tail) => double(tail, Pair(head * 2, acc))
//}
def double: IntList =
  this match {
    case End => End
    case Pair(hd, tl) => Pair(hd * 2, tl.double)
  }

val list = Pair(1, Pair(2, Pair(3, End)))
val list2 = createList(4)
list.tail

list2.getTail
End.getTail
list2
double(list)