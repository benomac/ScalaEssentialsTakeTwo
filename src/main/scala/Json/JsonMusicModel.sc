

object Music {

  sealed trait Music {
    def print: String = {
      def quote(s: String): String = {
        '"'.toString ++ s ++ '"'.toString
      }
      def noteCellToJson(noteCell: NoteCell): String = {
        noteCell match {
          case NoteCell(k, v, t @ NoteCell(_, _, _)) => s"${quote(k.toString)}: ${noteProperties(v)}, ${noteCellToJson(t)}"
          case NoteCell(k, v, NoteEnd)               => s"${quote(k.toString)}: ${noteProperties(v)}"
        }
      }
      def chordToJson(chord: ChordSeq): String = {
        chord match {
          case ChordSeq(h, t @ ChordSeq(_, _)) => s"${noteCellToJson(h)}, ${chordToJson(t)}"
          case ChordSeq(h, ChordEnd) => noteCellToJson(h)
        }
      }
      def noteProperties(noteProperties: NoteProperties): String =
        noteProperties match {
          case NoteProperties(_, _, SharpOrFlat(None))=>
            s"{${noteProperties.musicKey.musicKey}, " +
            s"${noteProperties.duration.duration}}"
          case _ =>
            s"{${noteProperties.musicKey.musicKey}, " +
              s"${noteProperties.duration.duration}, " +
              s"${noteProperties.sharpOrFlat.sharpOrFlat.getOrElse("")}}"
        }

      this match {
        case Pitch(pitch) => quote(pitch)
        case MusicKey(musicKey) => musicKey.toString
        case Duration(duration) => duration.toString
        case SharpOrFlat(sharpOrFlat) => if(sharpOrFlat.isEmpty) "" else quote(sharpOrFlat.get)
        case n @ NoteProperties(_, _, _) => noteProperties(n)
        case c @ ChordSeq(_, _) => chordToJson(c)
        case nc @ NoteCell(_, _, _) => noteCellToJson((nc))
        case NoteEnd => "[]"
      }


    }
  }

  final case class Pitch(pitch: String) extends Music
  final case class MusicKey(musicKey: Int) extends Music
  final case class Duration(duration: Double) extends Music
  final case class SharpOrFlat(sharpOrFlat: Option[String]) extends Music



  sealed trait NoteObject extends Music

  final case class NoteCell(key: Pitch,
                            value: NoteProperties,
                            tail: NoteObject) extends NoteObject

  final case class NoteProperties(musicKey: MusicKey,
                                  duration: Duration,
                                  sharpOrFlat: SharpOrFlat) extends NoteObject

  case object NoteEnd extends NoteObject


  sealed trait Chord extends Music

  final case class ChordSeq(Head: NoteCell, tail: Chord) extends Chord

  case object ChordEnd extends Chord


  val noteCell = NoteCell(key = Pitch("c"), NoteProperties(MusicKey(0), Duration(1.0), SharpOrFlat(None)), NoteEnd)
}

//val p = Music.Pitch("c")
//
val np = Music.NoteProperties(Music.MusicKey(0), Music.Duration(1), Music.SharpOrFlat(None))
//val nps = Music.NoteProperties(Music.MusicKey(0), Music.Duration(1), Music.SharpOrFlat(Some("#")))
np.print
//nps.print
Music.noteCell.print

