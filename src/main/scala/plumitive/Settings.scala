package plumitive

import java.io.File
import java.nio.file.FileSystems

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import org.sqlite.SQLiteDataSource

object Settings {
  implicit val actorSystem = ActorSystem()


  val fileSystem   = FileSystems.getDefault
  val overridePath = Option(System.getProperty("config.overrides"))

  val conf = {
    val baseConf = ConfigFactory.load()
    overridePath.fold(baseConf)(p =>
      ConfigFactory.parseFile(new File(p)).withFallback(baseConf)
    )
  }

  val flowMaterializer = ActorMaterializer()
  val executionContext = actorSystem.dispatcher

  val docDir = fileSystem.getPath(conf.getString("plumitive.docDir"))

  val staticDir = fileSystem.getPath(conf.getString("plumitive.assetsDir"))
  val indexFile = staticDir.resolve("index.html").toFile
  val cssDir    = staticDir.resolve("css").toString
  val jsDir     = staticDir.resolve("js").toString
  val tmpDir    = fileSystem.getPath(conf.getString("plumitive.tmpDir"))

  object SqliteConf {
    lazy val datasource = {
      val ds = new SQLiteDataSource()
      ds.setUrl(conf.getString("plumitive.db.sqliteJdbcUrl"))
      ds
    }
  }

}
