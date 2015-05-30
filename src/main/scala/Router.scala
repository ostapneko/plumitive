import akka.http.scaladsl.server.Directives._

object Router {
  lazy val route =
    get {
      pathSingleSlash {
        complete("yo")
      }
    }
}