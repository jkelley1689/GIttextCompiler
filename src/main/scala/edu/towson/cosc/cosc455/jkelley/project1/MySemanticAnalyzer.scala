package edu.towson.cosc.cosc455.jkelley.project1

import scala.collection.mutable.ListBuffer

class MySemanticAnalyzer {

  val parseTree = Compiler.Parser.parser
  var HTML = ListBuffer[String]()

  /*def convertToHtml(parseTree : List[String]) : ListBuffer[String] ={
    parseTree.head match{
      case CONSTANTS.DOCB => HTML += "<HTML>"
        convertToHtml(parseTree.tail)
      case CONSTANTS.TITLEB => HTML += "<head>"
        HTML += "<title>"
        HTML += parseTree.tail.head
        HTML += "</title>"
        HTML += "</head>"
        convertToHtml(parseTree.tail)
      case CONSTANTS.PARB => HTML += "<p>"
        convertToHtml(parseTree.tail)
      case CONSTANTS.PARE => HTML += "<p>"
        convertToHtml(parseTree.tail)
      case CONSTANTS.HASH => HTML += "<h1>"
        HTML += parseTree.tail.head
        HTML += "</h1>"
        convertToHtml(parseTree.tail)
      //case CONSTANTS.validText => HTML += parseTree.head
        //convertToHtml(parseTree.tail)
      case _ => return HTML
    }
  }
  def print() : Unit = {
    println(HTML.toList.mkString("\n"))
  }*/


  //val parseTree = Compiler.Parser.parser.toList

  def print() : Unit = println(HTML.toList.mkString(""))

  def convertToHtml(parseTree : List[String]) : Unit = {
    if(parseTree.head.equalsIgnoreCase(CONSTANTS.DOCB)){
      HTML += "<HTML>\n"
      convertToHtml(parseTree.tail)
    }
    else if(parseTree.head.equalsIgnoreCase(CONSTANTS.TITLEB)){
      HTML += ("<head>\n", "<title>",parseTree.tail.head,"</title>\n","</head>")
      convertToHtml(parseTree.tail)
    }
    else if(parseTree.head.equalsIgnoreCase(CONSTANTS.PARB)){
      HTML += "<p>"
      convertToHtml(parseTree.tail)
    }
    else if(parseTree.head.equalsIgnoreCase(CONSTANTS.PARE)){
      HTML += "</p>"
      convertToHtml(parseTree.tail)
    }
    else if(parseTree.head.equalsIgnoreCase(CONSTANTS.HASH)){
      HTML += ("<h1>",parseTree.tail.head,"</h1>")
      convertToHtml(parseTree.tail)
    }
    else if(CONSTANTS.validText.contains(parseTree.head)) {
      HTML += parseTree.head
      convertToHtml(parseTree.tail)
    }
    else if(CONSTANTS.whiteSpace.contains(parseTree.head)){
      convertToHtml(parseTree.tail)
    }
    else print()

  }




}
