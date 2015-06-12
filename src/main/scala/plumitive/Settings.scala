package plumitive

import java.nio.file.FileSystems

import akka.actor.ActorSystem
import akka.stream.ActorFlowMaterializer
import com.typesafe.config.ConfigFactory

object Settings {
  implicit val actorSystem = ActorSystem()
  val conf = ConfigFactory.load()

  val flowMaterializer = ActorFlowMaterializer()
  val executionContext = actorSystem.dispatcher
  val fileSystem       = FileSystems.getDefault

  val docDir = fileSystem.getPath(conf.getString("plumitive.docDir"))

  val staticDir = fileSystem.getPath(conf.getString("plumitive.assetsDir"))
  val indexFile = staticDir.resolve("index.html").toFile
  val cssDir    = staticDir.resolve("css").toString
  val jsDir     = staticDir.resolve("js").toString
}
