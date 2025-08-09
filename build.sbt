name := "akka-lambda-svc"
version := "0.1.0"
scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % "2.8.1",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.15.2",
  "ch.qos.logback" % "logback-classic" % "1.4.11"
)
