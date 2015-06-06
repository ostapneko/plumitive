package plumitive.core

import java.nio.file.Path

import plumitive.Document.Id
import plumitive.metadata.SQLiteStore
import plumitive.{ImageBytesBase64, Document, Settings}

import scala.concurrent.Future

object APIImpl extends API {
  implicit val ec = Settings.executionContext
  override def query(searchQuery: SearchQuery): Future[Seq[Document]] = {
    SQLiteStore.query(searchQuery)
  }

  override def extractText(bytes: ImageBytesBase64): Future[String] = Future.failed(new DocumentNotFound)

  override def put(doc: Document, image: Option[ImageBytesBase64]): Future[Unit] = {
    val err = new DocumentCreationException("Not implemented yet")
    Future.failed(err)
  }

  override def delete(docId: Id): Future[Unit] = Future.failed(new DocumentNotFound)

  override def show(docId: Id): Future[(Document, Path)] = Future.failed(new DocumentNotFound)
}
