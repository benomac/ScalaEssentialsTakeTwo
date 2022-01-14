

object Music {

  sealed trait Music {
    def print: String = {

      def quote(s: String): String = {
        '"'.toString ++ s ++ '"'.toString
      }

      def extractPitch(note: Note) =
        note.key.pitch.toUpperCase

      def getSharpOrFlat[F](input: F): Option[NoteType] = input match {
        case c @ ChordSeqBuilder(_, _) => c.Head.value.sharpOrFlat.sharpOrFlat
        case n @ Note(_, _) => n.value.sharpOrFlat.sharpOrFlat
      }

      def addSharpOrFlatToString(note: Note, sharpOrFlatSign: Option[String]): String = sharpOrFlatSign match {
        case Some(_) => s"${quote(extractPitch(note) + sharpOrFlatSign.get)}:${noteProperties(note.value)}"
        case None      => s"${quote(extractPitch(note))}:${noteProperties(note.value)}"
      }

      def noteToJson(noteCell: Note): String = getSharpOrFlat(noteCell) match {
        case Some(Flat)  => addSharpOrFlatToString(noteCell, Some("b"))
        case Some(Sharp) => addSharpOrFlatToString(noteCell, Some("#"))
        case _           => addSharpOrFlatToString(noteCell, None)
      }

      def chordToJson(chord: ChordSeqBuilder): String = {
        val pitch = getSharpOrFlat(chord) match {
          case Some(Flat) => "b"
          case Some(Sharp) => "#"
          case None => ""
        }
        chord match {
          case ChordSeqBuilder(h, t @ ChordSeqBuilder(_, _)) => s"${extractPitch(h)}${pitch}, ${chordToJson(t)}"
          case ChordSeqBuilder(h, ChordEnd) => extractPitch(h)
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
        case c @ ChordSeqBuilder(h, t) => (h.value.sharpOrFlat.sharpOrFlat, t) match {
          case (Some(Flat), _) => s"{ ${quote(extractPitch(h).toUpperCase + "b")}:  [ ${chordToJson(c)} ] }"
          case (Some(Sharp), _) => s"{ ${quote(extractPitch(h).toUpperCase + "#")}:  [ ${chordToJson(c)} ] }"
          case (None, _)       => s"{ ${quote(extractPitch(h).toUpperCase())}    :  [ ${chordToJson(c)} ] }"
        }
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
  final case class Note(key: Pitch, value: NoteProperties) extends NoteObject {
    def sharp = Note(Pitch(s"${key.pitch}#"), value)
    def flat = Note(Pitch(s"${key.pitch}b"), value)
  }
  final case class NoteProperties(octave: Octave, duration: Duration, sharpOrFlat: SharpOrFlat) extends NoteObject



  sealed trait Chord extends Music
  final case class ChordSeqBuilder(Head: Note, tail: Chord) extends Chord
//  final case class ChordSeqFromName(name: Note) extends Chord {
//
//  }
  case object ChordEnd extends Chord


  val A = Note(key = Pitch("A"), NoteProperties(Octave(1), Duration(1.0), SharpOrFlat(None)))
  val C = Note(key = Pitch("C"), NoteProperties(Octave(1), Duration(1.0), SharpOrFlat(None)))
  val E = Note(key = Pitch("E"), NoteProperties(Octave(1), Duration(1.0), SharpOrFlat(None)))
  val F = Note(key = Pitch("F"), NoteProperties(Octave(1), Duration(1.0), SharpOrFlat(None)))

  val chordA = ChordSeqBuilder(A, ChordSeqBuilder(C, ChordSeqBuilder(E, ChordEnd)))

}
Music.A.sharp.print

Music.chordA.print



