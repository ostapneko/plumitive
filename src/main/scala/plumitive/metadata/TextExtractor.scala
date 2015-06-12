package plumitive.metadata

import plumitive.ImageBytes

import scala.concurrent.Future

object TextExtractor {
  class TextExtractionFailed(msg: String) extends RuntimeException
}

trait TextExtractor {
  def extract(bytes: ImageBytes): Future[String]
}
