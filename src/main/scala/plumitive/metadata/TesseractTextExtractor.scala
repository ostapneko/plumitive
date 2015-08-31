package plumitive.metadata

import java.nio.file.{Files, Path}

import akka.http.scaladsl.model.MediaTypes
import plumitive.Settings
import plumitive.http.ImagePayload

import scala.concurrent.Future

import scala.sys.process._

object TesseractTextExtractor extends TextExtractor {
  implicit val ec = Settings.executionContext

  override def extract(image: ImagePayload): Future[String] = {
    Future {
      val path = saveImage(image)
      val result = runTesseract(path)
      Files.deleteIfExists(path)
      result
    }.recover { case e: Throwable =>
      throw new RuntimeException("Failed while trying to extract text with Tesseract")
    }
  }

  private def tempPath(image: ImagePayload): Path = {
    val ext = image.mimeType match {
      case MediaTypes.`image/jpeg` => "jpg" // Avoid the esoteric "jpe" extension
      case mt => mt.fileExtensions.head
    }

    Settings.tmpDir.resolve(System.currentTimeMillis().toString + "." + ext)
  }

  private def saveImage(image: ImagePayload): Path = {
    Files.createDirectories(Settings.tmpDir)
    val path = tempPath(image)
    Files.write(path, image.bytes)
    path
  }

  private def runTesseract(path: Path): String = {
    val buf = new StringBuffer()
    Seq("tesseract", path.toString, "stdout") ! ProcessLogger(buf.append(_))
    buf.toString
  }
}

