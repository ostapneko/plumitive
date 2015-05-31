package plumitive.http

import akka.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import akka.http.scaladsl.model._
import argonaut._
import Argonaut._

object Marshallers {
  implicit def encodeJsonMarshaller[T: EncodeJson]: ToEntityMarshaller[T] = {
    val mt = MediaTypes.`application/json`
    val ct = ContentTypes.`application/json`

    Marshaller.withFixedCharset(mt, HttpCharsets.`UTF-8`) { t =>
      HttpEntity(ct, t.asJson.toString())
    }
  }
}
