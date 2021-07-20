object Version {
  import scala.sys.process._

  val versionFile = "version.txt"
  val pwd = System.getProperty("user.dir")

  lazy val dateVersioning: String = { 
    val message = sys.env
      .get("USE_EXISTING_VERSION")
      .orElse {
        ((s"\${pwd}/generateVersion") !) match { 
          case 0 => Some("Version file creation succeeded")
          case _ => Some("Version file creation failed")
      }
    }
    println(message)

    (( s"cat \${pwd}/\${versionFile}") !!).trim
  }
}
