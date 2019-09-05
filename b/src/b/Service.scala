package b

import com.typesafe.scalalogging.LazyLogging

class Service extends LazyLogging {
  def answer(message: String) = {
    logger.info(s"a says '$message'")
    "Thanks!"
  }
}
