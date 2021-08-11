package tech.oheldarkaa.lectures.part2oop

object AbstractDataTypes extends App {
  // abstract (class with no def implementation)
  abstract class Animal {
    val creatureType: String = "wild"
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    override def eat: Unit = println("crunch crunch")
  }

  // traits - mixins
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferedMeal: String = "fresh meat"
  }

  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    override def eat: Unit = println("omnomnom")
    override def eat(animal: Animal): Unit =
      println(s"I'm a ${this.creatureType} and eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract classes
  // 1 - traits don't have constructor parameters
  // 2 - multiple traits may be inherited
  // 3 - traits = behaviour
}
