package plumitive.metadata

import plumitive.Document
import plumitive.core.SearchQuery

import scala.concurrent.Future

trait Store {
  def query(searchQuery: SearchQuery): Future[Seq[Document]]
  def find(docId: Document.Id): Future[Document]
  def put(doc: Document): Future[Unit]
  def delete(docId: Document.Id): Future[Unit]

  class DocumentNotFound extends RuntimeException
  class DocumentCreationException(val msg: String) extends RuntimeException
}
