package plumitive.core

import java.nio.file.Path

import plumitive.Document._
import plumitive.Settings.fileSystem
import plumitive.http.ImagePayload
import plumitive.metadata.TextExtractor.TextExtractionFailed
import plumitive.{Document, Fixtures, ImageBytes, Settings}

import scala.concurrent.Future

object TestAPI extends API {
  implicit val ec = Settings.executionContext
  override def query(searchQuery: SearchQuery): Future[Seq[Document]] = Future { Seq[Document]() }

  override def extractText(bytes: ImagePayload): Future[String] = Future.failed(new TextExtractionFailed("not implemented yet"))

  override def put(doc: Document, image: Option[ImagePayload]): Future[Unit] = {
    val err = new DocumentCreationException("Not implemented yet")
    Future.failed(err)
  }

  override def delete(docId: Id): Future[Unit] = Future.failed(new DocumentNotFound)

  override def show(docId: Id): Future[(Document, Path)] = {
    Future { (Fixtures.testDocument, fileSystem.getPath("/")) }
  }
}
