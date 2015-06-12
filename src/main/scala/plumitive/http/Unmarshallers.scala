package plumitive.http

import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.unmarshalling.Unmarshaller
import akka.util.ByteString
import argonaut.Argonaut._
import argonaut._
import plumitive.Settings

object Unmarshallers {
  implicit val fm = Settings.flowMaterializer

  implicit def jsonUnmarshaller[T: DecodeJson] = Unmarshaller[HttpRequest, T] { implicit ec =>
    req => {
      req.entity.dataBytes.runFold(ByteString.empty)(_ ++ _).map { bs =>
        bs.decodeString("UTF-8").decodeOption[T].getOrElse(
          throw new Exception("Parsing error")
        )
      }
    }
  }
}
