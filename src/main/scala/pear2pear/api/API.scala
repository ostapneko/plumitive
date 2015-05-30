package pear2pear.api

import pear2pear.Document

import scala.concurrent.{ExecutionContext, Future}

trait API {
  def query(ec: ExecutionContext): Future[Seq[Document]]
  def show(docId: Document.Id)(implicit ec: ExecutionContext): Future[Document]
  def extractMetadata(implicit ex: ExecutionContext): Future[Document]
  def put(implicit ex: ExecutionContext): Future[Unit]
  def delete(docId: Document.Id)(implicit ex: ExecutionContext): Future[Unit]

  class DocumentNotFound extends RuntimeException
  class DocumentCreation(val msg: String) extends RuntimeException
}
