package plumitive.http

import argonaut._, Argonaut._
import org.scalatest.FreeSpec
import Serializers.DocumentEncodeJson
import plumitive.Fixtures

class SerializersSpec extends FreeSpec {
  "A Document can be encoded en JSON" in {
    val exp = Parse.parseOption(
      """
        |{
        |  "id": "1234",
        |  "title": "title",
        |  "scannedText": "this is a scanned text",
        |  "tags": ["tag1", "tag2"],
        |  "month": "January",
        |  "year": 2015,
        |  "sender": "HSBC",
        |  "recipients": ["Me", "Myself"]
        |}
      """.stripMargin).get

    assertResult(exp) { Fixtures.testDocument.asJson }
  }
}
