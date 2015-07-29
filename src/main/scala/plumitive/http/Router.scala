package plumitive.http

import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import argonaut.Argonaut._
import argonaut._
import plumitive.Settings._
import plumitive.core.{API, SearchQuery}
import plumitive.http.JSONConverters._
import plumitive.http.Marshallers._
import plumitive.http.PathMatchers._
import plumitive.http.Unmarshallers._
import plumitive.{Document, Settings}

import scala.util.{Failure, Success}

object Router {
  def route(implicit api: API) = {
    implicit val ec = Settings.executionContext
    implicit val fm = ActorMaterializer()

    staticRoute ~
      get {
        path("documents") {
          val docs = api.query(SearchQuery(Seq(""), Set(), None))
          complete(docs.map(_.toList))
        } ~
          path("document" / DocumentIdMatcher) { docId =>
            val doc = api.show(docId).map(_._1)
            complete(doc)
          }
      } ~
      post {
        path("documents" / "extract-text") {
          entity(as[ImagePayload]) { image =>
            val extracted = api.extractText(image)
            val resp = extracted.map { ext =>
              Json("extractedText" := ext)
            }
            complete(resp)
          }
        }
      } ~
      (put | post) {
        path("document") {
          entity(as[(Document, Option[ImagePayload])]) { case (doc, image) =>
            onComplete(api.put(doc, image)) {
              case Success(_) => complete("Success")
              case Failure(err) => failWith(err)
            }
          }
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
        getFromFile(indexFile)
      } ~
        pathPrefix("css") {
          getFromDirectory(cssDir)
        } ~
        pathPrefix("js") {
          getFromDirectory(jsDir)
        }
    }
}
