package plumitive.files

import java.nio.file.Path

import plumitive.Document
import plumitive.Document.Id
import plumitive.http.ImagePayload

import scala.concurrent.Future

trait Store {
  def find(docId: Document.Id): Future[Path]
  def put(docId: Id, bytes: ImagePayload)
  def delete(docId: Document.Id)
}
