package tech.oheldarkaa.lectures.part2oop

object AnonymousClasses extends App {
  // works for traits and classes (abstract or not)
  abstract class Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal{
    override def eat: Unit = println("ahah omnomnom")
  }
  println(funnyAnimal.getClass) // AnonymousClasses$$anon$1
  // equivalent
  class AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("ahah omnomnom")
  }
  val funnyAnimal2 = new AnonymousClasses$$anon$1
  println(funnyAnimal2.getClass)


  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name. How can i help?")
  }

  val jim = new Person("Jim"){
    override def sayHi: Unit = println(s"Hi, my name is Jim. How can i be of service?")
  }

  println(jim.getClass) // AnonymousClasses$$anon$2
}
