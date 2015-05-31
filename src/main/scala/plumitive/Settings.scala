package plumitive

import akka.actor.ActorSystem
import akka.stream.ActorFlowMaterializer

object Settings {
  implicit val actorSystem = ActorSystem()
  val flowMaterializer = ActorFlowMaterializer()
  val executionContext = actorSystem.dispatcher
}
