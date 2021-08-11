package tech.oheldarkaa.lectures.part2oop

object Objects extends App {
  // Scala doesn't have class-level functionality

  object Person { // type + its only instance
    // static/class - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, fasther: Person): Person =
      new Person("Bobby")
  }
  class Person(val name:String) {
    // instance- level functionality
  }
  // class + object (COMPANIONS)

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = SINGLETON INSTANCE

  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john)

  val bobbie = Person(mary, john)

  // Scala Applications = scala object with
  // def main(args: Array[String]): Unit

}
