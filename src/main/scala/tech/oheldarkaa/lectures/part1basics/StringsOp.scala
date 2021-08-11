package tech.oheldarkaa.lectures.part1basics

object StringsOp extends App {
  val str: String = "Hello, I'm learning Scala"

  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.substring(9))
  println(str.split(' ').toList)
  println(str.startsWith("Hello"))
  println(str.startsWith("Hellow"))
  println(str.replace(" ", "_"))
  println(str.toLowerCase)
  println(str.toUpperCase())
  println(str.length)
  println(str.length)
  println(str.reverse)
  println(str.take(2))

  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  val iterable = aNumberString.toIterable

  println(aNumber)
  println(iterable)
  println('a' +: aNumberString :+ 'z')
  println('a' +: aNumberString :+ 'z')

  // String interpolators
  // S interpolators
  val age = 20
  println(s"Age is: ${age + 1}")
  // F interpolators
  val speed = 1.2f
  println(f"The speed is $speed%2.5f")

  // raw-interpolator
  val newlineStr = "This is a \n newline"
  println(raw"This is a \n newline")
  println(raw"$newlineStr") // carefully
}
