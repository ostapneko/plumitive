package gratte

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorFlowMaterializer
import gratte.http.Router

object Server {
  def main (args: Array[String]) {
    implicit val as = ActorSystem()
    implicit val fm = ActorFlowMaterializer()

    Http().bindAndHandle(Router.route, "localhost", 8080)
  }
}
