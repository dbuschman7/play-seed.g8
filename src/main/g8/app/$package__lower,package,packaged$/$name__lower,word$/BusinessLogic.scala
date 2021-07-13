package $package;format="lower,package"$
package $name;format="lower,word"$

import play.api.libs.json.{Format, JsError, JsResult, JsSuccess, Json}
import zio.ZIO

object BusinessLogic {

  def computeSum(num1: Int, num2: Int): ZIO[Any, Nothing, Int] =
    for {
      one <- ZIO.succeed[Int](num1)
      two <- ZIO.succeed[Int](num2)
    } yield one + two

  def objectTransformation(input: JsResult[TestInput]): ZIO[Any, TestError, TestOutput] =
    input match {
      case JsSuccess(value, _) => ZIO.succeed[TestOutput](TestOutput(s"Hello, \${value.input}"))
      case JsError(_) => ZIO.fail[TestError](TestError("Invalid JSON input."))
    }

}

final case class TestInput(input: String)
object TestInput {
  implicit val __json: Format[TestInput] = Json.format[TestInput]

}


final case class TestOutput(output: String)
object TestOutput {
  implicit val __json: Format[TestOutput] = Json.format[TestOutput]
}


final case class TestError(error: String)
object TestError {
  implicit val __json: Format[TestError] = Json.format[TestError]
}