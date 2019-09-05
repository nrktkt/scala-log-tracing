// build.sc
import mill._, scalalib._

trait BaseModule extends ScalaModule {
  def scalaVersion = "2.12.9"
  def ivyDeps = Agg(
    ivy"com.typesafe.akka::akka-http:10.1.9",
    ivy"com.typesafe.akka::akka-stream:2.5.25",
    ivy"io.monix::monix:3.0.0-RC5",
    ivy"com.typesafe.scala-logging::scala-logging:3.9.2",
    ivy"org.slf4j:slf4j-simple:1.7.28"
  )
}

object a extends BaseModule {
  def moduleDeps = List(common)
}

object b extends BaseModule {
  def moduleDeps = List(common)
}

object common extends BaseModule
