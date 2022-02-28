
// Scala contains another feature that is directly relevant to this sec􏰀on—the abil-
// ity to convert method calls to func􏰀ons. This is closely related to placeholder
// syntax—simply follow a method with an underscore:

object ConvertMethodsToFunctions {
  def sum(x: Int, y: Int) = x + y
}

// ConvertMethodsToFunctions.sum

//missing argument list for method sum in object ConvertMethodsToFunctions
//Unapplied methods are only converted to functions when a function type is expected.
//You can make this conversion explicit by writing `sum _` or `sum(_,_)` instead of `sum`.
//ConvertMethodsToFunctions.sum

ConvertMethodsToFunctions.sum _


object MathsStuff {
  def add1(num: Int) = num + 1
}

