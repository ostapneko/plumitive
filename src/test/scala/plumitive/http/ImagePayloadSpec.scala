package plumitive.http

import akka.http.scaladsl.model.MediaTypes
import org.scalatest.FreeSpec

class ImagePayloadSpec extends FreeSpec {
  // "abc" in UTF-8 == "YWJj" in base64
  "fromBase64Payload" - {
    "parses the base64 payload into the image chars and the media type" - {
      val Right(ImagePayload(bytes, mediaType)) = ImagePayload.fromBase64Payload("data:image/png;base64,YWJj")

      assertResult("abc".getBytes)(bytes)
      assertResult(MediaTypes.`image/png`)(mediaType)
    }
  }
}
