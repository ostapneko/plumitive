package plumitive.files

import java.nio.file.Path

import plumitive.Document.Id
import plumitive.http.ImagePayload

import scala.concurrent.Future

object LocalFileStore extends Store {
  override def find(docId: Id): Future[Path] = ???

  override def put(docId: Id, bytes: ImagePayload): Unit = println(s"Storing the doc $docId...")

  override def delete(docId: Id): Unit = ???
}
