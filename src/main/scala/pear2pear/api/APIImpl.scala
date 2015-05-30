package pear2pear.api

import pear2pear.Document
import pear2pear.Document.Id

import scala.concurrent.{ExecutionContext, Future}

object APIImpl extends API {
  override def query(implicit ex: ExecutionContext): Future[Seq[Document]] = Future { Seq() }

  override def extractMetadata(implicit ex: ExecutionContext): Future[Document] = Future.failed(new DocumentNotFound)

  override def put(implicit ex: ExecutionContext): Future[Unit] = {
    val err = new DocumentCreation("Not implemented yet")
    Future.failed(err)
  }

  override def delete(docId: Id)(implicit ex: ExecutionContext): Future[Unit] = Future.failed(new DocumentNotFound)

  override def show(docId: Id)(implicit ex: ExecutionContext): Future[Document] = Future.failed(new DocumentNotFound)
}
