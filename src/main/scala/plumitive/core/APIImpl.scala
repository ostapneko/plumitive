package plumitive.core

import plumitive.Document
import plumitive.Document.Id

import scala.concurrent.{ExecutionContext, Future}

object APIImpl extends API {
  override def query(searchQuery: SearchQuery)(implicit ec: ExecutionContext): Future[Seq[Document]] = Future { Seq[Document]() }

  override def extractMetadata(implicit ec: ExecutionContext): Future[Document] = Future.failed(new DocumentNotFound)

  override def put(implicit ec: ExecutionContext): Future[Unit] = {
    val err = new DocumentCreation("Not implemented yet")
    Future.failed(err)
  }

  override def delete(docId: Id)(implicit ec: ExecutionContext): Future[Unit] = Future.failed(new DocumentNotFound)

  override def show(docId: Id)(implicit ec: ExecutionContext): Future[Document] = Future.failed(new DocumentNotFound)
}
