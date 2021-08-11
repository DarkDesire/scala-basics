package tech.oheldarkaa.lectures.part3fp.collections

import scala.util.Random

object Sequences extends App {

  // Seq
  // well-defined order
  // can be indexed
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(7,5,6))
  println(aSequence.sorted)


  // Ranges
  val aRange : Seq[Int] = 1 to 10 by 2
  aRange.foreach(println)

  // List - A Linear Seq immutable linked list
  // head, tail, isEmpty are fast  O(1)
  // most operators are O(n): length, reverse
  // Nil (empty), :: (not empty)
  val aList = List (1,2,3)
  val prepended = 42 :: aList
  //val prepended = 42 +: aList
  println(prepended)
  val append = aList :+ 42
  println(append)

  val apples5 = List.fill(5)("apple")
  println(apples5)

  println(aList.mkString("-"))

  // Arrays
  // eq to simple Java array
  // can be mutated
  // index is fast
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[String](3)
  println(threeElements)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2,0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)


  // vectors
  // effectively constant indexed read and write: O(log32(N))
  // fast element addition: append/prepend
  // implemented as a fixed-branched tree (branch of 32 elem on level)
  // good performance for large objects
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vector vs lists

  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      _ <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt)
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tail
  // - updating an element in the middle takes a long
  println(getWriteTime(numbersList))
  // depth of the tree is small
  // needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))

}
