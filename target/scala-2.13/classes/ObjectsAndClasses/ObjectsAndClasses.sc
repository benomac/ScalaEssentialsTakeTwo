class Person {
  val firstName = "ben"
  val lastName = "omac"
  def name = s"$firstName $lastName"
}
val ben = new Person

ben.name

object alien {
  def greet(p: Person) =
    s"Greeting ${p.name}"
}

alien.greet(ben)

class AnotherPerson(first: String, last: String) {
  val firstName = first
  val lastName = last
  def name = s"$firstName $lastName"
}

class AnotherBetterPerson(val first: String, val last: String) {
  def name = s"$first $last"
}

val andy = new AnotherPerson("andy", "briggs")
andy.name


val matt = new AnotherBetterPerson(last = "Matt", first = "Vickers")

matt.name

class ATestClass {
  def doSomething(): Unit = println("something done")
}
val test = new ATestClass
test.doSomething()