import scala.annotation.tailrec

case class Colour(red: Int, green: Int, blue: Int) {

  def lightness: Int =
    (red + blue + green) / 3

  def lighten(amount: Int): Colour =
    this.copy(red + amount, green + amount, blue + amount)

  def leftPad2(text: String, length: Int): String =
    if(text.length >= length) text
    else
      leftPad("0" + text, length)

  def leftPad(text: String, len: Int): String = {
    @tailrec
    def help(t: String, l: Int, acc: String): String = {
      if (t.length >= l) acc
      else
        help(t, l - 1, s"0" + acc)
    }
    help(text, len, text)
  }


  //#c80a02

  def hexValue =
    s"#${leftPad(red.toHexString, 2)}${leftPad(green.toHexString, 2)}${leftPad(blue.toHexString, 2)}"


}

val red = Colour(200, 10, 2)
println(red.lighten(10))
red.hexValue

