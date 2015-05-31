package plumitive.core

import plumitive.{ImageBytesBase64, Document}

import scala.concurrent.Future

trait API {
  def query(searchQuery: SearchQuery): Future[Seq[Document]]
  def show(docId: Document.Id): Future[Document]
  def extractText(bytes: ImageBytesBase64): Future[String]
  def put(doc: Document): Future[Unit]
  def delete(docId: Document.Id): Future[Unit]

  class DocumentNotFound extends RuntimeException
  class DocumentCreationException(val msg: String) extends RuntimeException
}
