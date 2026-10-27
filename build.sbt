name := "snp-challenge"

version := "1.0.0"

autoScalaLibrary := false

javacOptions ++= Seq("--release", "21")

libraryDependencies +=
  "org.junit.jupiter" % "junit-jupiter" % "5.12.2" % Test

Test / fork := true