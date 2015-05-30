package pear2pear.http

import akka.http.scaladsl.server.{PathMatcher, PathMatcher1}
import pear2pear.Document

object PathMatchers {
  val DocumentIdMatcher: PathMatcher1[Document.Id] =
    PathMatcher("""[^/]+""".r).flatMap { idStr =>
      Some(Document.Id(idStr))
    }
}
