
// When you have a List[Option[Int]] and a method that takes an Int, you can map over
// the list, then inside that map, map over each Option[Int], the inner map will discard
// the Nones and apply the method to the Some(Int)'s! eg:-


val optionList: List[Option[Int]] = List(None, None, Some(3), Some(1))
val numListList: List[List[Int]] = List(List(1), List(2))

val stringList: List[String] = List("Hello", "ben", "dawn")
val stringListList: List[List[String]] = List(List("ben"), List("dawn"), List("hello"))

val numsAndStrings = List("1", "2", "3", "ben", "dawn", "4", "5")

case class PrintOption(ccid: Int)

case class NoneHere(none: None.type)

def returnBool(i: Int): Boolean = {
  i.isValidInt
}

val troos = optionList.map(x => x.map(y => returnBool(y)))


optionList.map {
  case Some(value) => println("THIS VALUE " + PrintOption(value))
  case None => println("None found")
}

stringList.map(_.toUpperCase())
stringList.flatMap(_.toUpperCase())

def toInt(s: String): Option[Int] = {
  try {
    Some(Integer.parseInt(s.trim))
  } catch {
    case e: Exception => None
  }
}

numsAndStrings.map(toInt)
numsAndStrings.map(x => toInt(x))