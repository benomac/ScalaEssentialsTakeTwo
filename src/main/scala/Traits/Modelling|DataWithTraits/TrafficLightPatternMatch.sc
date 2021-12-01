object TrafficLightPoly {

  sealed trait TrafficLight {
    def next: TrafficLight
  }

  case object Red extends TrafficLight {
    override def next: TrafficLight = Green
  }
  case object Green extends TrafficLight {
    override def next: TrafficLight = Yellow
  }
  case object Yellow extends TrafficLight {
    override def next: TrafficLight = Red
  }

}
TrafficLightPoly.Red.next

object TrafficLightPattern {

  sealed trait TrafficLight {
    def next: TrafficLight = this match {
      case Red => Green
      case Green => Yellow
      case Yellow => Red
    }
  }
  case object Red extends TrafficLight
  case object Green extends TrafficLight
  case object Yellow extends TrafficLight

}
TrafficLightPattern.Red.next