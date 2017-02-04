name := "idea-dependencies-bug"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.1.0" % "provided",
  "org.scalactic" %% "scalactic" % "3.0.1" ,
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)