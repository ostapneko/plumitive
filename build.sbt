name := "plumitive"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-core-experimental" % "1.0",
  "com.typesafe.akka" %% "akka-http-experimental" % "1.0",
  "io.argonaut" %% "argonaut" % "6.0.4",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
  "com.typesafe.slick" % "slick_2.11" % "3.0.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.xerial" % "sqlite-jdbc" % "3.8.10.1",
  "org.scalaz" %% "scalaz-core" % "7.0.6",
  "org.flywaydb" % "flyway-core" % "3.2.1"
)
