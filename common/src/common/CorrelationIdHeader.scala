package common

import akka.http.scaladsl.model.headers.{ModeledCustomHeader, ModeledCustomHeaderCompanion}

import scala.util.Success

final class CorrelationIdHeader(val value: String)
      extends ModeledCustomHeader[CorrelationIdHeader] {
    val renderInRequests  = true
    val renderInResponses = true
    val companion         = CorrelationIdHeader
  }

  object CorrelationIdHeader extends ModeledCustomHeaderCompanion[CorrelationIdHeader] {
    val name                 = "X-Correlation-Id"
    def parse(value: String) = Success(new CorrelationIdHeader(value))
  }