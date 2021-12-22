

object Music {

  sealed trait Music {
    def print: String = {

      def quote(s: String): String = {
        '"'.toString ++ s ++ '"'.toString
      }

      def noteCellToJson(noteCell: NoteCell): String = {
        s"${quote(noteCell.key.pitch)}:${noteProperties(noteCell.value)}"
        }

      def chordToJson(chord: ChordSeq): String = {
        chord match {
          case ChordSeq(h, t @ ChordSeq(_, _)) => s"${noteCellToJson(h)}, ${chordToJson(t)}"
          case ChordSeq(h, ChordEnd) => noteCellToJson(h)
        }
      }

      def noteProperties(noteProperties: NoteProperties): String =
        s"{${noteProperties.octave.octave}, ${noteProperties.duration.duration}}"

      this match {
        case Pitch(pitch) => quote(pitch)
        case Octave(musicKey) => musicKey.toString
        case Duration(duration) => duration.toString
        case SharpOrFlat(sharpOrFlat) => if(sharpOrFlat.isEmpty) "" else quote(sharpOrFlat.get)
        case n @ NoteProperties(_, _, _) => noteProperties(n)
        case c @ ChordSeq(_, _) => chordToJson(c)
        case nc @ NoteCell(_, _) => noteCellToJson(nc)
        case NoteEnd => "[]"
      }

    }
  }

  final case class Pitch(pitch: String) extends Music
  final case class Octave(octave: Int) extends Music
  final case class Duration(duration: Double) extends Music
  final case class SharpOrFlat(sharpOrFlat: Option[String]) extends Music //create types for flat and sharp!



  sealed trait NoteObject extends Music

  final case class NoteCell(key: Pitch, value: NoteProperties) extends NoteObject

  final case class NoteProperties(octave: Octave,
                                  duration: Duration,
                                  sharpOrFlat: SharpOrFlat) extends NoteObject

  case object NoteEnd extends NoteObject


  sealed trait Chord extends Music

  final case class ChordSeq(Head: NoteCell, tail: Chord) extends Chord

  case object ChordEnd extends Chord


  val noteCell = NoteCell(key = Pitch("c"), NoteProperties(Octave(1), Duration(1.0), SharpOrFlat(Some("#"))))
}


Music.noteCell.print

