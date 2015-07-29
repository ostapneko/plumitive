package plumitive.http

import akka.http.impl.model.parser.Base64Parsing
import akka.http.scaladsl.model.MediaType
import akka.http.scaladsl.model.MediaTypes._
import plumitive.ImageBytes

import scala.util.parsing.combinator.RegexParsers
import scalaz.Scalaz.ToIdOps
import scalaz.\/

case class ImagePayload(bytes: ImageBytes, mimeType: MediaType)

object ImagePayload {
  val supportedMediaTypes = Set(`image/jpeg`, `image/png`, `application/pdf`)

  def fromBase64Payload(base64Str: String): String \/ ImagePayload = {
    Base64Parser.parsePayload(base64Str)
  }

  object Base64Parser extends RegexParsers {
    def parsePayload(base64str: String): String \/ ImagePayload = {
      val parser: Parser[ImagePayload] = for {
        _ <- Parser("data:")
        mediaType <- parseMediaType
        _ <- Parser("base64,")
        imageChars <- """(.*)""".r
        imageBytes <- toBytes(imageChars)
      } yield ImagePayload(imageBytes, mediaType)

      parse(parser, base64str) match {
        case Success(payload, _) => payload.right
        case Failure(msg, _)     => s"Failure while trying to parse the image payload: $msg".left
        case Error(msg, _)       => s"Fatal error while parsing base64 characters into image bytes: $msg".left
      }
    }

    private

    def parseMediaType: Parser[MediaType] = {
      for {
        mtStr <- """[^;]+""".r <~ ";" // "image/png;" => "image/png"
        mediaType <- supportedMediaTypes.find(_.toString() == mtStr) match {
          case Some(mt) => success(mt)
          case None     => failure(s"Unsupported media type $mtStr")
        }
      } yield mediaType
    }

    def toBytes(str: String): Parser[ImageBytes] = {
      val chars = str.toCharArray
      success(Base64Parsing.rfc2045StringDecoder(chars))
    }
  }
}
