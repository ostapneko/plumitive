package plumitive.metadata

import org.scalatest.FreeSpec
import plumitive.Fixtures
import slick.driver.SQLiteDriver.api._
import plumitive.Settings
import scala.concurrent.Await
import scala.concurrent.duration._

class SQLiteStoreSpec extends FreeSpec {
  implicit val ec = Settings.executionContext

  "A Document can be inserted in a SQLite DB" in {
    val doc = Fixtures.testDocument
    SQLiteStore.put(doc)
    val firstDocQ = SQLiteDAOs.documents.result.headOption

    val docInDB =
      Await.result(SQLiteStore.db.run(firstDocQ), 1.second)

    assertResult(Some(doc))(docInDB)
  }
}
