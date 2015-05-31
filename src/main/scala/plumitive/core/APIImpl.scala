package plumitive.core

import plumitive.Document.Id
import plumitive.{Document, Settings}

import scala.concurrent.Future

object APIImpl extends API {
  implicit val ec = Settings.executionContext
  override def query(searchQuery: SearchQuery): Future[Seq[Document]] = Future { Seq[Document]() }

  override def extractText: Future[Document] = Future.failed(new DocumentNotFound)

  override def put(doc: Document): Future[Unit] = {
    val err = new DocumentCreationException("Not implemented yet")
    Future.failed(err)
  }

  override def delete(docId: Id): Future[Unit] = Future.failed(new DocumentNotFound)

  override def show(docId: Id): Future[Document] = Future.failed(new DocumentNotFound)
}
