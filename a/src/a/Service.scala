package a

import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding._
import Main.executionContext
import Main.system
import com.typesafe.scalalogging.LazyLogging
import common.CorrelationId

import scala.util.{Random, Success}
import common.propagate

class Service(bPort: Int) extends LazyLogging {

  def callB = {
    val greeting = greetings(Random.nextInt(greetings.length))
    val start = System.currentTimeMillis
    propagate(Http()
      .singleRequest(Post(s"http://localhost:$bPort", s"$greeting B")))
      .andThen { case Success(_) => logger.info(s"[${CorrelationId.local()}] b took ${System.currentTimeMillis - start}ms to respond") }
  }

  val greetings = List(
    "Marhaba",
    "Grüß Gott",
    "Namaskar",
    "Zdraveite",
    "Hola",
    "Hafa adai",
    "Nǐ hǎo",
    "Dobar dan",
    "God dag",
    "Hallo",
    "hyvää päivää",
    "Bonjour",
    "Dia dhuit",
    "Guten tag",
    "Yasou",
    "Shalom",
    "Namaste",
    "Konnichiwa",
    "Salam",
    "Cześć",
    "Ahoj",
    "Zdravstvuyte",
    "Sawubona"
  )
}
