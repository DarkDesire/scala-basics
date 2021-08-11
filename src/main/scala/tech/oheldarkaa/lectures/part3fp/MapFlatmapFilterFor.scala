package tech.oheldarkaa.lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1,2,3)
  println(list)
  list.head
  list.tail
  println("> map")
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  println("> filter")
  println(list.filter(_ > 2))

  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))


  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')
  val colors = List("black", "white")

  // "iterating"
  println("> iterating")
  val combinations = numbers.flatMap(n => chars.map(c => "" + c + n))
  println(combinations)

  val moarCombinations = numbers
    .flatMap(n => chars
      .flatMap(c => colors
        .map(color => "" + c + n + '-' +color)))
  println(moarCombinations)

  // foreach
  println("> foreach")
  list.foreach(println)

  // for-comprehension
  println("> for-comprehension ")
  val forCombinations = for {
    n <- numbers if n%2==0 // (with guard/ filter)
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color

  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map { x=>
     x * 2
  }

  /*
    1. MyList supports for-comprehensions?
    2. A small collection of at most ONE element - Maybe[+T]
       OneElement, Empty
       - map, flatMap, filter

   */

}

abstract class Maybe[+A] {
  def map[B](f: A => B): Maybe[B]
  def flatMap[B](f: A=>Maybe[B]):Maybe[B]
  def filter(f: A => Boolean):Maybe[A]
}
case object MaybeNot extends Maybe[Nothing]{
  def map[B](f: Nothing => B): Maybe[B] = MaybeNot
  def flatMap[B](f: Nothing => Maybe[B]): Maybe[B] = MaybeNot
  def filter(f: Nothing => Boolean): Maybe[Nothing] = MaybeNot
}
case class Just[+A](elem:A) extends Maybe[A] {
  def map[B](f: A => B): Maybe[B] = Just(f(elem))
  def flatMap[B](f: A => Maybe[B]): Maybe[B] = f(elem)
  def filter(f: A => Boolean): Maybe[A] =
    if (f(elem)) this
    else MaybeNot
}

object MaybeTest extends App {
  println("> for-comprehesion Maybe")

  val just3 = Just(3)
  println(just3)
  println(just3.map( _ * 2))
  println(just3.flatMap(x => Just(x % 2 == 0)))
  println(just3.filter(x => x % 2 == 0))



  val oneElem = Just("x").map(str => s"$str-${(Math.random()*10).toInt}")
  println(oneElem)

  val toCube = (x:Int) => Just(x << 3)
  val flatMapCube = Just(3).flatMap{ x =>
    println(x)
    toCube(x)
  }
  println(flatMapCube)
}
