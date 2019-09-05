package common

import com.typesafe.scalalogging.LazyLogging
import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContext
import scala.util.Success

class LoggingDirective(implicit ec: ExecutionContext) extends LazyLogging {

  def log =
    mapInnerRoute { inner => ctx =>
      val start = System.currentTimeMillis
      inner(ctx).andThen {
        case Success(_) =>
          val time = System.currentTimeMillis - start
          val request = ctx.request
          val requestString = s"${request.method.value} ${request.uri} ${request.protocol.value}"
          logger.info(s"responded to $requestString in ${time}ms")
      }
    }
}
