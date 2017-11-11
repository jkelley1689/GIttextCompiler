package edu.towson.cosc.cosc455.jkelley.project1

object Compiler {

  var currentToken : String = " "
  var fileContents : String = " "

  val Scanner = new MyLexicalAnalyzer
  val Parser = new MySyntaxAnalyzer
  val SemanticAnalyzer = new MySemanticAnalyzer

  def main(args: Array[String]): Unit = {

    checkFile(args)
    readFile(args(1))
    Scanner.getNextToken()
    Parser.gittex()
    SemanticAnalyzer.print()


  }

  def readFile(file : String) = {
    val source = scala.io.Source.fromFile("Test1.txt")
    fileContents = try source.mkString finally source.close()
  }

  def checkFile(args : Array[String]) = {
    if (args.length != 1) {
      println("USAGE ERROR: wrong number of args fool!")
      System.exit(1)
    }
    else if (! args(0).endsWith(".mkd")) {
      println("USAGE ERROR: wrong extension fool!")
      System.exit(1)
    }
  }
}


