package edu.towson.cosc.cosc455.jkelley.project1

import scala.collection.mutable.{HashMap, ListBuffer}

class MySyntaxAnalyzer extends SyntaxAnalyzer{


  var parser = ListBuffer[String]()
  var varDefinitions = HashMap[String,String]()
  var tempVar = " "
  var tempDec = " "


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

  }

  override def paragraph(): Unit = {
    skipWhiteSpace()
    if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARB))
      addToParser()
    else return{
      println("Error, \\PARB expected")
      System.exit(1)
    }
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
    if(CONSTANTS.innerText.exists(x => x.equalsIgnoreCase(Compiler.currentToken))){
      variableUse()
      heading()
      bold()
      listItem()
      image()
      link()
      innerText()
    }
    else if(!checkForKeyword() && Compiler.currentToken.isInstanceOf[String]){
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

  override def body(): Unit = { //i need help with this
    if(CONSTANTS.innerText.exists(x => x.equalsIgnoreCase(Compiler.currentToken))){
      innerText()
      body()
    }
    else if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARB))
      paragraph()
    while(!Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCE)) {
      Compiler.Scanner.getNextToken()
      body()
    }
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
    if(Compiler.currentToken.isInstanceOf[String]) {
      tempVar = Compiler.currentToken     //sets the currentToken to the variable that will be the key for the HashMap
      addToParser()
    }
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
    if(Compiler.currentToken.isInstanceOf[String]) {
      tempDec = Compiler.currentToken  //sets the currentToken to the variable that will be the value for the above key
      addToParser()
    }
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

    addVarDef(tempVar,tempDec) //maps the definition to its variable
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
    else return
    if(Compiler.currentToken.isInstanceOf[String])
      if(!lookUpVar().equals("Error"))
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
  //function creates a heading and
  override def heading(): Unit = {
    if (Compiler.currentToken.contains(CONSTANTS.HASH))
      addToParser()
    else return
    if(Compiler.currentToken.isInstanceOf[String])
      addToParser()
    else{
      println("Error, text expected")
      System.exit(1)
    }
  }
  //recursive function for list items
  //add to a list and goes into innerItem() until the currentToken does
  //not match List, returns to innerText()
  override def listItem(): Unit = {
    if(Compiler.currentToken.contains(CONSTANTS.LIST))
      addToParser()
    else return
    innerItem()
    listItem()
  }

  //function to skip white space
  def skipWhiteSpace(): Unit = {
    while(CONSTANTS.whiteSpace.contains(Compiler.currentToken))
      Compiler.Scanner.getNextToken()
  }
  //function to add the token to the parser once its passes the grammer rules
  def addToParser() : Unit = {
    parser += Compiler.currentToken
    Compiler.Scanner.getNextToken()
  }

  def checkForKeyword() : Boolean = CONSTANTS.keywords.contains(Compiler.currentToken)

  //function to check if a variable has been previously defined
  //takes in the current token as the hashmap key and checks if the key exists
  //if not "error" is returned
  def lookUpVar() : String = varDefinitions.getOrElseUpdate(Compiler.currentToken + " " ,"Error")

  //function that add variables and their definitions to the hashmap
  def addVarDef(holdVar : String, holdDec : String) : Unit = varDefinitions += (tempVar -> tempDec)

}
