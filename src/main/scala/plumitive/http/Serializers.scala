package plumitive.http

import argonaut._, Argonaut._
import plumitive.Document

object Serializers {
  implicit def DocumentEncodeJson: EncodeJson[Document] =
    EncodeJson( d =>
      ("id" := d.id.map(_.unId)) ->:
      ("scannedText" := d.scannedText) ->:
      ("tags" := d.tags.map(_.unTag)) ->:
      ("month" := d.date.month.map(_.toString)) ->:
      ("year" := d.date.year) ->:
      ("sender" := d.sender.unSender) ->:
      ("recipients" := d.recipients.map(_.unRecipient)) ->:
      jEmptyObject
    )
}
