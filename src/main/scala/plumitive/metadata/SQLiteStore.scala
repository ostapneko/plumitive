package plumitive.metadata

import scala.concurrent.Future

import plumitive.Document
import plumitive.Settings
import plumitive.core.SearchQuery
import slick.driver.SQLiteDriver.api._

object SQLiteStore extends Store {
  implicit val ec = Settings.executionContext
  val db = Database.forDataSource(Settings.SqliteConf.datasource)

  override def create(doc: Document): Future[Unit] = {
    db.run(SQLiteDAOs.documents += doc) map {
      _ => println(s"Successfully created the document: ${doc.title}")
    }
  }

  override def query(searchQuery: SearchQuery): Future[Seq[Document]] = {
    val q = for (d <- SQLiteDAOs.documents)  yield d
    db.run(q.result)
  }

  override def find(docId: Document.Id): Future[Document] = ???
  override def put(doc: Document): Future[Unit] = ???
  override def delete(docId: Document.Id): Future[Unit] = ???
}
