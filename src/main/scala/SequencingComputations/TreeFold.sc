object foldPattern {

  sealed trait LinkedList[A] {



    //• end has no parameters (as End stores no values) and returns B.
    // Thus its type is () => B, which we can op􏰀mise to just a value of type B; and
    //• pair has two parameters, one for the list head and one for the tail.
    // The argument for the head has type A, and the tail is recursive and thus has type B.
    // The final type is therefore (A, B) => B.

    def fold[B](end: B, pair: (A, B) => B): B =
      this match {
        case End() => end
        case Pair(hd, tl) => pair(hd, tl.fold(end, pair))
      }

  }
  final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
  final case class End[A]() extends LinkedList[A]
  val b = (a: Int) => a * 10
  val list = Pair(1, Pair(2, Pair(3, Pair(4, End()))))
  val stringList = Pair("ben", Pair("dawn", Pair("heath", Pair("sammie", Pair("rock", End())))))

}






object TreeFold {
  sealed trait Tree[A] {
    def fold[B](node: (B, B) => B, leaf: A => B): B =
      this match {
        case Node(left, right) => node(left.fold(node, leaf), right.fold(node, leaf))
        case Leaf(value) => leaf(value)
      }
  }

  final case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A] {

//    override def fold[B](node: (B, B) => B, leaf: A => B): B =
//      node(left.fold(node, leaf), right.fold(node, leaf))

  }

  final case class Leaf[A](value: A) extends Tree[A] {

//    override def fold[B](node: (B, B) => B, leaf: A => B): B =
//      leaf(value)

  }
  val newTree = Node(Leaf(1), Node(Leaf(2), Leaf(3)))
  val newStringTree = Node(Node(Leaf("ben"), Leaf("dawn")), Node(Leaf("heath"), Leaf("sammie")))
  val tree: Tree[String] =
    Node(Node(Leaf("To"), Leaf("iterate")),
      Node(Node(Leaf("is"), Leaf("human,")),
        Node(Leaf("to"), Node(Leaf("recurse"), Leaf("divine")))))
}
import TreeFold._
import foldPattern._
println(foldPattern.b(100))
println(stringList.fold[String]("", (a, b) => s"$a, $b") + s"are one big happy family")

println(newTree.fold[Int]((a, b) => a + b, int => int))
println(newTree.fold[Tree[Int]]((a, b) => Node(a, b), int => Leaf(int * 2)))
println(newStringTree.fold[Tree[String]]((a, b) => Node(a, b), str => Leaf(str + str)))
println(tree.fold[String]((a, b) => s"$a $b", str => str))
println(tree.fold[String]((a, b) => s"$a $b", str => str.toUpperCase()))
println(tree.fold[String]((a, b) => s"$a $b", str => str.toList.map(x => x.toInt).mkString))

//def double(tree: Tree): Tree = tree match {
//  case Node(left, right) => Node(left.double, right.double)
//  case Leaf(element) => Leaf(element * 2)
//}

