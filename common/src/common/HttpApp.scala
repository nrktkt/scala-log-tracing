package common

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging
import monix.execution.schedulers.TracingScheduler

import scala.concurrent.ExecutionContext
import scala.io.StdIn

trait HttpApp extends App with LazyLogging {
  implicit val system = ActorSystem("default", defaultExecutionContext = Some(TracingScheduler(ExecutionContext.global)))
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  def route: Route
  def port: Int

  def start() = {
    val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", port)

    logger.info(s"Server online at http://localhost:$port/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
