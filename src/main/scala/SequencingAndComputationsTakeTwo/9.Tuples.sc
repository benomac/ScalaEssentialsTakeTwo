Tuple2("hi", 1) //or
("hi", 1) // sugared syntax
("hi", 1, true).getClass // == Tuple3

def tupilized[A, B](in: (A, B)) = in._1
tupilized((1, "d"))

// you can pattern match on tuples too.
("a", 1) match {
  case (a, b) => a
}
val x = (1, "b", true)

x._1
x._2
x._3