package common

import java.util.Base64

import akka.http.scaladsl.server.Directives._
import monix.execution.misc.Local

import scala.util.Random

object CorrelationIdDirectives {

  val withCorrelationId = mapInnerRoute { inner => ctx =>
    val id = ctx.request.header[CorrelationIdHeader] match {
      case Some(CorrelationIdHeader(id)) => id
      case None =>
        val bytes = new Array[Byte](16)
        Random.nextBytes(bytes)
        Base64.getUrlEncoder.withoutPadding.encodeToString(bytes)
    }

    Local.isolate {
      CorrelationId.local := id
      inner(ctx)
    }
  }
}
