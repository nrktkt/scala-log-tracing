package b

import com.typesafe.scalalogging.LazyLogging
import common.CorrelationId

class Service extends LazyLogging {
  def answer(message: String) = {
    logger.info(s"[${CorrelationId.local()}] a says '$message'")
    "Thanks!"
  }
}
