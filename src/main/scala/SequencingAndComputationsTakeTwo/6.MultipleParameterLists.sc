object MultipleParamLists {
  def example(x: Int)(y: Int) = x + y
}
import MultipleParamLists._

example(1)(2)
