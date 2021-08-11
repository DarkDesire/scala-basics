package tech.oheldarkaa.lectures.part4pm

import scala.util.Random

object PatternMatching extends App {
  // switch on steroids
  val random = new Random
  val x = random.nextInt(5)

  val description = x match {
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "i don't care" // _ = wildcard
  }
  println(s"$x: $description")

  // 1. Decompose values
  case class Person(name: String, age:Int)
  val bob = Person("Bob", 21)
  val mary = Person("Mary", 20)
  mary match {
    case Person(n,a) if n == "Bob" && a == 20 => println("That's our Bob!")
    case Person(n,_) if n == "Bob" => println("Here is another Bob!")
    case _ => println("Another guy")
  }

  /*
    1. cases are matched in order
    2. what if no case match => scala.MatchError
    3. type of the PM expression? unified type of all the types in all the cases
    4. PM works really well with case classes
   */

  // PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
    // missed Parrot
  }

  // match everything
  val isEven = x match {
    case n if n%2==0 => true
    case _ => false
  }
  // WHY?!


  // Exercise
  // simple function uses PM
  //  takes an Expr => human readable form
  trait Expr
  case class Number(n: Int) extends Expr {
    override def toString: String = n.toString
  }
  case class Sum(e1: Expr, e2: Expr) extends Expr {
    override def toString: String = s"($e1 + $e2)"
  }
  case class Prod(e1: Expr, e2: Expr) extends Expr {
    override def toString: String = s"($e1 * $e2)"
  }

  println(Prod(Sum(Number(2),Number(1)), Number(3)))

}
