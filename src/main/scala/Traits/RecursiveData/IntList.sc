import scala.annotation.tailrec

sealed trait IntList {
  def getHead: Int
  def getTail: IntList

  def sum: Int = this match {
    case End => 0
    case Pair(head, tail) => tail.sum + head
  }

  def product: Int = this match {
    case End => 1
    case Pair(head, tail) => tail.product * head
  }

  def double: IntList = this match {
    case End => End
    case Pair(head, tail) => Pair(head * 2, tail.double)
  }


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


@tailrec
def createList(length: Int, acc: IntList = End): IntList = {
  if (length == 0) acc
  else createList(length - 1, Pair(length, acc))
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
//def double: IntList =
//  this match {
//    case End => End
//    case Pair(hd, tl) => Pair(hd * 2, tl.double)
//  }

val list = Pair(1, Pair(2, Pair(3, End)))
val list2 = createList(4)
list.double


