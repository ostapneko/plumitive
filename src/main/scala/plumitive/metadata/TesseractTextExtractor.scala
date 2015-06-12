package plumitive.metadata

import java.nio.file.{Files, Path}

import plumitive.{ImageBytes, Settings}

import scala.concurrent.Future

object TesseractTextExtractor extends TextExtractor {
  implicit val ec = Settings.executionContext

  def saveImage(bytes: ImageBytes): Future[Path] = {
    Future {
      val path = Settings.docDir.resolve("bla.png")
      Files.createDirectories(Settings.docDir)
      Files.write(path, bytes)
      path
    }
  }

  def runTesseract(path: Path): Future[String] = {
    Future { "example" }
  }

  override def extract(bytes: ImageBytes): Future[String] = {
    for {
      path <- saveImage(bytes)
      result <- runTesseract(path)
    } yield result
  }
}

