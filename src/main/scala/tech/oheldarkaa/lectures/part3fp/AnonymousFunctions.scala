package tech.oheldarkaa.lectures.part3fp

object AnonymousFunctions extends App {

  // anonymous function (LAMBDA)
  val double = (x:Int) => x*2

  // multiple parameters
  val adder: (Int, Int) => Int = (a:Int, b:Int) => a+b
  // no params
  val justDoSmth: () => Int = () => 3
  // careful
  println(justDoSmth) // function itself
  println(justDoSmth()) // call

  // STYLE. curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntatic sugar
  val niceIncrementer: Int => Int = _ + 1 // eq to x = x+1
  val niceAdder: (Int, Int) => Int = _ + _ // eq to (a,b) => a+b

  /*
   Exercises:
    1. MyList. Replace all FunctionX calls with lambdas
    2. rewrite the "special" adder as an anonymous function
   */
  // 1 done
  // 2
  val superAdder = (str:String) => (x:Int) => str.toInt*x
  val superAdder4 = superAdder("4")
  println(double(4))
  println(superAdder4(4))

}
