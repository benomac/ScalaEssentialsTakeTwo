object Shapes {

  sealed trait Shape {
    def sides: Int

    def perimeter: Double

    def area: Double

    def colour: Colour
  }
  sealed trait Rectangular extends Shape {
    val sides: Int = 4

    def width: Double

    def height: Double

    override def perimeter: Double = (height * 2) + (width * 2)

    override def area: Double = width * height
  }

  sealed trait Colour {
    def red: Int
    def green: Int
    def blue: Int

    def isLight: Boolean = ((red + green + blue) / 3) > (255 / 2)
    def isDark: Boolean = !isLight
  }

  object Red extends Colour {
    val red = 255
    val green = 0
    val blue = 0
  }
  object Yellow extends Colour {
    val red = 255
    val green = 255
    val blue = 0
  }
  object HotPink extends Colour {
    val red = 255
    val green = 105
    val blue = 180
  }
  case class CustomColour(red: Int, green: Int, blue: Int) extends Colour

  case class Circle(radius: Double, colour: Colour) extends Shape {
    val sides: Int = 1
    val perimeter = 2 * math.Pi * radius
    val area = math.Pi * radius * radius
  }

  case class Rectangle(height: Double, width: Double, colour: Colour) extends Rectangular

  case class Square(size: Double, colour: Colour) extends Rectangular {
    val height = size
    val width = size
  }


  object Draw {
    def apply(shape: Shape): String = shape match {
      case Square(size, colour) => s"A ${Draw(colour)} square with a size of $size"
      case Circle(radius, colour) => s"A ${Draw(colour)} circle with a radius of $radius"
      case Rectangle(width, height, colour) => s"A ${Draw(colour)} rectangle with a width if $width and height of $height"
      case _ => "Not a valid shape"
    }

    def apply(colour: Colour) = colour match {
      case Red => "red"
      case Yellow => "yellow"
      case HotPink => "hot pink mofo!"
      case colour => if(colour.isLight) "light" else "dark"
    }
  }
}


Shapes.Draw(Shapes.Rectangle(1, 2, Shapes.CustomColour(1, 2, 3)))
Shapes.Draw(Shapes.Red)
