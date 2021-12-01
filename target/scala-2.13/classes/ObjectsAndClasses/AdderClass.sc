

class Adder(val amount: Int) {
  def apply(in: Int) = in + amount
}

object Count {

  class Counter(val count: Int) {
    def inc: Counter = new Counter(count + 1)
    def dec: Counter = new Counter(count - 1)

    def inc(num: Int = 1): Counter = new Counter(count + num)
    def dec(num: Int = 1): Counter = new Counter(count - num)

    def adjust(adder: Adder): Counter = new Counter(adder.apply(count))
  }
}

val add3 = new Adder(3)
val c = new Count.Counter(1)

c.adjust(add3).count

val addNum = new Adder(3)
addNum.apply(2)
addNum(4)

val addAdd = addNum
addAdd(9)
add3.apply(2)
add3(4)