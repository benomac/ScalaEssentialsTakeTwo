case class Person(firstName: String, lastName: String) {
  def name = s"$firstName $lastName"
}

object Person {
  def apply(wholeName: String): Person = {
    val nameSplit = wholeName.split(" ")
    new Person(nameSplit(0), nameSplit(1))
  }
}

val foo = Person.apply("ben mcallister")
val bar = Person.apply("ben", "macallister")

foo
bar.lastName

object Stormtrooper {
  def inspect(person: Person): String = person match{
    case Person("Luke", "Skywalker") => "Stop, rebel scum!"
    case Person("Han", "Solo") => "Stop, rebel scum!"
    case Person(firstName, lastName) => s"Move along, $firstName"
  }
}

Stormtrooper.inspect(foo)