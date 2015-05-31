package plumitive.files

import java.nio.file.Path

import plumitive.{ImageBytesBase64, Document}

import scala.concurrent.Future

trait Store {
  def find(docId: Document.Id): Future[Path]
  def put(docId: Document.Id, bytes: ImageBytesBase64)
  def delete(docId: Document.Id)
}
