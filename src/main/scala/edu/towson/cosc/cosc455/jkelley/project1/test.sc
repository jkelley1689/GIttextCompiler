import scala.collection.mutable.ListBuffer

val letters : List[String] = List("a","b","c","d","e","f","g","h","i","j","k","l","m",
  "n","o","p","q","r","s","t","u","v","w","x","y","z")
val upperCase : List[String] = letters.map(_ .toUpperCase)
val numbersEtc : List[String] = List("1","2","3","4","5","6","7","8","9","0",
  ",",".","\"",":","?","_","/", "'", "")
val whiteSpace : List[String] = List(" ", "\t", "\n", "\b","\f","\r")
val validText : List[String] = whiteSpace ::: letters ::: numbersEtc ::: upperCase


var currentToken = "The Simpsons"

def doSomething() : Unit = {
  println("Hello")
}
doSomething().##
doSomething().getClass
doSomething().isInstanceOf[Unit]


var parser = ListBuffer[String]("hello","world","and","its","people")

println(parser)







































