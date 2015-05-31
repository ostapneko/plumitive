package plumitive.core

import plumitive.Document

import scala.concurrent.{ExecutionContext, Future}

trait API {
  def query(searchQuery: SearchQuery)(implicit ec: ExecutionContext): Future[Seq[Document]]
  def show(docId: Document.Id)(implicit ec: ExecutionContext): Future[Document]
  def extractMetadata(implicit ec: ExecutionContext): Future[Document]
  def put(implicit ec: ExecutionContext): Future[Unit]
  def delete(docId: Document.Id)(implicit ec: ExecutionContext): Future[Unit]

  class DocumentNotFound extends RuntimeException
  class DocumentCreation(val msg: String) extends RuntimeException
}
