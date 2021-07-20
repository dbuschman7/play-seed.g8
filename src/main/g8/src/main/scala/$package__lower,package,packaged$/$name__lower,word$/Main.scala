package $package;format="lower,package"$
package $name;format="lower,word"$

import zio.console._

object Main extends zio.App {

  val myAppLogic =
    for {
      _ <- putStrLn("─" * 100)
      _ <- putStrLn("hello world")
      _ <- putStrLn("─" * 100)
    } yield ()

  def run(args: List[String]) = 
    myAppLogic.exitCode

}


