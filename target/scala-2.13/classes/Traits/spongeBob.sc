import scala.annotation.tailrec

val str: String = "You cant do that here!"
val str2: String = "s"


def mirror(s: String, acc: String = ""): String = {
  if (s.isEmpty) acc
  else mirror(s.take(s.length - 1), acc + s.last)
}

def mirrorMatch(s: String, acc: String = ""): String = s match {
  case "" => acc
  case _ => mirror(s.take(s.length - 1), acc + s.last)
}
def spongeBob(s: String, acc: String = "", bool: Boolean = true): String = (s, bool) match {
  case ("", _) => acc
  case (_, false) => spongeBob(s.drop(1), acc + s.head.toLower)
  case (_, true) => spongeBob(s.drop(1), acc + s.head.toUpper, bool = false)
  case (" ", _) => spongeBob(s.drop(1), acc)
}

@tailrec
def isPalindrome(s: String): Boolean = {
  if (s.isEmpty || s.length == 1) true
  else
    if (s.head == s.last) isPalindrome(s.slice(1, s.length - 1))
    else
      false
}


isPalindrome("ttttttttttttttttttttttttttttttttttttttttttttttttttttttt")
mirror(str)
mirrorMatch(str)
spongeBob(str)
val foo = "benny"
foo.slice(1, foo.length - 1)

