package tech.oheldarkaa.lectures.part3fp

import scala.annotation.tailrec
import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(123)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)

  def unsafeMethod(): String = null
  val result = Option(unsafeMethod())
  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"
  val chainResult = Option(unsafeMethod()).getOrElse(backupMethod())

  // design unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")
  val betterChainedResult = betterUnsafeMethod() orElse(betterBackupMethod())

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // unsafe!

  // map flatMap filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(_ > 124))
  println(myFirstOption.flatMap(x => Option(x*10)))

  /*
   Exercise.
   */

  val config: Map[String, String] = Map(
    //fetched from elsewhere
    "host"->"176.45.36.1",
    "port"->"80"
  )
  class Connection {
    def connect() = "Connected"
  }
  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some (new Connection)
      else None
    }

    // try to establish a connection, print connect method
  }

  def establishConnection(config: Map[String, String]): Unit = {

    val host = config.get("host")
    val port = config.get("port")

    def connection: Option[Connection] = host.flatMap(h => port.flatMap(p => Connection(h,p)))

    @tailrec
    def tryConnect(times: Int, opConn: Option[Connection] = None): Unit = {
      val connectionStatus = opConn.map(c => c.connect())
      connectionStatus match {
        case Some(status) =>
          println(s"Tried: $times times")
          println(status)
        case None => tryConnect(times+1, connection)
      }
    }
    tryConnect(1, connection)
  }
  println("establishConnection")
  establishConnection(config)

  // chained calls
  config.get("host")
    .flatMap(h => config.get("port")
      .flatMap(p => Connection(h, p))
      .map(c => c.connect()))
    .foreach(println)

  // for-comprehension

  println("forConnectionStatus")
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    conn <- Connection(host, port)
  } yield {
    conn.connect()
  }
}
