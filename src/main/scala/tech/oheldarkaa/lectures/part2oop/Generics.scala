package tech.oheldarkaa.lectures.part2oop

object Generics extends App {
  class MyList[+A] {
    // use the type A inside class definition
    def add[B >: A](element: B): MyList[B] = ???
    /*
     solve HARD QUESTION
     A = Cat
     B = Dog = Animal
     */

  }
  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // !!!!!!!!!!!!!!!!!!!!!!!!!!
  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION
  // cats+dogs - will turn it into List[Animal]

  // 2. no, INVARIANCE
  class InvariantList[A]
  // IMPOSIBLE val invariantAnimalList:InvariantList[Animal] = new InvariantList[Cat]
  val invariantAnimalList:InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no! CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]




  // bounded types
  class Cage[A <: Animal](animal: A)
  // upper bounded
  // only accepts A which is subtype of Animal
  val cage = new Cage(new Dog)

  class Car
  // impossible !
  // val newCarCage = new Cage(new Car)

  class Cage2[A >: Animal](animal: A)
  // lower bounded type


  // FINALLY
  // Variance: IF B extends A , should List[B] extends List[A]
  // trait List[+A] | yes, (covariant) + HARD QUESTION (solve with bounded types)
  // trait List[A] | no, (invariant) - default
  // trait List[-A] | hell no, (contravariant)
}
