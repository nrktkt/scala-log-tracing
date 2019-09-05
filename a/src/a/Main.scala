package a

import akka.http.scaladsl.server.Directives._
import common.{HttpApp, LoggingDirective}

object Main extends HttpApp {

  val port = 8080

  val loggingDirective = new LoggingDirective()
  val service = new Service(8081)

  val route = loggingDirective.log(
    complete(
      service.callB
    )
  )

  start()
}

