import Dependencies._
import sbt._
import sbt.Keys._ 


ThisBuild / organization := "$organization;format="lower,package"$"
ThisBuild / scalaVersion := "2.13.6"
ThisBuild / version := Version.dateVersioning

lazy val `$name;format="norm"$` =
  project
    .enablePlugins(PlayScala, BuildInfoPlugin, DockerPlugin)
    .in(file("."))
    .settings(name := "$name$")
    .settings(commonSettings)
    .settings(dependencies)
    .settings(libraryDependencies += guice)
    .settings( 
      doc / sources := Seq.empty,
      Compile / publishArtifact := false,
      packageDoc / publishArtifact := false,
      //
      wartremoverExcluded += crossTarget.value / "routes" / "main" / "router" / "Routes.scala",
      wartremoverExcluded += crossTarget.value / "routes" / "main" / "router" / "RoutesPrefix.scala",
      wartremoverExcluded += crossTarget.value / "routes" / "main" / "controllers" / "ReverseRoutes.scala",
      wartremoverExcluded += crossTarget.value / "routes" / "main" / "controllers" / "javascript" / "JavaScriptReverseRoutes.scala",
    )
    .settings(BuildInfo.settings(name, version, ThisBuild / scalaVersion, sbtVersion))

lazy val commonSettings = Seq(
  // addCompilerPlugin(org.typelevel.`kind-projector`),
  update / evictionWarningOptions := EvictionWarningOptions.empty,
  Compile / console / scalacOptions := {
    (Compile / console / scalacOptions)
      .value
      .filterNot(_.contains("wartremover"))
      .filterNot(Scalac.Lint.toSet)
      .filterNot(Scalac.FatalWarnings.toSet) :+ "-Wconf:any:silent"
  },
  Test / console / scalacOptions :=
    (Compile / console / scalacOptions).value,
)

lazy val dependencies = 
  //
  // Main deps 
  // 
  Seq(
  libraryDependencies ++= Seq(
    com.softwaremill.quicklens,
    dev.zio.zio,
    Dependencies.io.scalaland.chimney,
    com.softwaremill.quicklens,
    com.lihaoyi.`ammonite-ops`,
  ),
    //
    // Test Deps
    // 
  libraryDependencies ++= Seq(
    org.scalacheck.scalacheck,
    org.scalatest.scalatest,
    org.scalatestplus.`scalacheck-1-15`,
    org.scalatestplus.scalatestplus,
  ).map(_ % Test),
  //
  libraryDependencies ++= CDK.cdkDeps.map(_ % Test),  // CDK libs 
)




onLoadMessage +=
  s"""|
      |╭─────────────────────────────────────
      |│ App \${name.value}
      |├─────────────────┬───────────────────
      |│ Scala Version   │ \${scalaVersion.value}
      |│ Sbt Version     │ \${sbtVersion.value}
      |│ App Version     │ \${version.value}
      |├─────────────────┼───────────────────
      |│ Git Branch      │ \${BuildInfo.branch}
      |│ Git Commit      │ \${BuildInfo.commit}
      |│ Has Uncommitted │ \${BuildInfo.hasUnCommitted}
      |╰─────────────────┴───────────────────""".stripMargin
