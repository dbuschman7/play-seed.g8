# A [Giter8][g8]/[SBT][sbt]/[Scala][scala]/Play Framework template

Kudos go to DevInsideYou for the base template which replaces much of my custom stuff with only minor tweaks/addtions that I want to have.

A Giter8 template for a fully configured Scala SBT single but multibuild ready project. It is configured in a slightly opinionated but mostly dependency free fashion.

All versions will always stay hardcoded as opposed to being chooseable or automatically updatable via Giter8 in order to guarantee the soundness of the build. In other words, assuming you don't have any global settings/plugins the build won't break unless you manually break it by changing versions by hand. Enjoy!

```bash
sbt new dbuschman7/play-seed.g8  # for Scala 2.x
```

or

```bash
g8 dbuschman7/play-seed  # for Scala 2.x
```

[g8]: http://www.foundweekends.org/giter8/
[sbt]: https://www.scala-sbt.org/
[scala]: https://www.scala-lang.org/
