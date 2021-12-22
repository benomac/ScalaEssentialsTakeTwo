// Implementing dinner method, using polymorphism

object FelineADT {

  sealed trait Feline {
    def dinner: Food
  }

  final case class Lion() extends Feline {
    override def dinner: Food = Antelope
  }
  final case class Tiger() extends Feline {
    override def dinner: Food = TigerFood
  }
  final case class Panther() extends Feline {
    override def dinner: Food = Licorice
  }
  final case class Cat(favouriteFood: String) extends Feline {
    override def dinner: Food = CatFood(favouriteFood)
  }

  sealed trait Food

  case object Antelope extends Food
  case object TigerFood extends Food
  case object Licorice extends Food
  final case class CatFood(food: String) extends Food
}
val lion = FelineADT.Lion()
lion.dinner


// Implementing dinner method, using pattern matching,
// in a single method on Feline
object FelineADTPatternMatch {

  sealed trait Feline {
    def dinner: Food = this match {
      case Lion() => Antelope
      case Tiger() => TigerFood
      case Panther() => Licorice
      case Cat(favouriteFood) => CatFood(favouriteFood)
    }

  }

  sealed trait Food

  case object Antelope extends Food
  case object TigerFood extends Food
  case object Licorice extends Food
  final case class CatFood(food: String) extends Food

  final case class Lion() extends Feline
  final case class Tiger() extends Feline
  final case class Panther() extends Feline
  final case class Cat(favouriteFood: String) extends Feline
}
val tiger = FelineADTPatternMatch.Tiger()
tiger.dinner


// Implementing dinner method, using pattern matching, in a method,
// on another object.
object FelineADTPatternMatchTwo {

  sealed trait Feline

  object Diner {
    def dinner(feline: Feline): Food = feline match {
      case Lion() => Antelope
      case Tiger() => TigerFood
      case Panther() => Licorice
      case Cat(favouriteFood) => CatFood(favouriteFood)
    }
  }
  object AnotherDiner {
    def dinner(feline: Feline): Food = feline match {
      case Lion() => TigerFood
      case Tiger() => Antelope
      case Panther() => TigerFood
      case Cat(favouriteFood) => CatFood(favouriteFood)
    }
  }

  sealed trait Food

  case object Antelope extends Food
  case object TigerFood extends Food
  case object Licorice extends Food
  final case class CatFood(food: String) extends Food

  final case class Lion() extends Feline
  final case class Tiger() extends Feline
  final case class Panther() extends Feline
  final case class Cat(favouriteFood: String) extends Feline
}

val panther = FelineADTPatternMatchTwo.Panther()
FelineADTPatternMatchTwo.Diner.dinner(panther)
FelineADTPatternMatchTwo.AnotherDiner.dinner(panther)

// Which should we use? The first two methods give the same result: a method
// defined on the classes of interest. We should use whichever is more convenient.
// This normally ends up being pa􏰂ern matching on the base trait as it requires less code duplication.
// When we implement a method in the classes of interest we can have only one
// implementation of the method, and everything that method requires to work must
// be contained within the class and parameters we pass to the method. When we
// implement methods using pa􏰂ern matching in an external object we can provide
// mul􏰀ple implementations, one per object (mul􏰀ple Diners in the example above).
//The general rule is: if a method only depends on other fields and methods in a
// class it is a good candidate to be implemented inside the class. If the method
// depends on other data (for example, if we needed a Cook to make
// dinner) consider implementing it using pattern matching outside of the classes
// in question. If we want to have more than one implementation we should use pa􏰂ern
// matching and implement it outside the classes.
