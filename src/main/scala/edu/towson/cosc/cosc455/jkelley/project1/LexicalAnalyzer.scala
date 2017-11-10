package edu.towson.cosc.cosc455.jkelley.project1

trait LexicalAnalyzer {
  def addChar() : Unit
  def getChar() : Char
  def getNextToken() : Unit
  def lookup(s : String) : Boolean

}
