package tech.oheldarkaa.lectures.part2oop

object OOBasics extends App {

  val person = new Person("John", 25)
  println(person)
  println(person.age)
  person.greet("Daniel")
  person.greet()

  // counter test
  val counter = new Counter
  counter.inc(5).print
}


// constructor
class Person(name: String, val age:Int) {
  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")
  // overloading
  def greet(): Unit = println(s"Hi, I'm $name")

  // multiple constructors // can be omit with default params
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}
// class parameters are not FIELDS by default

// exercise1
class Novel(name:String, year:Int, author: Writer){
  def authorAge: Int = year - author.year
  def isWrittenBy(author: Writer): Boolean = author == this.author
  def copy(new_year:Int): Novel = new Novel(name, new_year, author)
}
class Writer(firstName:String, surName:String, val year:Int){
  def fullName:String = s"$firstName $surName"
}

// exercise2
class Counter(val count: Int = 0){
  def inc() = {
    println("incrementing")
    new Counter(count+1)
  }
  def dec() = {
    println("decrement")
    new Counter(count+1)
  }

  def inc(n:Int): Counter = {
    if (n <= 0) this
    else inc().inc(n-1)
  }
  def dec(n:Int): Counter = {
    if (n <= 0) this
    else dec().dec(n-1)
  }
  def print = println(count)
}