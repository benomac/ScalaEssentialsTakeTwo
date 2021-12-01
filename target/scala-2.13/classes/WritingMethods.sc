object calc {
  def square(d: Double): Double = d * d

  def cube(e: Double): Double = e * square(e)

}

object calc2 {
  def square(d: Double): Double = d * d
  def cube(e: Double): Double = e * square(e)

  def square(d: Int): Int = d * d
  def cube(e: Int): Int = e * square(e)
}

def newSquareMethod(i: Double): Double =
  i * i

println(assert(newSquareMethod(2.0) == 4.0))
println(assert(newSquareMethod(3.0) == 9.0))
println(assert(newSquareMethod (-2.0) == 4.0))
val f = if(1 > 2) 5 else "dog"
f.getClass
if(false) "hello"

if(true) "hello"