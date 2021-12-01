object Objects extends App {

  object Oswald {
    val colour: String = "Black"
    val food: String = "Milk"
  }

  object Henderson {
    val colour: String = "Ginger"
    val food: String = "Chips"
  }

  object Quentin {
    val colour: String = "Tabby and white"
    val food: String = "Curry"
  }

  object calc {
    def square(d: Double): Double = d * d

    def cube(e: Double): Double = e * square(e)

  }
  val foo: Int = 2
  println(calc.square(foo))
  println(calc.cube(11))

  object calc2 {
    def square(d: Double): Double = d * d
    def cube(e: Double): Double = e * square(e)

    def square(d: Int): Int = d * d
    def cube(e: Int): Int = e * square(e)
  }
  println(calc2.square(2.3))
  println(calc2.cube(11))


}
