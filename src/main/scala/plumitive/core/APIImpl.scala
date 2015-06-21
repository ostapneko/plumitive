package plumitive.core

import java.nio.file.Path

import plumitive.Document.Id
import plumitive.http.ImagePayload
import plumitive.metadata.{TesseractTextExtractor, SQLiteStore}
import plumitive.{ImageBytes, Document, Settings}

import scala.concurrent.Future

object APIImpl extends API {
  implicit val ec = Settings.executionContext
  override def query(searchQuery: SearchQuery): Future[Seq[Document]] = {
    SQLiteStore.query(searchQuery)
  }

  override def extractText(bytes: ImagePayload): Future[String] =
    TesseractTextExtractor.extract(bytes)

  override def put(doc: Document, image: Option[ImagePayload]): Future[Unit] = {
    val err = new DocumentCreationException("Not implemented yet")
    Future.failed(err)
  }

  override def delete(docId: Id): Future[Unit] = Future.failed(new DocumentNotFound)

  override def show(docId: Id): Future[(Document, Path)] = Future.failed(new DocumentNotFound)
}
