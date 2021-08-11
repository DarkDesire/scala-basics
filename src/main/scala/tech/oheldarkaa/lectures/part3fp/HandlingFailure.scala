package tech.oheldarkaa.lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU BUSTER")

  val potentialFailure = Try(unsafeMethod())
  potentialFailure.fold(
    fa => println(s"This is a fail. ${fa.getLocalizedMessage}"),
    fb => println(s"No error! Everything is fine!"),
  )

  def anotherPotentialFailure = Try {
    // code that might throw

  }

  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  // IF you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException("oops"))
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFallbackTry = betterUnsafeMethod() orElse betterBackupMethod()
  betterFallbackTry.foreach(println)

  // map, flatMap, filter
  println(aSuccess.map(_*2))
  println(aSuccess.flatMap(v => Success(v*10)))
  println(aSuccess.filter(_ > 10))

  //
}

object HTMLExercise extends App {

  /*
    Exercise.
    1. Try to renderHTML
   */
  val host = "localhost"
  val port = "8080"
  def renderHTML(page:String):Unit = println(page)

  class Connection{
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html> .... </html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }

    def getSafeConnection(host:String, port:String): Try[Connection] =
      Try(getConnection(host,port))
  }

  //1
  HttpService.getSafeConnection(host,port)
    .flatMap(c => c.getSafe("google.com"))
    .fold(
      _ => println("Unsuccess!"),
      page => renderHTML(page)
    )

  //1 for-comprehension
  val opPage = for {
    conn <- HttpService.getSafeConnection(host, port)
    page <- conn.getSafe("google.com")
  } yield page
  opPage.fold(
    _ => println("Unsuccess!"),
    page => renderHTML(page)
  )

  //
}