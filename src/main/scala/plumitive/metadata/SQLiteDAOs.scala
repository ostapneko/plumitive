package plumitive.metadata

import plumitive.Document
import slick.driver.SQLiteDriver.api._

case object SQLiteDAOs {
  class Documents(tag: Tag) extends Table[Document](tag, "documents") {
    import plumitive.Document._
    def fromRow(id: Option[String], title: String, scannedText: Option[String], sender: String, recipientsStr: String, year: Int, monthNum: Option[Int], tagsStr: String): Document = {
      val tags = tagsStr.split(',').map(Tag).toSet
      val recipients = recipientsStr.split(',').map(Recipient).toSet
      val month = monthNum.map(Month.fromInt)
      val date = Date(month, year)
      Document(
        id = id.map(Id),
        title = Title(title),
        scannedText = scannedText,
        date = date,
        sender = Sender(sender),
        recipients = recipients,
        tags = tags
      )
    }
    def toRow(d: Document): Option[(Option[String], String, Option[String], String, String, Int, Option[Int], String)] =
      Some((
        d.id.map(_.unId),
        d.title.unTitle,
        d.scannedText,
        d.sender.unSender,
        d.recipients.map(_.unRecipient).toSeq.mkString(","),
        d.date.year,
        d.date.month.map(_.toInt),
        d.tags.map(_.unTag).mkString(",")
      ))

    def id = column[String]("docid", O.PrimaryKey) // This is the primary key column
    def title = column[String]("title")
    def scannedText = column[String]("scanned_text")
    def sender = column[String]("sender")
    def recipients = column[String]("recipients")
    def year = column[Int]("year")
    def month = column[Int]("month")
    def tags = column[String]("tags")
    // Every table needs a * projection with the same type as the table's type parameter
    def * = (id.?, title, scannedText.?, sender, recipients, year, month.?, tags) <> ((fromRow _).tupled, toRow)
  }

  val documents = TableQuery[Documents]
}
