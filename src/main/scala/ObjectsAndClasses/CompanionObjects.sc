class Timestamp(val secondValue: Long)

object Timestamp {
  def apply(hours: Int, minutes: Int, seconds: Int): Timestamp =
    new Timestamp(hours*60*60 + minutes*60 + seconds)
}
val foo: Timestamp = Timestamp(1)
Timestamp.apply(1, 1, 1)
foo.secondValue