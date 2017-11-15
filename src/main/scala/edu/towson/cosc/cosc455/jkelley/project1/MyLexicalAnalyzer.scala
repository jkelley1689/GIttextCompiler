package edu.towson.cosc.cosc455.jkelley.project1

import scala.collection.mutable.ListBuffer

class MyLexicalAnalyzer extends LexicalAnalyzer {
  var lexeme = ListBuffer[Char]()
  var c = ' '
  var position : Int = 0

  //adds the current char to the lexeme list to be processed into a string
  override def addChar(): Unit = {
    lexeme += c
  }

  //looks up the token to see if it is a valid keyword
  override def lookup(s : String): Boolean = CONSTANTS.keywords.exists(x => x.equalsIgnoreCase(s))

//This function searches for the first non-blank ' ' char in the string
  //Once it finds a char, it checks if the char is the beginning of a keyword
  //If keyword, enters the processToken state
  //If not a keyword, we know it is text since all text is valid so we enter the processText state
  override def getNextToken(): Unit = {

    getNonBlank()
    if(checkForKeyword())
      processToken()
    else if(checkForText())
      processText()
    else {
      println("System Exit, Lexical Error: " + c + " unknown character")
      System.exit(1)
    }
  }

  //retrieves the current character in the string and pushes the index to the next character in the string
  override def getChar(): Char ={
    if(position < Compiler.fileContents.length) {
      c = Compiler.fileContents.charAt(position)
      position += 1
    }
    c
  }

  //helper function to get the next non blank character
  def getNonBlank(): Unit = while(c == ' ') getChar()

  //helper function to turn the lexeme into a string
  def convertToString() : String = lexeme.mkString("")

  //helper function to check if the current char is a keyword
  def checkForKeyword() : Boolean = CONSTANTS.keyStart.contains(c)

  //helper function to check if the current char is valid text
  def checkForText() : Boolean = CONSTANTS.validText.contains(c.toString)

  //This function is called when a keyword character is found, the token is checked against
  //against legal tokens and if legal set to currentToken and gets the next token
  def processToken() : Unit = {
    if (c == '[' || c == '(') {  //This if statement is used in the case of a link; to skip the while loop
    addChar()
    getChar()
    }
    else
  {
    addChar()
    getChar()
    while (!checkForKeyword() && !CONSTANTS.whiteSpace.contains(c.toString) && c.toString != ".") {
      addChar()
      getChar()
    }
    if (c == '[') { //this is used to make sure the '[' gets added to the end of //TITLE[ //VARDEF[ //VARUSE[
      addChar()
      getChar()
    }
  }

    if(lookup(convertToString())){
      Compiler.currentToken = convertToString()
      lexeme.clear()
    }
    else {
      println("System Exit, Lexical Error: " + lexeme.mkString("") +  " is not a valid token")
      System.exit(1)
    }
  }
  //This function to used to process anything not a keyword into a string
  def processText() : Unit = {
    addChar()
    getChar()
    while(!checkForKeyword() && c != '\t'){
      addChar()
      getChar()
    }
    Compiler.currentToken = convertToString()
    lexeme.clear()
  }



}







