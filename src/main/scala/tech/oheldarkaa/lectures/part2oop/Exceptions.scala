package tech.oheldarkaa.lectures.part2oop

object Exceptions extends App{
  val x: String = null
  // println(x.length)
  // this will crush with a NPE

  // 1. throwing exceptions
  //val aWeirdValue: String = throw new NullPointerException

  // throwable classes extends Throwable class
  // Exception and Error are the major Throwable
  // Error something wrong in system (JVM)


  // 2. catching exceptions
  def getInt(withExceptions: Boolean): Int = {
    if(withExceptions) throw new RuntimeException("no Int for you")
    else 42
  }
  val potentialFail = try {
    // code that might fail
    getInt(true)
  } catch {
    // catching errors
    case e: RuntimeException => println(e.getMessage)
    case e: NullPointerException => println(e.getMessage)
    case e: Exception => println("caught any other exception")
  } finally {
    // code that will get executed NO MATTER WHAT
    // doesn't influence the return type of this expression
    // use finally only for side effects
    println("finally")
  }

  // 3. How to define your own Exception
  /*class MyException extends Exception
  val exception = new MyException
  throw exception*/

  /* Exercises
    1. Crash your program with OOM error
    2. Crash with SOError
    3. PocketCalculator
      - add(x,y)
      - subtract(x,y)
      - multiply(x,y)
      - divide(x,y)

      Throw:
        -OverflowException if add(x,y) exceeds Int.MAX_VALUE
        -UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
        -MathCalculationException for division by zero
   */
  // 1 Crash your program with OOM
  // val array = Array.ofDim(Int.MaxValue)

  // 2 Crash with SOError
  /*def infinite: Int = 1 + infinite
  def noLimit:Int = infinite
  noLimit*/

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")
  object PocketCalculator {
    def add(x:Int, y:Int): Int = {
      val newValue = x+y
      if (newValue >= Int.MaxValue) throw new OverflowException
      else newValue
    }
    def subtract(x:Int,y:Int):Int = {
      val newValue = x-y
      if (newValue <= Int.MinValue) throw new UnderflowException
      else newValue
    }
    def multiply(x:Int, y:Int):Int = x*y
    def divide(x:Int, y:Int): Float = {
      if (y == 0) throw new MathCalculationException
      else x.toFloat/y
    }
  }
  // PocketCalculator.add(Int.MaxValue, 0)
  // PocketCalculator.subtract(Int.MinValue, 0)
  // println(PocketCalculator.multiply(1, 100))
  // println(PocketCalculator.divide(100, 0))

}
