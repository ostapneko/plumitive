package plumitive

import akka.http.scaladsl._
import plumitive.core.APIImpl
import plumitive.http.Router

object Server {
  def main (args: Array[String]) {
    implicit val api = APIImpl
    implicit val as = Settings.actorSystem
    implicit val fm = Settings.flowMaterializer

    Http().bindAndHandle(Router.route, "localhost", 8080)
  }
}
