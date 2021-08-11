package tech.oheldarkaa.lectures.part3fp

object WhatsAFunction extends App {

  // DREAM: use functions as first class elements
  // problem: OOP

  val doubler = new MyFunction[Int,Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(2))

  // function types =  Function1[A,B]
  val stringToIntConverter = new Function[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }
  println(stringToIntConverter("3")+4)

  val adder = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1+v2
  }
  /*val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1+v2
  }*/

  // Function types Function2[A,B,R] === (A,B) =>

  // ALL SCALA FUNCTIONS ARE OBJECTS

  /*
    1. Define a function which takes 2 string and concate them
    2. transform the MyPredicate and MyTransformer into function types
    3. define a func which takes an int and returns another func
    which takes an int and return an int
     - what's the type of this function
     - how to do it
   */

  // 1
  val concateFunc: (String, String) => String = {
    (v1: String, v2: String) => s"$v1 $v2"
  }
  println(concateFunc("Hello", "Scala"))
  // 3 curried function
  val hardFunc: (String) => (Int) => Int = str => x => str.toInt * x
  println(hardFunc("4")(4))

}

trait MyFunction[A,B] {
  def apply(element:A): B = ???
}
