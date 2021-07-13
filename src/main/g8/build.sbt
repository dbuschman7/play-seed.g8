import Dependencies._

ThisBuild / organization := "$organization;format="lower,package"$"
ThisBuild / scalaVersion := "2.13.6"
ThisBuild / version := Version.dateVersioning

lazy val `$name;format="norm"$` =
  project
    .enablePlugins(JavaAppPackaging, BuildInfoPlugin, DockerPlugin)
    .in(file("."))
    .settings(name := "$name$")
    .settings(commonSettings)
    .settings(dependencies)
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

lazy val dependencies = Seq(
  libraryDependencies ++= Seq(
    com.softwaremill.quicklens,
    dev.zio.zio,
    Dependencies.io.scalaland.chimney,
    com.softwaremill.quicklens,
    com.lihaoyi.`ammonite-ops`,
    //
    // main dependencies
    //
  ),
  libraryDependencies ++= Seq(
    org.scalacheck.scalacheck,
    org.scalatest.scalatest,
    org.scalatestplus.`scalacheck-1-15`,
  ).map(_ % Test),
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
