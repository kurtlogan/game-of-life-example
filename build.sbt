import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "kurtlogan",
      scalaVersion := "2.13.8",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "game-of-life"
  )
