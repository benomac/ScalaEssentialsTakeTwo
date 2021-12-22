

object json {
  sealed trait Json {
    def print: String = {
      def quote(s: String): String =
        '"'.toString ++ s ++ '"'.toString
      def seqToJson(seq: SeqCell): String =
        seq match {
          case SeqCell(h, t @ SeqCell(_, _)) =>
            s"${h.print}, ${seqToJson(t)}"
          case SeqCell(h, SeqEnd) => h.print
        }
      def objectToJson(obj: ObjectCell): String =
        obj match {
          case ObjectCell(k, v, t @ ObjectCell(_, _, _)) => s"${quote(k)}: ${v.print}, ${objectToJson(t)}"
          case ObjectCell(k, v, ObjectEnd) =>
            s"${quote(k)}: ${v.print}"
        }
      this match {
        case JsNumber(v) => v.toString
        case JsString(v) => quote(v)
        case JsBoolean(v) => v.toString
        case JsNull => "null"
        case s @ SeqCell(_, _) => "[" ++ seqToJson(s) ++ "]"
        case SeqEnd => "[]"
        case o @ ObjectCell(_, _, _) => "{" ++ objectToJson(o) ++ "}"
        case ObjectEnd => "{}"
      }

    }
  }
  final case class JsNumber(value: Double) extends Json
  final case class JsString(value: String) extends Json
  final case class JsBoolean(value: Boolean) extends Json
  case object JsNull extends Json
  sealed trait JsSequence extends Json
  final case class SeqCell(head: Json, tail: JsSequence) extends
    JsSequence
  case object SeqEnd extends JsSequence
  sealed trait JsObject extends Json
  final case class ObjectCell(key: String, value: Json, tail: JsObject ) extends JsObject
  case object ObjectEnd extends JsObject

  val foo = SeqCell(JsString("a string"), SeqCell(JsNumber(1.0), SeqCell(JsBoolean(true), SeqEnd))).print
  // res0: String = ["a string", 1.0, true]
  val bar = ObjectCell(
    "a", SeqCell(JsNumber(1.0), SeqCell(JsNumber(2.0), SeqCell(JsNumber
    (3.0), SeqEnd))),
    ObjectCell(
      "b", SeqCell(JsString("a"), SeqCell(JsString("b"), SeqCell( JsString("c"), SeqEnd))),
      ObjectCell(
        "c", ObjectCell("doh", JsBoolean(true),
          ObjectCell("ray", JsBoolean(false), ObjectCell("me", JsNumber(1.0), ObjectEnd))),
        ObjectEnd )
    ) ).print

}
println(json.foo)
println(json.bar)
