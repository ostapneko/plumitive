package plumitive.http

import akka.http.scaladsl.server.Directives._
import plumitive.Settings
import plumitive.core.API
import plumitive.http.Marshallers._
import plumitive.http.PathMatchers._
import Serializers.DocumentEncodeJson
import Settings._

object Router {
  def route(implicit api: API) = {
    implicit val ec = Settings.executionContext

    staticRoute ~
      get {
        path("documents") {
          complete("TODO: document query")
        } ~
          path("document" / DocumentIdMatcher) { docId =>
            val doc = api.show(docId).map(_._1)
            complete(doc)
          }
      } ~
      post {
        path("documents" / "preview") {
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

  private val staticRoute =
    get {
      pathSingleSlash {
        getFromResource(indexFile)
      } ~
        pathPrefix("css") {
          getFromResourceDirectory(cssDir)
        } ~
        pathPrefix("js") {
          getFromResourceDirectory(jsDir)
        }
    }
}
