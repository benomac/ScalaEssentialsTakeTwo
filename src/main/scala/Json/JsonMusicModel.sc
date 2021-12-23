

object Music {

  sealed trait Music {
    def print: String = {

      def quote(s: String): String = {
        '"'.toString ++ s ++ '"'.toString
      }

      def extractPitchForChord(note: Note) =
        note.key.pitch.toUpperCase

      def noteToJson(noteCell: Note): String = noteCell.value.sharpOrFlat.sharpOrFlat match {
        case Some(Flat)  => s"${quote(noteCell.key.pitch.toUpperCase + "b")}:${noteProperties(noteCell.value)}"
        case Some(Sharp) => s"${quote(noteCell.key.pitch.toUpperCase + "#")}:${noteProperties(noteCell.value)}"
        case _           => s"${quote(noteCell.key.pitch.toUpperCase)}:${noteProperties(noteCell.value)}"
      }

      def chordToJson(chord: ChordSeq): String = {
        chord match {
          case ChordSeq(h, t @ ChordSeq(_, _)) => s"${noteToJson(h)}, ${chordToJson(t)}"
          case ChordSeq(h, ChordEnd) => noteToJson(h)
        }
      }

      def noteProperties(noteProperties: NoteProperties): String =
        s" [${noteProperties.octave.octave}, ${noteProperties.duration.duration}]"

      this match {
        case Pitch(pitch) => quote(pitch)
        case Octave(musicKey) => musicKey.toString
        case Duration(duration) => duration.toString
        case SharpOrFlat(sharpOrFlat) => if(sharpOrFlat.isEmpty) "" else quote(sharpOrFlat.get.toString)
        case n @ Note(_, _) => s"{ ${noteToJson(n)} }"
        case c @ ChordSeq(h, _) => h.value.sharpOrFlat.sharpOrFlat match {
          case Some(Flat)  => s"{ ${quote(extractPitchForChord(h).toUpperCase + "b")}:  { ${chordToJson(c)} } }"
          case Some(Sharp) => s"{ ${quote(extractPitchForChord(h).toUpperCase + "#")}:  { ${chordToJson(c)} } }"
          case None        => s"{ ${quote(extractPitchForChord(h).toUpperCase())}    :  { ${chordToJson(c)} } }"
        }
        case nc @ Note(_, _) => noteToJson(nc)
      }

    }
  }

  final case class Pitch(pitch: String) extends Music
  final case class Octave(octave: Int) extends Music
  final case class Duration(duration: Double) extends Music
  final case class SharpOrFlat(sharpOrFlat: Option[NoteType]) extends Music



  sealed trait NoteType extends Music
  case object Flat extends NoteType
  case object Sharp extends NoteType

  sealed trait NoteObject extends Music
  final case class Note(key: Pitch, value: NoteProperties) extends NoteObject
  final case class NoteProperties(octave: Octave, duration: Duration, sharpOrFlat: SharpOrFlat) extends NoteObject



  sealed trait Chord extends Music
  final case class ChordSeq(Head: Note, tail: Chord) extends Chord
  case object ChordEnd extends Chord


  val A_# = Note(key = Pitch("a"), NoteProperties(Octave(1), Duration(1.0), SharpOrFlat(Some(Sharp))))
  val C = Note(key = Pitch("c"), NoteProperties(Octave(1), Duration(1.0), SharpOrFlat(None)))
  val E = Note(key = Pitch("e"), NoteProperties(Octave(1), Duration(1.0), SharpOrFlat(None)))
  val F = Note(key = Pitch("f"), NoteProperties(Octave(1), Duration(1.0), SharpOrFlat(None)))

  val chordA = ChordSeq(A_#, ChordSeq(C, ChordSeq(E,  ChordSeq(F, ChordEnd))))
}
Music.A_#.print

Music.chordA.print

