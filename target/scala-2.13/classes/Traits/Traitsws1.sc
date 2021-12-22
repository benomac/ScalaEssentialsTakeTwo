import java.util.Date

// Traits are templates for creating classes, in the same way that classes are templates
// for creating objects. Traits allow us to express that two or more classes can be
// considered the same, and thus both implement the same operations.

//In other words, traits allow us to express that multiple classes share a common
// super-type (outside of the Any super-type that all classes share.
sealed trait Visitor {
  def id: String // Unique id assigned to each user
  def createdAt: Date // Date this user first visited the sit
  // How long has the user been around?
  def age: Long = new Date().getTime - createdAt.getTime // this method is implemented in the trait itself


}

def older(v1: Visitor, v2: Visitor): Boolean =
  v1.createdAt.before(v2.createdAt)
// def id: and def createdAt:, from trait, are implemented by the constructor of each class.
// Anonymous and User are SUBTYPES of the trait Visitor (by using the 'extends' keyword

// Consider making subtypes final if there is no case for extending them
// Remember subtypes must be defined in the same file as a sealed trait.
final case class Anonymous(id: String, createdAt: Date = new Date()) extends Visitor

final case class User(
                 id: String,
                 email: String,
                 createdAt: Date = new Date()
               ) extends Visitor

val foo = Anonymous("ben")
val bar = User("dawn", "email@email.com")
older(foo, bar)
foo.createdAt
bar.createdAt

def missingCase(v: Visitor) =
  v match {
    case User(_, _, _) => "Got a user"
  }

//Sealed traits and final (case) classes allow us to control extensibility of types.
// The majority of cases should use the sealed trait / final case class pa􏰂ern.

//sealed trait TraitName { ... }
//final case class Name(...) extends TraitName

// The main advantages of this pattern are:

// * The compiler will warn is we miss a case in pattern matching; and
// * we can control extension points of sealed traits and thus make stronger guarantees about the behaviour
//   of subtypes.