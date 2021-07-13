package controllers

import com.myapp.play.{BusinessLogic, TestInput}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

import javax.inject._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents)
  extends BaseController
    with ZioHelper {

  /**
   * Simple example of an action that can't fail.
   *
   * In cURL:
   * $ curl localhost:9000
   */
  def index(): Action[AnyContent] =
    Action
      .zioTask { _ =>
        BusinessLogic
          .computeSum(1, 2)
          .map { int: Int => Ok(s"1 + 2 = $int") }
      }

  /**
   * Simple example of an action with error handling.
   * This will transform the input if the input is okay, or it will return an error.
   * Note that if the `fold` is removed, ZIO complains that the error must be handled.
   *
   * Try these examples in cURL:
   * $ curl localhost:9000 -H "Content-Type: application/json" -d '{"input": "testing"}'
   * $ curl localhost:9000 -H "Content-Type: application/json" -d '{"foo": "bar"}'
   */
  def testJson(): Action[JsValue] =
    Action
      .zioTask(controllerComponents.parsers.json) { request =>
        BusinessLogic
          .objectTransformation(request.body.validate[TestInput])
          .fold(
            error => BadRequest(Json.toJson(error)),
            success => Ok(Json.toJson(success))
          )
      }
}

