package plumitive.metadata

import plumitive.ImageBytes
import plumitive.http.ImagePayload

import scala.concurrent.Future

object TextExtractor {
  class TextExtractionFailed(msg: String) extends RuntimeException
}

trait TextExtractor {
  def extract(bytes: ImagePayload): Future[String]
}
