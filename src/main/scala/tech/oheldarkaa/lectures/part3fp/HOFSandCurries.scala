package tech.oheldarkaa.lectures.part3fp

import scala.annotation.tailrec

object HOFSandCurries extends App {

  val superFunction: (Int, //1st INT
    (String, (Int => Boolean)) => Int) //2nd Func
    => (Int => Int) = null

  // higher order function (HOF)
  // map, filter, flatMap

  // function that applies a function nTimes over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) => f(f(f(x))) = nTimes(f, 2, f(x))
  // nTimes(f, n, x) => f(f(..(x))) = nTimes(f, n-1, f(x))

  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))
  }

  val plusOne: Int => Int = _ + 1
  println(nTimes(plusOne, 10, 1))


  // nTb(f,n) = x => f(f(..(x)))
  // increment 10 = nTb(plusOne, 10) = x => plusOne(plusOne(...plusOne(x)))
  // val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): Int => Int = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))
  }

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))


  // curried function
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3)
  println(add3(10))
  println(superAdder(3)(10))

  // functions with multiple parameters list
  def curriedFormatter(str: String)(x: Double): String = str.format(x)

  val standardFormat: Double => String = curriedFormatter("%4.2f")
  val preciseFormat: Double => String = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  /*
    Exercises.
    Expand MyList:
      - foreach method A => Unit
        [1,2,3].foreach(x=> println(x))
      - sort function ((A,A) => Unit) => MyList
        [1,2,3].sort( (x,y) => y-x ) => 3,2,1
      - zipWith (list, (A,A) => B) => MyList[B]
        [1,2,3].zipWith([4,5,6], x*y) = [1*4,2*5,3*6] = [2,10,18]
      - fold(start)(function) => a value
        [1,2,3].fold(0)(x + y) = 6
        0+1 , => 1+2, => 3+3 => 6

    2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
       fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int

    3. compose(f,g) => x => f(g(x))
       andThen(f,g) => x => g(f(x))
   */

  //
  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) = x => y => f(x,y)
  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int = (x,y) => f(x)(y)
  def compose[A,B,C](f: A => B, g: C => A): C => B = x => f(g(x))
  def andThen[A,B,C](f: A => B, g: B => C): A => C = x => g(f(x))

  def superAdder2: (Int => Int => Int) = toCurry(_ + _)
  def add4 = superAdder2(4)
  println(add4(17))

  def simpleAdder = fromCurry(superAdder2)
  println(simpleAdder(4,17))

  val add2 = (x:Int) => x+2
  val times3 = (x:Int) => x*3
  val composed = compose(add2, times3) // 4*3=12 + 2
  val ordered = andThen(add2, times3) // (4+2)*3=18

  println(composed(4))
  println(ordered(4))
}