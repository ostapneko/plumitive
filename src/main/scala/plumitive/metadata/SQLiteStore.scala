package plumitive.metadata

import plumitive.Document.Id

import scala.concurrent.Future

import plumitive.Document
import plumitive.Settings
import plumitive.core.SearchQuery
import slick.driver.SQLiteDriver.api._
import SQLiteDAOs._

object SQLiteStore extends Store {
  implicit val ec = Settings.executionContext
  val db = Database.forDataSource(Settings.SqliteConf.datasource)

  override def put(doc: Document): Future[Id] = {
    val newDocId = doc.id.getOrElse(Document.Id(System.currentTimeMillis().toString))
    val newDoc = doc.copy(id = Some(newDocId))
    db.run(documents.insertOrUpdate(newDoc)).map(_ => newDocId)
  }

  override def query(searchQuery: SearchQuery): Future[Seq[Document]] = {
    db.run(documents.result)
  }

  override def find(docId: Document.Id): Future[Document] = ???
  override def delete(docId: Document.Id): Future[Unit] = ???
}
