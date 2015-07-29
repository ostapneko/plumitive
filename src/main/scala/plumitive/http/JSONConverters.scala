package plumitive.http

import argonaut.Argonaut._
import argonaut._
import plumitive.Document

import scala.util.Try

object JSONConverters {
  implicit def DocumentEncodeJson: EncodeJson[Document] =
    EncodeJson( d =>
      ("id" := d.id.map(_.unId)) ->:
      ("title" := d.title.unTitle) ->:
      ("scannedText" := d.scannedText) ->:
      ("tags" := d.tags.map(_.unTag)) ->:
      ("month" := d.date.month.map(_.name)) ->:
      ("year" := d.date.year) ->:
      ("sender" := d.sender.unSender) ->:
      ("recipients" := d.recipients.map(_.unRecipient)) ->:
      jEmptyObject
    )

  implicit def DocumentDecodeJson: DecodeJson[Document] = {
    import plumitive.Document._

    DecodeJson(c =>
      for {
        id <- (c --\ "id").as[Option[String]]
        title <- (c --\ "title").as[String]
        scannedText <- (c --\ "scannedText").as[Option[String]]
        tags <- (c --\ "tags").as[List[String]]
        month <- (c --\ "month").as[Option[String]]
        year <- (c --\ "year").as[Int]
        sender <- (c --\ "sender").as[String]
        recipients <- (c --\ "recipients").as[List[String]]
      } yield Document(
        id = id.map(Id),
        title = Title(title),
        scannedText = scannedText,
        tags = tags.map(Tag).toSet,
        date = Date(month.flatMap(Month.fromName), year),
        sender = Sender(sender),
        recipients = recipients.map(Recipient).toSet
      )
    )
  }

  implicit def DecodeImagePayload: DecodeJson[Try[ImagePayload]] =
    DecodeJson( c =>
      for {
        base64Str <- (c --\ "image").as[String]
      } yield ImagePayload.fromBase64Payload(base64Str)
    )
}
