package plumitive

import java.io.File
import java.nio.file.FileSystems
import java.sql.DriverManager

import akka.actor.ActorSystem
import akka.stream.ActorFlowMaterializer
import com.typesafe.config.ConfigFactory
import org.sqlite.SQLiteDataSource

object Settings {
  implicit val actorSystem = ActorSystem()


  val overrideFile = {
    val overridesPath = System.getProperty("config.overrides")
    new File(overridesPath)
  }

  val conf = {
    val baseConf = ConfigFactory.load()
    if (overrideFile.exists)
      ConfigFactory.parseFile(overrideFile).withFallback(baseConf)
    else
      baseConf
  }

  val flowMaterializer = ActorFlowMaterializer()
  val executionContext = actorSystem.dispatcher
  val fileSystem       = FileSystems.getDefault

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
