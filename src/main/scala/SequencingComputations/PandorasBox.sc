object PandorasBox {
  final case class Box[A](value: A)
  def generic[A](in: A): A = in
}

PandorasBox.Box(2)

res0.value

PandorasBox.Box("hello")

res2.value



PandorasBox.generic[String]("hello")
PandorasBox.generic(1)