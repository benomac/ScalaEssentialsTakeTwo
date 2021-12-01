object ModellingDataWithTraits {
  // In this section we're going to look at modelling data and learn a process for expressing
  // in Scala any data model defined in terms of logical ors and ands.
  // Using the terminology of oop, we will express 'is-a' and 'has-a' relationships.
  // In the terminology of functional programming, we are learning about sum and product types,
  // which together called 'algebraic data types'.

  // Our goal in this section is to see how to translate a data model into Scala code.



  // Our first pattern is to model data that contains other data. We might describe this as
  // " 'A' HAS A 'B' AND A 'C' ". For example, a Cat has a colour and a favourite food;
  // a Visitor has an id and a creation date; and so on.

 // The way we write this is to use a case class.

 // If 'A' has a 'b' (with type 'B') and a 'c' (with type 'C') write

  // THE PRODUCT TYPE PATTERN
  // "has-a and"
  //ex
   case class A(b: B, c: C)

  //or

  //ex
  trait A {
    def b: B

    def c: C
  }


  // The Sum Type Pattern
  // "is-a or"
  // Our next pattern is to model data that is two or more distinct cases.
  // We might describe this as "A is a B or C". For example, a Feline is a Cat, Lion, or Tiger;
  // a Visitor is an Anonymous or a User; and so on.

  // We write this using the sealed trait / final case class pattern.

  // If A is a B or C write:
  //ex
  sealed trait A
  final case class B() extends A
  final case class C() extends A


  //THE "is-a and" pattern

  // The "is-a and" pattern means that A is a B and C. This is in some ways the inverse of the sum type pattern,
  // and we can implement it as.
  // ex
  trait B
  trait C
  trait A extends B with C



  //The "has-a or" patterns means that A has a B or a C. There are 2 ways we can
  // implement this. We can say that A has a d of type D, where D is a B or a C. We
  // can mechanically apply our 2 patterns to implement this.

  //ex
  trait A {
    def d: D
  }
  sealed trait D
  final case class B() extends D
  final case class C() extends D


}