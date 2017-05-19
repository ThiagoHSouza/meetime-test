name := """meetimeCarro"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"


libraryDependencies += filters
libraryDependencies += "br.com.six2six" % "fixture-factory" % "3.1.0"
libraryDependencies += "org.powermock" % "powermock-api-mockito" % "1.6.6"
libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)

