object Version {
  val major: Int = 1
  val minor: Int = 0

  lazy val patch: String =
    new java.text.SimpleDateFormat("yyMMdd.HHmm").format(new java.util.Date())

  val versonFile = "version.txt"

  lazy val dateVersioning: String = {
    val value = s"\$major.\$minor.\$patch"

    import java.io._
    val pw = new PrintWriter(new File(versonFile))
    pw.write(value)
    pw.close()

    value
  }
}
