package ObjectsAndClasses

import ObjectsAndClasses.FilmDirectors.{Director, Film}

object Dad {
  def rate(film: Film): Double = film match {
    case Film(_, _, _, Director("Clint", "Eastwood", _)) => 10.0
    case Film(_, _, _, Director("John", "McTiernan", _)) => 7.0
    case Film(_, _, _, _) => 3.0
  }
}
