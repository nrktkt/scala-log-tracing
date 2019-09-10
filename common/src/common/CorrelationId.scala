package common

import monix.execution.misc.Local

object CorrelationId {
  val local = Local("missing")
}
