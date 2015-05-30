package gratte

object Document {
  case class Id(unId: String) extends AnyVal
  case class Tag(unTag: String) extends AnyVal
  case class Date(month: Option[Month], year: Int)
  case class Sender(unSender: String) extends AnyVal
  case class Recipient(unRecipient: String) extends AnyVal

  sealed trait Month
  case object January extends Month
  case object February extends Month
  case object March extends Month
  case object April extends Month
  case object May extends Month
  case object June extends Month
  case object July extends Month
  case object August extends Month
  case object September extends Month
  case object October extends Month
  case object November extends Month
  case object December extends Month
}

case class Document(
    id: Document.Id,
    scannedText: Option[String],
    tags: Set[Document.Tag],
    date: Document.Date,
    sender: Document.Sender,
    recipient: Document.Recipient
  )
