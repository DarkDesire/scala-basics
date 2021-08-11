package tech.oheldarkaa.lectures.part3fp.collections

import scala.annotation.tailrec

object TuplesAndMaps extends App {
  // tuples = finite ordered "lists"
  val aTuple = (2, "Hello, Scala") // Tuple2[Int,String] = (Int, String)
  val (int,str) = aTuple
  println(aTuple.copy(_2 = "Good Bye, Java"))
  println(aTuple.swap) // ("Hello, Scala", 2)


  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()
  val phonebook = Map(
    ("Jim", 555),
    "Daniel" -> 789
  ).withDefaultValue(-1)
  println(phonebook)

  println(phonebook.contains("Jim"))
  println(phonebook("Jim"))
  println(phonebook("Kim")) // returns -1

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  // functions on maps
  // map flatMap filter
  println(phonebook.map{case (k,v) => (k.toLowerCase -> v)})

  // filterKeys
  println(phonebook.view.filterKeys(x => x.startsWith("J")).toMap)

  // mapValues
  println(phonebook.view.mapValues(number => number * 10).toMap)

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Jim", 555)).toMap)

  // group by functions
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  val longText = "identity"
  println(longText.groupBy(identity).map{case (k,v) => (k, v.length)})


  // questions
  val phoneBook2 = Map(
    ("Jim", 555),
    ("Jim", 700)
  )
  println(phoneBook2)


}

object SocialNetworkApp extends App {
  // social networks on maps
  type Person = String
  type Network = Map[Person, Set[Person]]
  // - add a person to the network
  // - remove
  // - friend (mutual)
  // - unfriend
  // some stats:
  // - number of friends of a person
  // - person with most friends
  // - how many people have NO friends
  // - count - if there is a social connection between two people

  def add(network: Network, person: Person): Network = {
    network + (person -> Set.empty[Person])
  }

  def remove(network: Network, person: Person): Network = {
    def removeAux(friends: Set[Person], networkAcc: Map[String, Set[String]]): Network = {
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    }
    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  def friend(network: Network, personA: Person, personB: Person): Network = {
    val friendsA = network(personA)
    val friendsB = network(personB)
    network + (personA -> (friendsA + personB)) + (personB -> (friendsB + personA))
  }

  def unfriend(network: Network, personA: Person, personB: Person): Network = {
    val friendsA = network(personA)
    val friendsB = network(personB)
    network + (personA -> (friendsA - personB)) + (personB -> (friendsB - personA))
  }

  val empty: Network = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  // Jim, Bob, Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")
  println(testNet)

  def nFriends(network: Network, person: Person): Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Network): Person = {
    network.maxBy(pair => pair._2.size)._1
  }

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Network): Int = {
    network.count(_._2.isEmpty)
  }
  println(nPeopleWithNoFriends(testNet))

  def socialConnections(network: Network, personA: Person, personB: Person): Boolean = {
    @tailrec
    def bfs(target: Person, consideredPeople: Set[Person], discoveredPeople: Set[Person]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(personB, Set(), network(personA) + personA)
  }

  println(socialConnections(testNet, "Mary", "Jim"))
  println(socialConnections(network, "Mary", "Bob"))
}