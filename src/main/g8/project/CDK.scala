object CDK extends sbt.librarymanagement.DependencyBuilders {

    lazy val cdkDeps = { 
        val version = "1.114.0"

        Seq("core", "s3")
          .map(lib => stringToOrganization("software.amazon.awscdk") % lib % version)
    }


}

