import sbt._

object Dependencies {
  case object com {
    case object github {

      case object liancheng {
        val `organize-imports` =
          "com.github.liancheng" %% "organize-imports" % "0.5.0"
      }
    }

    case object lihaoyi {
      val `ammonite-ops` = "com.lihaoyi" %% "ammonite-ops" % "2.4.0"
    }

    case object softwaremill {
      val quicklens = "com.softwaremill.quicklens" %% "quicklens" % "1.7.4"
    }

    case object typesafe {
      case object Akka {
        val version = "2.6.15"
        val akka = "com.typesafe.akka" %% "akka" % version
        val streams = "com.typesafe.akka" %% "akka-stream" % version
        val http = "com.typesafe.akka" %% "akka-http" % "10.2.4"
      }
    }

  }

  case object dev {
    case object zio {
      val zio =
        "dev.zio" %% "zio" % "1.0.9"

      val `zio-config` =
        "dev.zio" %% "zio-config" % "1.0.6"

      val `zio-interop-cats` =
        "dev.zio" %% "zio-interop-cats" % "3.1.1.0"

      val `zio-streams` =
        "dev.zio" %% "zio-streams" % "1.0.9"

    }
  }

  case object io {
    case object scalaland {
      val chimney = "io.scalaland" %% "chimney" % "0.6.1"
    }
  }

  case object org {

    case object scalacheck {
      val scalacheck =
        "org.scalacheck" %% "scalacheck" % "1.15.4"
    }

    case object scalatest {
      val scalatest =
        "org.scalatest" %% "scalatest" % "3.2.9"
    }

    case object scalatestplus {
      val `scalacheck-1-15` =
        "org.scalatestplus" %% "scalacheck-1-15" % "3.2.9.0"
      val scalatestplus = 
        "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0"
    }

  }
}
