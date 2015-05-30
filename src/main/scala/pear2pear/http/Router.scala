package pear2pear.http

import akka.http.scaladsl.server.Directives._
import pear2pear.api.API
import pear2pear.http.PathMatchers._

import scala.concurrent.ExecutionContext

object Router {
  def route(implicit api: API, ec: ExecutionContext) =
    get {
      path("documents") {
        complete("TODO: document query")
      } ~
      path("document" / DocumentIdMatcher) { docId =>
        complete(s"TODO: Show document with id ${docId.unId}")
      }
    } ~
    post {
      path("extract_metadata") {
        complete("TODO: extract submitted info metadata")
      }
    } ~
    (put | post) {
      path("document") {
        complete("TODO: Update or insert a document")
      }
    } ~
    delete {
      path("document" / DocumentIdMatcher) { docId =>
        complete(s"TODO: Delete document with id ${docId.unId}")
      }
    }
}