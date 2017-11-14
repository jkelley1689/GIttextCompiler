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

val parseTree = parser.toList
println(parseTree.head)

var HTML = ListBuffer[String]()

def convertToHtml(parseTree : List[String]) : ListBuffer[String] ={
  parseTree.head match{
    case "hello" => HTML += "<HTML>"
      convertToHtml(parseTree.tail)
    case "world" => HTML += "<title>"
      convertToHtml(parseTree.tail)
    case "people" => HTML += "<parb>"
      convertToHtml(parseTree.tail)
    case "and" => HTML += "<pare>"
  }
}

convertToHtml(parseTree)








































