package plumitive.http

import akka.http.scaladsl.server.Directives._
import akka.stream.ActorFlowMaterializer
import argonaut._
import Argonaut._
import plumitive.Settings
import plumitive.Settings._
import plumitive.core.{API, SearchQuery}
import plumitive.http.JSONConverters._
import plumitive.http.Marshallers._
import plumitive.http.Unmarshallers._
import plumitive.http.PathMatchers._

import scala.util.{Failure, Success, Try}

object Router {
  def route(implicit api: API) = {
    implicit val ec = Settings.executionContext
    implicit val fm = ActorFlowMaterializer()

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
          entity(as[Try[ImagePayload]]) {
            case Success(image) =>
              val extracted = api.extractText(image)
              val resp = extracted.map { ext =>
                Json("extractedText" := ext)
              }
              complete(resp)
            case Failure(err) =>
              complete("TODO")
          }
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
