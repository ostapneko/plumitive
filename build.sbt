name := "plumitive"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-core-experimental" % "1.0-RC3",
  "com.typesafe.akka" %% "akka-http-experimental" % "1.0-RC3",
  "io.argonaut" %% "argonaut" % "6.0.4",
  "org.scala-lang.modules" % "scala-parser-combinators_2.11" % "1.0.3",
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.3"
)
