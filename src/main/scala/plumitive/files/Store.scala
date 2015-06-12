package plumitive.files

import java.nio.file.Path

import plumitive.{ImageBytes, Document}

import scala.concurrent.Future

trait Store {
  def find(docId: Document.Id): Future[Path]
  def put(docId: Document.Id, bytes: ImageBytes)
  def delete(docId: Document.Id)
}
