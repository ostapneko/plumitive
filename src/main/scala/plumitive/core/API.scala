package plumitive.core

import java.nio.file.Path

import plumitive.{ImageBytes, Document}

import scala.concurrent.Future

trait API {
  def query(searchQuery: SearchQuery): Future[Seq[Document]]
  def show(docId: Document.Id): Future[(Document, Path)]
  def extractText(chars: ImageBytes): Future[String]
  def put(doc: Document, image: Option[ImageBytes]): Future[Unit]
  def delete(docId: Document.Id): Future[Unit]

  class DocumentNotFound extends RuntimeException
  class DocumentCreationException(val msg: String) extends RuntimeException
}
