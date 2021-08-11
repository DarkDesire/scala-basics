package tech.oheldarkaa.lectures.part2oop

import tech.oheldarkaa.playground.{Cinderella, PrinceCharming}
import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // package members are accessible by simple name
  val writer = new Writer("Mary", "Rapid", 32)

  // import the package
  val princess = new Cinderella
  // equal to (fully qualified name)
  val princess2= new tech.oheldarkaa.playground.Cinderella

  // packages are hierarchy
  // matching folder structure

  // package object
  // can only be 1 for the package / file package.scala
  // define constants and methods
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

  // 1. use FQ name
  val date = new Date(2021,1,1)
  // 2. use aliasing
  val sqlDate = new SqlDate(2021,1,1)

  // default imports
  // java.lang - Objects, String, Exception
  // scala - Int, Nothing, Function
  // scala.predef - println, ???, =>

}
