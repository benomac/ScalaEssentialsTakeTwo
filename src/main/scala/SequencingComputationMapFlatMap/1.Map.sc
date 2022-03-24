object MapSequence {
  case class UserRecord(record: String)
  case class UserId(id: Int)


  sealed trait LinkedList[A] {
    def map[B](fn: A => B): LinkedList[B] =
      this match {
        case Pair(hd, tl) => Pair(fn(hd), tl.map(fn))
        case End() => End[B]()
      }

  }
  final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]


  final case class End[A]() extends LinkedList[A]
}

import java.lang.Character.isUpperCase

import MapSequence._


val userRecord = Map(1 -> UserRecord("user record 1"), 2 -> UserRecord("user record 2"), 3 -> UserRecord("user record 3"))
val list = Pair(UserId(1), Pair(UserId(2), Pair(UserId(3), End())))

userRecord(1)
list.map(x => userRecord(x.id).record)


def upperCase(list: List[String]): List[String] = list match {
  case ::(head, next) => head.toUpperCase :: upperCase(next)
  case Nil => Nil
}

val list1 = List("ben", "dawn", "heath")
upperCase(list1)
list1.map(_.toUpperCase()).map(_.substring(0, 3))

case class Phone(name: String, models: List[String])

val apple = Phone("Apple", List("Iphone x", "Iphone 8"))
val google = Phone("Google", List("Pixel 5", "Pixel 6"))
val samsung = Phone("Samsung", List("S22", "S22 ultra"))

val phoneList = List(apple, google, samsung)

phoneList.map(_.models)
phoneList.flatMap(_.models)


val num1 = {
  println("run")
  val x = 1
  val y = 2
  x + y
}
num1
num1
num1

def rockPaperScissors(player1: String, player2: String): Int = (player1.toUpperCase, player2.toUpperCase) match {
  case ("rock", "Scissors") | ("Scissors", "paper") | ("paper", "rock")  => + 1
  case _                     => - 1
}
def whichComic(hero: String): String = hero match {
  case "Spider-Man" | "Iron Man" => "Marvel"
  case "Batman" | "SuperMan"     => "DC"
  case _ => "Unknown"
}
println(rockPaperScissors("rock", "paper"))
println(rockPaperScissors("rock", "Scissors"))

println(whichComic("Spider-Man"))

def containsExactlyOnce(str: String, fn: Char => Boolean): Boolean = {
  val x = str.indexWhere(fn)
  val y = str.lastIndexWhere(fn)
  x >= 0 && y >= 0 && x == y
}
containsExactlyOnce("heLlo", isUpperCase)
"heLlo".indexWhere(isUpperCase, 3)
"heLlo".lastIndexWhere(isUpperCase, 3)