package edu.towson.cosc.cosc455.jkelley.project1

import scala.collection.mutable.ListBuffer

class MySyntaxAnalyzer extends SyntaxAnalyzer{


  var parser = ListBuffer[String]()


  override def gittex(): Unit = {
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCB))
     addToParser()
    else {
      println("Error, \\BEGIN expected")
      System.exit(1)
    }
    variableDefine()
    title()
    variableDefine()
    body()
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCE))
      parser += Compiler.currentToken
      println("Done")
  }

  override def paragraph(): Unit = {
    skipWhiteSpace()
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARB))
      addToParser()
    else return//{
      //println("Error, \\PARB expected")
      //System.exit(1)
    //}
    variableDefine()
    innerText()
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARE))
      addToParser()
    else{
      println("Error, \\PARE expected")
      System.exit(1)
    }


  }

  override def innerItem(): Unit = {
    skipWhiteSpace()
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.VARUSE)){
      variableUse()
      innerItem()
    }
    else if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)){
      bold()
      innerItem()
    }
    else if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LINKS)){
      link()
      innerItem()
    }
    else if(!checkForKeyword() && Compiler.currentToken.isInstanceOf[String]){
      addToParser()
      innerItem()
    }
    else return
  }

  override def innerText(): Unit = {
    skipWhiteSpace()
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.VARUSE)){
      variableUse()
      innerText()
    }
    else if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.HASH)){
      heading()
      innerText()
    }
    else if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)){
      bold()
      innerText()
    }
    else if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LIST)){
      listItem()
      innerText()
    }
    else if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.IMAGES)){
      image()
      innerText()
    }
    else if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LINKS)){
      link()
      innerText()
    }
    else if(!checkForKeyword() && Compiler.currentToken.isInstanceOf[String]) {
      addToParser()
      innerText()
    }
    else return
  }

  override def link(): Unit = {
    skipWhiteSpace()
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LINKS))
      addToParser()
    else return
    if(Compiler.currentToken.isInstanceOf[String])
      addToParser()
    else{
      println("Error, text expected")
      System.exit(1)
      }
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BRACKETE))
      addToParser()
    else{
      println("Error, ] expected")
      System.exit(1)
    }
    if(Compiler.currentToken == "(")
      addToParser()
    else{
      println("Error, ( expected")
      System.exit(1)
    }
    if(Compiler.currentToken.isInstanceOf[String])
      addToParser()
    else{
      println("Error, text expected")
      System.exit(1)
    }
    if(Compiler.currentToken == ")")
      addToParser()
    else{
      println("Error, ) expected")
      System.exit(1)
    }
  }

  override def italics(): Unit = ???

  override def body(): Unit = {
    if(!innerText().isInstanceOf[Unit])
      body()
    else paragraph()
    while(!Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCE))
      body()

  }

  override def bold(): Unit = {
    skipWhiteSpace()

    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD))
      addToParser()
    else return
    if(Compiler.currentToken.isInstanceOf[String])
      addToParser()
    else {
      println("Error, text expected")
      System.exit(1)
    }
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD))
      addToParser()
    else {
      println("Error, * expected")
      System.exit(1)
    }

  }

  override def newline(): Unit = {
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.NEWLINE))
      addToParser()
    else return
  }

  override def title(): Unit = {
    skipWhiteSpace()

    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.TITLEB))
      addToParser()
    else{
      println("Error, \\TITLE[ expected")
      System.exit(1)
    }
    if(Compiler.currentToken.isInstanceOf[String])
     addToParser()
    else {
      println("Error, text expected")
      System.exit(1)
    }
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BRACKETE))
      addToParser()
    else{
      println("Error, ] expected")
      System.exit(1)
    }
  }

  override def variableDefine(): Unit ={
    skipWhiteSpace()

    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.VARDEF))
      addToParser()
    else return
    if(Compiler.currentToken.isInstanceOf[String])
      addToParser()
    else{
      println("Error, text expected")
      System.exit(1)
    }
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.EQUAL))
      addToParser()
    else{
      println("Error, = expected")
      System.exit(1)
    }
    if(Compiler.currentToken.isInstanceOf[String])
      addToParser()
    else{
      println("Error, text expected")
      System.exit(1)
    }
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BRACKETE))
      addToParser()
    else {
      println("Error, ] expected")
      System.exit(1)
    }

    variableDefine()


  }

  override def image(): Unit = {
    skipWhiteSpace()

    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.IMAGES))
      addToParser()
    else return
    if(Compiler.currentToken.isInstanceOf[String])
      addToParser()
    else{
      println("Error, text expected")
      System.exit(1)
    }
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BRACKETE))
      addToParser()
    else{
      println("Error, ] expected")
      System.exit(1)
    }
    if(Compiler.currentToken == "(")
      addToParser()
    else{
      println("Error, ( expected")
      System.exit(1)
    }
    if(Compiler.currentToken.isInstanceOf[String])
      addToParser()
    else
      {
        println("Error, text expected")
        System.exit(1)
      }
    if(Compiler.currentToken == ")")
      addToParser()
    else{
      println("Error, ) expected")
      System.exit(1)
    }
  }

  override def variableUse(): Unit = {
    skipWhiteSpace()

    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.VARUSE))
      addToParser()
    else{
      println("Error, \\USE expected")
      System.exit(1)
    }
    if(Compiler.currentToken.isInstanceOf[String])
      if(lookUpVar())
          addToParser()
      else{
        println("Static semantic error: variable " + Compiler.currentToken.mkString("") + " was not defined")
        System.exit(1)
      }

    else {
      println("Error, text expected")
      System.exit(1)
    }
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BRACKETE))
      addToParser()
    else{
      println("Error, ] expected")
      System.exit(1)
    }

  }

  override def heading(): Unit = {
    if (Compiler.currentToken.contains(CONSTANTS.HASH))
      addToParser()
    else return
    if(Compiler.currentToken.isInstanceOf[String])
      addToParser()
  }

  override def listItem(): Unit = {
    if(Compiler.currentToken.contains(CONSTANTS.LIST))
      addToParser()
    else return
    innerItem()
    listItem()
  }

  def skipWhiteSpace(): Unit = {
    //while(Compiler.currentToken == "\t" || Compiler.currentToken == "\n" || Compiler.currentToken == "\\")
      //Compiler.Scanner.getNextToken()
    while(CONSTANTS.whiteSpace.contains(Compiler.currentToken))
      Compiler.Scanner.getNextToken()
  }

  def addToParser() : Unit = {
    parser += Compiler.currentToken
    Compiler.Scanner.getNextToken()
  }

  def checkForKeyword() : Boolean = CONSTANTS.keywords.contains(Compiler.currentToken)

  def lookUpVar() : Boolean = parser.contains(Compiler.currentToken + " ")

}
