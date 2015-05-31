package plumitive

import plumitive.Document._

object Fixtures {
  val testDocument =
    Document(
      id = Some(Id("1234")),
      scannedText = Some("this is a scanned text"),
      tags = Set(Tag("tag1"), Tag("tag2")),
      date = Date(Some(January), 2015),
      sender = Sender("HSBC"),
      recipients = Set(Recipient("Me"), Recipient("Myself"))
    )
}
