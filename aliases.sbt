addCommandAlias(
  "up2date",
  "reload plugins; dependencyUpdates; reload return; dependencyUpdates",
)
addCommandAlias("fullClean", "clean ;cleanFiles")
addCommandAlias("tc", "Test / compile")
addCommandAlias("cc", "fullClean   ;+tc")
