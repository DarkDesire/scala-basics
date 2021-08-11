package tech.oheldarkaa.lectures.part2oop

abstract class MyList2[+A] {
  def head: A

  def tail: MyList2[A]

  def isEmpty: Boolean

  def nonEmpty: Boolean

  def add[B >: A](element: B): MyList2[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  // higher-order functions
  // receive other functions as parameters
  def map[B](transformer: A => B): MyList2[B]

  def filter(predicate: A => Boolean): MyList2[A]

  def flatMap[B](transformer: A => MyList2[B]): MyList2[B]

  // HOF extended
  def foreach(f: A => Unit): Unit

  def sort(compare: (A, A) => Int): MyList2[A]

  def zipWith[B, C](list: MyList2[B], zip: (A, B) => C): MyList2[C]

  def fold[B](start: B)(operator: (B, A) => B): B

  // concatenation
  def ++[B >: A](list: MyList2[B]): MyList2[B]
}

case object Empty2 extends MyList2[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList2[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def nonEmpty: Boolean = !isEmpty

  def add[B >: Nothing](element: B): MyList2[B] = Cons2(element, Empty2)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList2[B] = Empty2

  def filter(predicate: Nothing => Boolean): MyList2[Nothing] = Empty2

  def ++[B >: Nothing](list: MyList2[B]): MyList2[B] = list

  def flatMap[B](transformer: Nothing => MyList2[B]): MyList2[B] = Empty2

  def foreach(f: Nothing => Unit): Unit = ()

  def sort(compare: (Nothing, Nothing) => Int): MyList2[Nothing] = Empty2

  def zipWith[B, C](list: MyList2[B], zip: (Nothing, B) => C): MyList2[C] = {
    if (list.nonEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty2
  }

  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons2[+A](h: A, t: MyList2[A]) extends MyList2[A] {
  def head: A = h

  def tail: MyList2[A] = t

  def isEmpty: Boolean = false

  def nonEmpty: Boolean = !isEmpty

  def add[B >: A](element: B): MyList2[B] = Cons2(element, this)

  def printElements: String = {
    if (t.isEmpty) "" + h
    else h.toString + " " + t.printElements
  }

  def filter(predicate: A => Boolean): MyList2[A] = {
    if (predicate(h)) Cons2(h, t.filter(predicate))
    else t.filter(predicate)
  }

  def map[B](transformer: A => B): MyList2[B] = {
    Cons2(transformer(h), t.map(transformer))
  }

  /*
    [1,2] ++ [3,4,5]
    - Cons2(1, [2] ++ [3,4,5])
    - Cons2(1, Cons2(2, Empty2 + [3,4,5]))
    - Cons2(1, Cons2(2, Cons2(3,Cons2(4,Cons2(5, Empty2)))))
   */
  def ++[B >: A](list: MyList2[B]): MyList2[B] =
    Cons2(h, t ++ list)

  /*
    [1,2].flatMap(n => [n, n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty2.flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty2
    = [1,2,2,3]
   */
  def flatMap[B](transformer: A => MyList2[B]): MyList2[B] =
    transformer(h) ++ tail.flatMap(transformer)

  // extended
  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyList2[A] = {
    // switch to tail recursion
    def insert(x: A, sortedList: MyList2[A]): MyList2[A] = {
      if (sortedList.isEmpty) Cons2(x, Empty2)
      else if (compare(x, sortedList.head) <= 0) Cons2(x, sortedList)
      else Cons2(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: MyList2[B], zip: (A, B) => C): MyList2[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Cons2(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  def fold[B](start: B)(operator: (B, A) => B): B = t.fold(operator(start, h))(operator)
}


case class EvenPredicate() extends (Int => Boolean) {
  override def apply(v1: Int): Boolean = v1 % 2 == 0
}

case class StringToIntTransformer() extends (String => Int) {
  override def apply(v1: String): Int = v1.toInt
}

object ListTest2 extends App {

  val listOfIntegers: MyList2[Int] = Cons2(1, Cons2(2, Cons2(3, Empty2)))
  val anotherListOfIntegers: MyList2[Int] = Cons2(4, Cons2(5, Empty2))
  val listOfStrings: MyList2[String] = Cons2("Hello", Cons2("Scala", Empty2))

  println(listOfIntegers)
  println(listOfStrings)

  println(listOfIntegers.map(_ * 2))
  println(listOfIntegers.filter(_ % 2 == 0))

  println(listOfIntegers ++ anotherListOfIntegers)
  println(listOfIntegers.flatMap((elem: Int) => Cons2(elem, Cons2(elem + 1, Empty2))))


  listOfIntegers.foreach(println)

  println("sort")
  listOfIntegers.sort((x, y) => y - x).foreach(println)
  println("sort2")
  listOfIntegers.sort((x, y) => x - y).foreach(println)
  println("zipWith")
  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))

  println("fold")
  println(anotherListOfIntegers.fold(0)(_ + _))

  println("for-comprehension")
  val comp1 = for {
    i <- listOfIntegers // 3 elems
  } println(i)

  val comp2 = for {
    i <- listOfIntegers // 3 elems
    s <- listOfStrings // 2 elems
  } yield s"$i-$s" // 6 elems
  println(comp2)
}

/*
  1. Generic trait MyPredicate[-T] with a little method test(value:T):Boolean
  (Have different implementation of method) - transform(value:A) -> B
  2. Generic trait MyTransformer[-A,B]
  3. MyList:
    - map(transformer) => MyList
    - filter(predicate) => MyList
    - flatMap(transformer from A to MyList[B]) => myList[B]

  pseudo-code
  class EvenPredicate extends MyPredicate[Int]
  class StringToIntTransformer extends MyTransformer[String, Int]

  -map:     MyList[1,2,3].map(n * 2) => MyList[2,4,6]
  -filter:  MyList[1,2,3,4].map(n % 2) => MyList[2,4]
  -flatMap: MyList[1,2,3].flatMap(n => [n, n+1]) = MyList[1,2,2,3,3,4]
 */
