object ConvertingMethodsToFunctions {
  // Conver􏰀ng methods to func􏰀ons
  // Scala contains another feature that is directly relevant to this sec􏰀on—the
  // ability to convert method calls to func􏰀ons.
  // This is closely related to placeholder syntax—simply follow a method with an underscore:

  object Sum {
    def sum(x: Int, y: Int): Int = x + y
  }
  val c = Sum.sum(1, 2)
  val d = Sum.sum _ // converted to a function

  //In situa􏰀ons where Scala can infer that we need a func􏰀on,
  // we can even drop the underscore and simply write the method
  // name—the compiler will promote the method to a func􏰀on automa􏰀cally:

  //object MathStuff {
  //  def add1(num: Int) = num + 1
  //}
  //Counter(2).adjust(MathStuff.add1)
}
import ConvertingMethodsToFunctions._
d(1, 2)
c