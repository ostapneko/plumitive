package plumitive.metadata

import plumitive.ImageBytesBase64

import scala.concurrent.Future

object TextExtractor {
  class TextExtractionFailed(msg: String) extends RuntimeException
}

trait TextExtractor {
  def extract(bytes: ImageBytesBase64): Future[String]
}
