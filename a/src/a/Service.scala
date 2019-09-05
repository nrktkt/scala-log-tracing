package a

import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding._
import Main.executionContext
import Main.system
import com.typesafe.scalalogging.LazyLogging

import scala.util.Success

class Service(bPort: Int) extends LazyLogging {
  def callB = {
    val start = System.currentTimeMillis
    Http()
      .singleRequest(Post(s"http://localhost:$bPort", "Hello B"))
      .andThen { case Success(_) => logger.info(s"b took ${System.currentTimeMillis - start}ms to respond") }
  }
}
