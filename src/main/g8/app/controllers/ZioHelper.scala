package controllers

import play.api.mvc.{Action, ActionBuilder, BodyParser, Result}
import zio.{Runtime, UIO}

trait ZioHelper {

   implicit class ActionBuilderOps[+R[_], B](ab: ActionBuilder[R, B]) {
    case class AsyncTaskBuilder() {
      def apply(cb: R[B] => UIO[Result]): Action[B] = {
        ab.async { c =>
          val value: UIO[Result] = cb(c)
          Runtime.default.unsafeRunToFuture[Nothing, Result](value)
        }
      }
    }

    def zioTask: AsyncTaskBuilder = AsyncTaskBuilder()
  }

  implicit class ActionBuilderOpsParser[+R[_], B](ab: ActionBuilder[R, B]) {
    case class AsyncTaskBuilderParser[A](bp: BodyParser[A]) {
      def apply(cb: R[A] => UIO[Result]): Action[A] = {
        ab.async[A](bp) { c =>
          val value: UIO[Result] = cb(c)
          Runtime.default.unsafeRunToFuture[Nothing, Result](value)
        }
      }
    }
    def zioTaskBodyParser[A](bp: BodyParser[A]) = AsyncTaskBuilderParser[A](bp)

  }
}
