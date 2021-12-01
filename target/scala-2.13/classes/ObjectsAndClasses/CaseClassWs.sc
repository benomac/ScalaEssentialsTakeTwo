
case class Person(firstName: String, lastName: String) {
  def name = s"$firstName $lastName"
}
case object Citizen {
  def firstName = "John"
  def lastName  = "Doe"
  def name = firstName + " " + lastName
}

val ben = Person("ben", "Mcallister")
val ben2 = ben.copy()
val ben3 = ben.copy(lastName = "jackson")


ben.firstName
ben == ben2 //compares values == true
ben.eq(ben2) //compares reference identity == false
ben.toString
Citizen.toString
