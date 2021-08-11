package tech.oheldarkaa.lectures.part4pm

import tech.oheldarkaa.lectures.part2oop.{Cons2, Empty2, MyList2}

object AllThePatterns extends App {

  // 1 - constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
    case _ => "other"
  }

  // 2 - match anything
  // 2.1 wildcard
  val matchAnything = x match {
    case _ =>
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3 - tuples
  val aTuple = (1,2)
  val nestedTuple = (1, (2,3))
  val matchATuple = aTuple match {
    case (1,1) => //tuple of literals
    case (something, 2) => //try to extract something
      s"I've found $something"
  }

  // PMs can be NESTED
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) => println(v)
  }

  // 4- case classes - constructor pattern
  // PMs can be nested with CCs as well
  val aList: MyList2[Int] = Cons2(1, Cons2(2, Empty2))
  val matchAList = aList match {
    case Empty2 =>
    case Cons2(_,Cons2(subhead, subtail)) =>
      println(subhead)
      println(subtail)
    case Cons2(h,t) =>
      println(h)
      println(t)
  }

  // 5 - list patterns
  val aStandardList = List(1,2,3,42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => // extractor
    case List(1, _*) => // list of arbitrary length
    case 1 :: List(_) => // infix patter
    case List(1,2,3) :+ 42 => // infix patter
    case ::(head, next) =>
      println(head)
      println(next)
    case Nil =>
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val unknownMatching = unknown match {
    case list:List[Int] => // explicit type specifier
    case _ =>
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case nonEmpty@Cons2(h, t) => nonEmpty // name binding
    case Cons2(1, rest @ Cons2(2, _)) => rest // name binding
    case Empty2 =>
    case _ =>
  }

  // 8 -multi-patterns
  val multiPattern = aList match {
    case Empty2 | Cons2(0, _) =>  // compound pattern (multi-pattern)
    case _ =>
  }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case Cons2(h, Cons2(specialElement, t)) if specialElement % 2 ==0  => // if guard
    case _ =>
  }

  //
  /*
    Questions.
   */

  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfString: List[String] => "list of string"
    case listOfNumbers: List[Int] => "list of numbers"
    case ::(head, next) =>
    case Nil =>
  }
  println(numbersMatch)
  // JVM trick question

}

