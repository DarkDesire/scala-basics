package tech.oheldarkaa.lectures.part2oop

object CaseClasses extends App {

  /*
    equals, hashCode, toString
   */

  case class Person(name: String, age:Int)
  // 1. class parameters are fields
  val jim = Person("Jim", 34)
  println(jim.age)

  // 2. sensible toString
  // println(instance) = println(instance.toString)
  println(jim) // Person(Jim,42)
  // otherwise without case => Person$2c8d66b2

  // 3. equals and hashCode implemented OOTB
  val jim2 = Person("Jim", 34)
  println(jim == jim2) // compare by fields
  println(jim eq jim2) // compare link

  // 4. CCs have handy copy method
  val jim3 = jim2.copy(age = 45)
  println(jim3 == jim) // compare by fields
  println(jim3 eq jim) // compare link

  // 5. CCs have companion object
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. CCs are serializable
  // Akka

  // 7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING

  case object UnitedKingdom{
    def name: String = "The UK of GB and NI"
  }
  /*
    Expand MyList - use case classes and case objects
   */


}
