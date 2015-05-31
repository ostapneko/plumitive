package plumitive

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorFlowMaterializer
import plumitive.core.APIImpl
import plumitive.http.Router

object Server {
  def main (args: Array[String]) {
    implicit val as = ActorSystem()
    implicit val fm = ActorFlowMaterializer()
    implicit val api = APIImpl
    implicit val ec = as.dispatcher

    Http().bindAndHandle(Router.route, "localhost", 8080)
  }
}
