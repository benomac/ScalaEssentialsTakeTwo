object Tuples {

  val tuple2 = Tuple2("hi", 1) // de-sugared syntax
  val tuple2B = ("hi", 1)      //Sugared syntax
  val tuple3 = ("hi", 1, true)

  // We can define methods accept tuples as parameters, using the same syntax.
  def tuplized[A, B](in: (A, B)) = in._1

}

import Tuples._

tuple2.getClass
tuple2B.getClass
tuple3.getClass


// We can also pattern match on tuples as follows:
tuplized(1, "ben")
(1, 22) match {
  case (_, b) => println(b)
}

// Although pattern matching is the natural way to deconstruct a tuple,
// each class also has a complement of fields named _1, _2 and so on:
val x = (13, "b", true)
x._1
x._3

tuple2
tuple2B
tuple3