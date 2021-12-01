package ObjectsAndClasses

object Person extends App {
  val firstName: String = "Ben"
  val lastName: String = "Mcallister"
}

object Alien {
  def greet(p: Person.type) = {
    s"Greetings Earthling ${p.firstName}, ${p.lastName}"
  }
}


