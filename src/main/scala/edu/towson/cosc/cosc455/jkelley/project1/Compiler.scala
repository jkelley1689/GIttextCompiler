package edu.towson.cosc.cosc455.jkelley.project1
import java.awt.Desktop
import java.io.{File, IOException}

object Compiler {

  var currentToken : String = " "
  var fileContents : String = " "
  var fileName : String = " "

  val Scanner = new MyLexicalAnalyzer
  val Parser = new MySyntaxAnalyzer
  val SemanticAnalyzer = new MySemanticAnalyzer

  def main(args: Array[String]): Unit = {

    checkFile(args)
    readFile(args(0))
    fileName = mkFileName(args(0))
    Scanner.getNextToken()
    Parser.gittex()
    SemanticAnalyzer.convert



  }

  def readFile(file : String) = {
    val source = scala.io.Source.fromFile(file)
    fileContents = try source.mkString finally source.close()
  }

  def checkFile(args : Array[String]) = {
    if (args.length != 1) {
      println("USAGE ERROR: wrong number of args fool!")
      System.exit(1)
    }
    else if (! args(0).endsWith(".gtx")) {
      println("USAGE ERROR: wrong extension fool!")
      System.exit(1)
    }
  }

  //changes the file ext from .gtx to .html
  def mkFileName(fullPath : String): String = {
    fullPath.split('.').init ++ Seq("html") mkString "."
  }
}


