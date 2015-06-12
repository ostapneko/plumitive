package plumitive.http

import akka.http.impl.model.parser.Base64Parsing
import akka.http.scaladsl.model.{MediaType, MediaTypes}
import plumitive.ImageBytes

import scala.util.parsing.combinator._

case class ImagePayload(imageBase64: ImageBytes, mimeType: MediaType)

object ImagePayload {
  def fromBase64Payload(base64Str: String): Either[String, ImagePayload] = {
    Base64Parser.parsePayload(base64Str)
  }

  object Base64Parser extends RegexParsers {
    def parsePayload(base64str: String): Either[String, ImagePayload] = {
      val parser: Parser[ImagePayload] = for {
        _ <- Parser("data:")
        mediaType <- parseMediaType
        _ <- Parser(";base64,")
        imageChars <- """(.*)""".r
        imageBytes <- toBytes(imageChars)
      } yield ImagePayload(imageBytes, mediaType)

      parse(parser, base64str) match {
        case Success(payload, _) => Right(payload)
        case Failure(msg, _)     => Left(s"Failure while trying to parse the image payload: $msg")
        case Error(msg, _)       => throw new RuntimeException(s"Fatal error while parsing base64 characters into image bytes: $msg")
      }
    }

    private

    def parseMediaType: Parser[MediaType] = {
      "image/png" ^^ (_ => MediaTypes.`image/png`) | failure(s"Unhandled media type")
    }

    def toBytes(str: String): Parser[ImageBytes] = {
      val chars = str.toCharArray
      success(Base64Parsing.rfc2045StringDecoder(chars))
    }
  }
}
