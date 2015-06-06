package plumitive.metadata

import plumitive.Document
import plumitive.core.SearchQuery

import scala.concurrent.Future

class SQLiteStore extends Store {
  def query(searchQuery: SearchQuery): Future[Seq[Document]] = ???
  def find(docId: Document.Id): Future[Document] = ???
  def put(doc: Document): Future[Unit] = ???
  def delete(docId: Document.Id): Future[Unit] = ???
}
