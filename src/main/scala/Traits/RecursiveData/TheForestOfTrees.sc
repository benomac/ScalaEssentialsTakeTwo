object TheForestOfTrees {
  sealed trait Tree {
    //pattern matching on the base trait
    def sum: Int = this match {
      case Node(left, right) => left.sum + right.sum
      case Leaf(element) => element
    }

    def double: Tree = this match {
      case Node(left, right) => Node(left.double, right.double)
      case Leaf(element) => Leaf(element * 2)
    }
    def sumPoly: Int

    def doublePoly: Tree
  }
  // A Tree is a Node, with a left and a right tree or...
  final case class Node(left: Tree, right: Tree) extends Tree {
    // polymorphism
    override def sumPoly: Int = left.sum + right.sum

    override def doublePoly: Tree = Node(left.double, right.double)
  }
  // with a leaf of type Int
  final case class Leaf(element: Int) extends Tree {
    override def sumPoly: Int = element

    override def doublePoly: Tree = Leaf(element * 2)
  }

  object TreeOps {
    //pattern matching on an object
    def sum(tree: Tree): Int = tree match {
      case Node(left, right) => left.sum + right.sum
      case Leaf(element) => element
    }
    def double(tree: Tree): Tree = tree match {
      case Node(left, right) => Node(left.double, right.double)
      case Leaf(element) => Leaf(element * 2)
    }
  }

  val aTree = Node(Leaf(3), Node(Leaf(2), Leaf(3)))


}
TheForestOfTrees.aTree.sum
TheForestOfTrees.aTree.double
TheForestOfTrees.TreeOps.sum(TheForestOfTrees.aTree)