package edu.towson.cosc.cosc455.jkelley.project1

import scala.collection.mutable.ListBuffer

class MySyntaxAnalyzer extends SyntaxAnalyzer{
  //var nextToken : String = Compiler.Scanner.getNextToken().toString

  var parser = ListBuffer[String]()

  override def gittex(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCB)){
      parser += Compiler.currentToken
      Compiler.Scanner.getNextToken()
    }
    else {
      println("Error, \\BEGIN expected")
      System.exit(1)
    }
    variableDefine()
    title()
    body()
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCE))
      System.exit(1)
  }

  override def paragraph(): Unit = {
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARB)) {
      //add to parse tree / stack
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Error, \\PARB expected")
    }
    variableDefine()
    innerText()
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARE)){
      //add to parse tree / stack
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Error, \\PARE expected")
    }


  }

  override def innerItem(): Unit = ???

  override def innerText(): Unit = ???

  override def link(): Unit = ???

  override def italics(): Unit = ???

  override def body(): Unit = ???

  override def bold(): Unit = {
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)){
      //add to parse tree / stack
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Error, * expected")
    }
    if(CONSTANTS.validText.contains(Compiler.currentToken))
      Compiler.Scanner.getNextToken()
    else
      println("Error, text expected")
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)){
      //add to parse tree / stack
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Error, * expected")
    }
  }

  override def newline(): Unit = ???

  override def title(): Unit = {

    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.TITLEB)){
      parser += Compiler.currentToken
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Error, \\TITLE[ expected")
    }
    if(CONSTANTS.validText.contains(Compiler.currentToken.toCharArray))
      Compiler.Scanner.getNextToken()
    else
      println("Error, text expected")
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BRACKETE)){
      //add to parse tree / stack
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Error, ] expected")
    }
  }

  override def variableDefine(): Unit ={
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.VARDEF)){
      parser += Compiler.currentToken
      Compiler.Scanner.getNextToken()
    }
    else return
    if(CONSTANTS.validText.contains(Compiler.currentToken)){
      //add to parse tree / stack
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Error, text expected")
    }
    if(Compiler.currentToken.equalsIgnoreCase("=")){
      //add to parse tree / stack
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Error, = expected")
    }
    if(CONSTANTS.validText.contains(Compiler.currentToken)){
      //add to parse tree / stack
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Error, text expected")
    }
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BRACKETE)){
      //add to parse tree / stack
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Error, ] expected")
    }
    variableDefine()


  }

  override def image(): Unit = ???

  override def variableUse(): Unit = {
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.VARUSE)){
      //add to parse tree / stack
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Error, \\USE expected")
    }
    if(CONSTANTS.validText.contains(Compiler.currentToken))
      Compiler.Scanner.getNextToken()
    else
      println("Error, text expected")
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BRACKETE)){
      //add to parse tree / stack
      Compiler.Scanner.getNextToken()
    }
    else{
      println("Error, ] expected")
    }
  }

  override def heading(): Unit = ???

  override def listItem(): Unit = ???

}
