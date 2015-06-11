package plumitive.metadata

import plumitive.core.SearchQuery
import plumitive.{Document, Settings}
import slick.driver.SQLiteDriver.api._

import scala.concurrent.Future

object SQLiteStore extends Store {
  implicit val ec = Settings.executionContext
  val db = Database.forConfig("plumitive.sqliteDB")

  def query(searchQuery: SearchQuery): Future[Seq[Document]] = {
    val q = for ( d <- Schema.documents ) yield d
    db.run(q.result)
  }

  def find(docId: Document.Id): Future[Document] = ???
  def put(doc: Document): Future[Unit] = ???
  def delete(docId: Document.Id): Future[Unit] = ???
}
