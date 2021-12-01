package ObjectsAndClasses

class Adder(val amount: Int) {
  def apply(in: Int) = in + amount
}

object Count {

  case class Counter(count: Int = 0) {
    def inc: Counter = copy(count + 1)
    def dec: Counter = copy(count - 1)

    def inc(num: Int = 1): Counter = copy(count + num)
    def dec(num: Int = 1): Counter = copy(count - num)

    def adjust(adder: Adder): Counter = Counter(adder(count))
  }
}
