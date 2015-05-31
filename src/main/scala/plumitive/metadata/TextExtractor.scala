package plumitive.metadata

import plumitive.ImageBytesBase64

import scala.concurrent.Future

trait TextExtractor {
  def extract(bytes: ImageBytesBase64): Future[String]
}
