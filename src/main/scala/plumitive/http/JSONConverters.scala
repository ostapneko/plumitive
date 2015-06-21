package plumitive.http

import argonaut.Argonaut._
import argonaut._
import plumitive.Document

import scala.util.Try

object JSONConverters {
  implicit def DocumentEncodeJson: EncodeJson[Document] =
    EncodeJson( d =>
      ("id" := d.id.unId) ->:
      ("title" := d.title.unTitle) ->:
      ("scannedText" := d.scannedText) ->:
      ("tags" := d.tags.map(_.unTag)) ->:
      ("month" := d.date.month.map(_.name)) ->:
      ("year" := d.date.year) ->:
      ("sender" := d.sender.unSender) ->:
      ("recipients" := d.recipients.map(_.unRecipient)) ->:
      jEmptyObject
    )

  implicit def DecodeImagePayload: DecodeJson[Try[ImagePayload]] =
    DecodeJson( c =>
      for {
        base64Str <- (c --\ "image").as[String]
      } yield ImagePayload.fromBase64Payload(base64Str)
    )
}
