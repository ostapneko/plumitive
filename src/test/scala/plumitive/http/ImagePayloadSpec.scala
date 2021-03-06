package plumitive.http

import akka.parboiled2.util.Base64
import org.scalatest.FreeSpec

import scala.util.{Success, Failure}

class ImagePayloadSpec extends FreeSpec {
  "fromBase64Payload" - {
    val encoded = Base64.rfc2045().encodeToString("abc".getBytes("UTF-8"), true)
    "parses the base64 payload into the image chars and the media type" - {
      val Success(ImagePayload(bytes, _)) = ImagePayload.fromBase64Payload(s"data:image/png;base64,$encoded")

      assertResult("abc".getBytes)(bytes)
    }

    "parses the supported media types" - {
      ImagePayload.supportedMediaTypes.foreach(expMediaType => {
        val mtStr = expMediaType.toString()
        ImagePayload.fromBase64Payload(s"data:$mtStr;base64,$encoded") match {
          case Success(ImagePayload(_, mediaType)) => assertResult(expMediaType)(mediaType)
          case Failure(err) => fail(s"Failed at parsing media type $mtStr: ${err.getMessage}")
        }
      })
    }

    "returns an error" - {
      "when the payload is not well formed" - {
        assert(ImagePayload.fromBase64Payload("malformed").isFailure)
      }

      "when the media type is not supported" - {
        assert(ImagePayload.fromBase64Payload("data:text/html;base64,YMJj").isFailure)
      }

      "when the payload is not valid base64" - {
        //TODO with another library that checks input validity
      }
    }
  }
}
