package ObjectsAndClasses

object FilmDirectors {

  case class Director (
                  firstName: String,
                  lastName: String,
                  yearOfBirth: Int) {
    def name: String =
      s"$firstName $lastName"

  }

  object Director {

    def older(dir1: Director, dir2: Director): Director =
      if (dir1.yearOfBirth < dir2.yearOfBirth) dir1 else dir2

  }
  case class Film(
              name: String,
              yearOfRelease: Int,
              imdbRating: Double,
              director: Director) {
    def directorsAge: Int =
      yearOfRelease - director.yearOfBirth
    def isDirectedBy(director: Director): Boolean =
      this.director == director

  }

  object Film {

    def highestRating1(film1: Film, film2: Film): Double = {
      if (film1.imdbRating > film2.imdbRating) film1.imdbRating else film2.imdbRating
    }

    def highestRating(film1: Film, film2: Film): Double = {
      val rating1 = film1.imdbRating
      val rating2 = film2.imdbRating
      if (rating1 > rating2) rating1 else rating2
    }

    def oldestDirectorAtTheTime(film1: Film, film2: Film): Director = {
      if (film1.directorsAge > film2.directorsAge)
        film1.director
      else
        film2.director
    }
  }


    val eastwood = new Director("Clint", "Eastwood", 1930)
    val mcTiernan = new Director("John", "McTiernan", 1951)
    val nolan = new Director("Christopher", "Nolan", 1970)
    val someBody = new Director("Just", "Some Body", 1990)
    val memento = new Film("Memento", 2000, 8.5, nolan)
    val darkKnight = new Film("Dark Knight", 2008, 9.0, nolan)
    val inception = new Film("Inception", 2010, 8.8, nolan)
    val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7,
      eastwood)
    val outlawJoseyWales = new Film("The Outlaw Josey Wales", 1976, 7.9,
      eastwood)
    val unforgiven = new Film("Unforgiven", 1992, 8.3, eastwood)
    val granTorino = new Film("Gran Torino", 2008, 8.2, eastwood)
    val invictus = new Film("Invictus", 2009, 7.4, eastwood)
    val predator = new Film("Predator", 1987, 7.9, mcTiernan)
    val dieHard = new Film("Die Hard", 1988, 8.3, mcTiernan)
    val huntForRedOctober = new Film("The Hunt for Red October", 1990,
      7.6, mcTiernan)
    val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8,
      mcTiernan)


    println(highPlainsDrifter.copy(name = "L'homme des hautes plaines").name)
    // res19: Film = Film(L'homme des hautes plaines,1973,7.7,Director( Clint,Eastwood,1930))
    thomasCrownAffair.copy(yearOfRelease = 1968, director = new Director("Norman", "Jewison", 1926))
    // res20: Film = Film(The Thomas Crown Affair,1968,6.8,Director(Norman ,Jewison,1926))
    inception.copy().copy().copy()
    // res21: Film = Film(Inception,2010,8.8,Director(Christopher,Nolan ,1970))


    println(Director.older(eastwood, mcTiernan).name)
    println(Film.highestRating(unforgiven, granTorino))
    println(Film.highestRating1(unforgiven, granTorino))



}
