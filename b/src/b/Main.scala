package b

import akka.http.scaladsl.server.Directives._
import common.{HttpApp, LoggingDirective}
import common.CorrelationIdDirectives.withCorrelationId

object Main extends HttpApp {

  val port = 8081

  val loggingDirective = new LoggingDirective()
  val service = new Service()

  val route =
    withCorrelationId (
      loggingDirective.log (
        entity(as[String]) ( message =>
          complete(
            service.answer(message)
          )
        )
      )
    )

  start()
}

