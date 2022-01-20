object PlaceHolderSyntax {

  // In very simple situa􏰀ons we can write inline func􏰀ons
  // using an extreme short- hand called placeholder syntax. It looks like this:

  // `placeHolder` expands to `notPlaceHolder`
  val placeHolder = (_: Int) * 2
  val notPlaceHolder = (a: Int) => a * 2

  // Here are a few more examples:
//  _+_        // expands to`(a,b)=>a+b`
//  foo(_)     // expands to `(a) => foo(a)`
//  foo(_, b)  // expands to `(a) => foo(a, b)`
//  _(foo)     // expands to `(a) => a(foo)`

}

import ObjectsAndClasses.Count.Counter
import PlaceHolderSyntax.{notPlaceHolder, placeHolder}

println(placeHolder(1))
println(notPlaceHolder(1))



