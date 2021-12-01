import ObjectsAndClasses.Alien

"hello world"

"hello, world".toUpperCase

2.max(8)

1 + 2
"3".toInt

"ehllo" take 2
"ehllo".take(2)
"Hello my name is Ben".split(" ").toList.head
"foo" take 1

1.+(2).+(3).getClass

"the\nusual\tescape characterply"

object Test {
  def name: String = "Probably the best object ever"
}
Test.name
val foo = Test
foo.name

object Test3 {
  def hello(name: String) = "Hello " + name
}
Test3.hello("ben")

object Test4 {
  val name = "Ben"
  def hello(other: String): String =
    name + " says hi to " + other
}
Test4.hello("dawn")

object Test7 {
  val simpleField = {
    println("Evaluating simpleField")
    42
  }
  def noParameterMethod = {
    println("Evaluating noParameterMethod")
    42
  }
}

Test7.simpleField
Test7.simpleField

Test7.noParameterMethod
Test7.noParameterMethod

object argh {
  def a = {
    println("a")
    1
  }
  val b = {
    println("b")
    a + 2
  }
  def c = {
    println("c")
    a
    b + "c"
  }
}

argh.c + argh.b + argh.a

object Person {
  val firstName: String = "Ben"
  val lastName: String = "Mcallister"
}

object Alien {
  def greet(p: ObjectsAndClasses.Person.type) = {
    s"Greetings Earthling ${p.firstName}, ${p.lastName}"
  }
}

println(Alien.greet(ObjectsAndClasses.Person).getClass)

