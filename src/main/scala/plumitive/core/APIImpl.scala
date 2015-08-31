package plumitive.core

import java.nio.file.Path

import plumitive.Document.Id
import plumitive.http.ImagePayload
import plumitive.metadata.{SQLiteStore, TesseractTextExtractor}
import plumitive.{Document, Settings}

import scala.concurrent.Future
import plumitive.files.LocalFileStore

object APIImpl extends API {
  implicit val ec = Settings.executionContext
  private val metadataStore = SQLiteStore
  private val textExtractor = TesseractTextExtractor
  private val fileStore = LocalFileStore

  override def query(searchQuery: SearchQuery): Future[Seq[Document]] = {
    metadataStore.query(searchQuery)
  }

  override def extractText(bytes: ImagePayload): Future[String] =
    textExtractor.extract(bytes)

  override def put(doc: Document, image: Option[ImagePayload]): Future[Unit] = {
    val docId = metadataStore.put(doc)
    docId.map { _docId =>
      image.fold(()) { _image =>
        fileStore.put(_docId, _image)
      }
    }
  }

  override def delete(docId: Id): Future[Unit] = Future.failed(new DocumentNotFound)

  override def show(docId: Id): Future[(Document, Path)] = Future.failed(new DocumentNotFound)
}
