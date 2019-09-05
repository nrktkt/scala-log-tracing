package b

import akka.http.scaladsl.server.Directives._
import common.{HttpApp, LoggingDirective}

object Main extends HttpApp {

  val port = 8081

  val loggingDirective = new LoggingDirective()
  val service = new Service()

  val route =
    loggingDirective.log (
      entity(as[String]) ( message =>
        complete(
          service.answer(message)
        )
      )
    )

  start()
}

