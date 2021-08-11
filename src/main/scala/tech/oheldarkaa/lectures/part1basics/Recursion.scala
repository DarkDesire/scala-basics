package tech.oheldarkaa.lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorialFunc(x: Int): BigInt = {
    @tailrec
    def innerFactorial(x: Int, accumulator: BigInt): BigInt = x match {
      case x if x == 1 => accumulator
      case x => innerFactorial(x - 1, x * accumulator)
    }

    innerFactorial(x, 1)
  }

  println(factorialFunc(1))
  println(factorialFunc(3000))


  // concate string N times
  def concateString(str: String, n: Int): String = {
    @tailrec
    def innerTailrec(n: Int, accumulator: String): String = n match {
      case n if n == 0 => accumulator
      case n => innerTailrec(n - 1, s"$accumulator $str".trim)
    }

    innerTailrec(n, "")
  }

  println(concateString("five", 5))
  println(concateString("two", 2))


  // fibo tailrec
  def fibo(n: Int): Int = {
    @tailrec
    def innerTailrec(i: Int, last: Int, nextToLast: Int): Int = i match {
      case i if i >= n => last
      case i => innerTailrec(i + 1, last + nextToLast, last)
    }

    if (n <= 2) 1
    else innerTailrec(2, 1, 1)
  }

  println(fibo(8))
}
