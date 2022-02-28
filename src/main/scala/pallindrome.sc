object Palindrome {
  def isPalindrome(word: String): Boolean = word match {
    case word if word.length <= 1 => true
    case word if word.head == word.last => isPalindrome(word.drop(1).dropRight(1))
    case _ => false
  }
}
"ben".drop(1).dropRight(1)
val f = "beb"

f.head == f.last
import Palindrome._

isPalindrome(("beb"))
isPalindrome(("beeb"))
isPalindrome("safliudscbl")