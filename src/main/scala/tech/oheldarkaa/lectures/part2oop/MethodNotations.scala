package tech.oheldarkaa.lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 20){
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} hanging out with ${person.name}"
    def +(guy: String): Person = new Person(s"${this.name} ($guy)", favoriteMovie)
    def unary_! : String = s"${this.name}, what the heck?!"
    def unary_+ : Person = new Person(name, favoriteMovie, age+1)
    def isAlive: Boolean = true
    def learns(thing:String): String = s"$name is learning $thing"
    def learnsScala(): String = this learns "Scala"
    def apply(): String = s"Hi, my name is ${this.name} and I like ${favoriteMovie}"
    def apply(times: Int): String = s"${this.name} watched $favoriteMovie $times times"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception")
  // infix notation = operator notation (syntactic sugar)

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))
  // ALL OPERATORS ARE METHODS

  ///////////////////////////////////////

  // prefix notation

  val x = -1 // eq with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix works only with - + ! ~
  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive)

  // apply specific
  println(mary.apply())
  println(mary())


  /// exercises check
  println((mary+"the Rockstar")())
  println((+mary).age)
  println(mary.learnsScala())
  println(mary learns "Scala")
  println(mary(10))
}
