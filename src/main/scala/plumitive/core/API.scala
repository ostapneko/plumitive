package plumitive.core

import java.nio.file.Path

import plumitive.Document
import plumitive.http.ImagePayload

import scala.concurrent.Future

trait API {
  def query(searchQuery: SearchQuery): Future[Seq[Document]]
  def show(docId: Document.Id): Future[(Document, Path)]
  def extractText(chars: ImagePayload): Future[String]
  def put(doc: Document, image: Option[ImagePayload]): Future[Unit]
  def delete(docId: Document.Id): Future[Unit]

  class DocumentNotFound extends RuntimeException
  class DocumentCreationException(val msg: String) extends RuntimeException
}
