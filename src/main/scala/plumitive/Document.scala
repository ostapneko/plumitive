package plumitive

object Document {
  case class Id(unId: String)
  case class Title(unTitle: String)
  case class Tag(unTag: String)
  case class Date(month: Option[Month], year: Int)
  case class Sender(unSender: String)
  case class Recipient(unRecipient: String)

  sealed trait Month {
    def toInt: Int
    def name: String
  }

  object Month {
    def fromInt(int: Int): Month = int match {
      case 1 => January
      case 2 => February
      case 3 => March
      case 4 => April
      case 5 => May
      case 6 => June
      case 7 => July
      case 8 => August
      case 9 => September
      case 10 => October
      case 11 => November
      case 12 => December
      case _ => throw new RuntimeException(s"Month $int does not exist")
    }

    def fromName(name: String): Option[Month] = name.toLowerCase match {
      case "january"   => Some(January)
      case "february"  => Some(February)
      case "march"     => Some(March)
      case "april"     => Some(April)
      case "may"       => Some(May)
      case "june"      => Some(June)
      case "july"      => Some(July)
      case "august"    => Some(August)
      case "september" => Some(September)
      case "october"   => Some(October)
      case "november"  => Some(November)
      case "december"  => Some(December)
      case _ => None
    }
  }

  case object January extends Month {
    override def toInt: Int = 1
    override def name: String = "January"
  }
  case object February extends Month {
    override def toInt: Int = 2
    override def name: String = "February"
  }
  case object March extends Month {
    override def toInt: Int = 3
    override def name: String = "March"
  }
  case object April extends Month {
    override def toInt: Int = 4
    override def name: String = "April"
  }
  case object May extends Month {
    override def toInt: Int = 5
    override def name: String = "May"
  }
  case object June extends Month {
    override def toInt: Int = 6
    override def name: String = "June"
  }
  case object July extends Month {
    override def toInt: Int = 7
    override def name: String = "July"
  }
  case object August extends Month {
    override def toInt: Int = 8
    override def name: String = "August"
  }
  case object September extends Month {
    override def toInt: Int = 9
    override def name: String = "September"
  }
  case object October extends Month {
    override def toInt: Int = 10
    override def name: String = "October"
  }
  case object November extends Month {
    override def toInt: Int = 11
    override def name: String = "November"
  }
  case object December extends Month {
    override def toInt: Int = 12
    override def name: String = "December"
  }
}

import Document._
case class Document(
    id: Option[Id],
    title: Title,
    scannedText: Option[String],
    tags: Set[Tag],
    date: Date,
    sender: Sender,
    recipients: Set[Recipient]
  )
