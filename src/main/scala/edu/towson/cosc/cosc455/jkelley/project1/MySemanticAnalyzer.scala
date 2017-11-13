package edu.towson.cosc.cosc455.jkelley.project1

class MySemanticAnalyzer {

  val parseTree = Compiler.Parser.parser

  def print() : Unit = {
    if(parseTree.toList.head.equals("\\BEGIN"))
      println("<HTML>")
  }

  //val parseTree = Compiler.Parser.parser.toList

  //def print() : Unit = println(parseTree.head)




}
