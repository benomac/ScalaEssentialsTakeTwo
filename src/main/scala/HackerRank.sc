def f(n: Int): String = n == 0 match {
  case false =>
    println("Hello World")
    f(n - 1)
  case true => ""
}

f(2)