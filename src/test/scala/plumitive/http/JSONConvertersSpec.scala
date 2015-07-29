package plumitive.http

import argonaut._, Argonaut._
import org.scalatest.FreeSpec
import JSONConverters._
import plumitive.{Document, Fixtures}

import scalaz.\/

class JSONConvertersSpec extends FreeSpec {
  "A Document can be encoded in JSON" in {
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

  "A Document can be decoded from JSON" in {
    val json = Parse.parseOption(
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

    assertResult(\/.right(Fixtures.testDocument))(json.as[Document].toDisjunction)
  }
}
