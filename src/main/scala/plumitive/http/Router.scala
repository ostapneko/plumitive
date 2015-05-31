package plumitive.http

import akka.http.scaladsl.server.Directives._
import plumitive.core.API
import plumitive.http.PathMatchers._
import Marshallers._
import Serializers._

import scala.concurrent.ExecutionContext

object Router {
  def route(implicit api: API, ec: ExecutionContext) =
    get {
      path("documents") {
        complete("TODO: document query")
      } ~
      path("document" / DocumentIdMatcher) { docId =>
        val doc = api.show(docId)
        complete(doc)
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