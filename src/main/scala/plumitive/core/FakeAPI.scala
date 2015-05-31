package plumitive.core

import plumitive.Document
import plumitive.Document._

import scala.concurrent.{ExecutionContext, Future}

object FakeAPI extends API {
  override def query(searchQuery: SearchQuery)(implicit ec: ExecutionContext): Future[Seq[Document]] = Future { Seq[Document]() }

  override def extractMetadata(implicit ec: ExecutionContext): Future[Document] = Future.failed(new DocumentNotFound)

  override def put(implicit ec: ExecutionContext): Future[Unit] = {
    val err = new DocumentCreation("Not implemented yet")
    Future.failed(err)
  }

  override def delete(docId: Id)(implicit ec: ExecutionContext): Future[Unit] = Future.failed(new DocumentNotFound)

  override def show(docId: Id)(implicit ec: ExecutionContext): Future[Document] = {
    Future {
      Document(
        id = Some(Id("1234")),
        scannedText = Some("this is a scanned text"),
        tags = Set(Tag("tag1"), Tag("tag2")),
        date = Date(Some(January), 2015),
        sender = Sender("BNP"),
        recipients = Set(Recipient("EP"), Recipient("TF"))
      )
    }
  }
}
