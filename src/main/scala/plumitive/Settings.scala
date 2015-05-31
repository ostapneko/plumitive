package plumitive

import java.nio.file.{FileSystems, FileSystem}

import akka.actor.ActorSystem
import akka.stream.ActorFlowMaterializer

object Settings {
  implicit val actorSystem = ActorSystem()
  val flowMaterializer = ActorFlowMaterializer()
  val executionContext = actorSystem.dispatcher
  val fileSystem = FileSystems.getDefault

  val staticDir = "static"
  val indexFile = staticDir ++ "/index.html"
  val cssDir = staticDir ++ "/css"
  val jsDir = staticDir ++ "/js"
}
