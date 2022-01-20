
//Methods in Scala can actually have mul􏰀ple parameter lists.
// Such methods work just like normal methods,
// except we must bracket each parameter list separately.


object MultipleParameterLists {
  def example(x: Int)(y: Int) = x + y
  val exampleAsFunction = example(1) _

//  Mul􏰀ple parameter lists have two relevant uses:
//  they look nicer when defining func􏰀ons inline and
//  they assist with type inference.
//  The former is the ability to write func􏰀ons
// that look like code blocks. For example, if we define fold as
def fold[B](end: B)(pair: (A, B) => B): B = {
  this match {
    case End() => end
    case Pair(hd, tl) => pair(hd, tl.fold(end, pair))
  }
// then we can call it as
  fold(0){ (total, elt) => total + elt }
  //which is easier to read than
  fold(0, (total, elt) => total + elt)
  //apparently!
}
// More importantly, the use of multiple parameter lists eases type inference.
// Scala can't use type inferred for one parameter for another parameter in the
// same list, eg :
def fold[B](end: B, pair: (A, B) => B): B
// If scala infers B for `end` it can't then use this inferred type for the B in `pair`,
// so we must often write type declaration on `pair`. How ever, Scala can use types
// inferred for one parameter list in another parameter list! So if we write `fold` as:
  def fold[B](end: B)(pair: (A, B) => B): B
// then inferring B from end (which is usually easy) allows B to be used when
// inferring the type `pair`. This means fewer type declarations and a smoother dev process.
}
import MultipleParameterLists._

example(1)(2)
exampleAsFunction(2)

