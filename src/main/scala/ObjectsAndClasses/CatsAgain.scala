package ObjectsAndClasses

object CatsAgain {
  case class Cat(colour: String, food: String)

  val Oswald = new Cat("Black", "Milk")
  val Henderson = new Cat("Ginger", "Chips")
  val Quentin = new Cat("Tabby and white", "Chips")

  object ChipShop {
    def willServe(cat: Cat): Boolean =
      if (cat.food == "Chips") true
      else
        false

    def willServeTwo(cat: Cat): Boolean = cat match {
      case Cat(_, "Chips") => true
      case Cat( _, _) => false
    }
  }

  println(ChipShop.willServe(Oswald))
  println(ChipShop.willServeTwo(Quentin))

}
