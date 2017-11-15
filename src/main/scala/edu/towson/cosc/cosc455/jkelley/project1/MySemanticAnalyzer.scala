package edu.towson.cosc.cosc455.jkelley.project1

import java.awt.Desktop
import java.io.{BufferedWriter, File, FileWriter, IOException}

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap

class MySemanticAnalyzer {

  val parseTree = Compiler.Parser.parser
  var HTML = ListBuffer[String]()
  var variables = ListBuffer[String]()
  var definitions = Compiler.Parser.varDefinitions

  //function to convert the created parseTree, into HTML
  def convert() : Unit = {
    convertToHtml(parseTree.toList)
    writeToFile(Compiler.fileName)
    openHTMLFileInBrowser(Compiler.fileName)
  }



  def print() : Unit = println(HTML.toList.mkString(""))

  //function takes in the parseTree, converts to a List() from a ListBuffer() and moves one by one through the
  //list changing Keyword tokens from Gittext to HTML and adding them to the HTML ListBuffer()
  def convertToHtml(parseTree : List[String]) : Unit = {
    if(parseTree.head.equalsIgnoreCase(CONSTANTS.DOCB)){
      HTML += "<html>\n"
      convertToHtml(parseTree.tail)
    }
    else if(parseTree.head.equalsIgnoreCase(CONSTANTS.DOCE)){
      HTML += "</html>"
      print()
    }
    else if(parseTree.head.equalsIgnoreCase(CONSTANTS.TITLEB)){
      HTML += ("<head>\n" + "\t<title>" + parseTree.tail.head + "</title>\n" + "</head>\n")
      convertToHtml(parseTree.tail.tail.tail)
    }
    else if(parseTree.head.equalsIgnoreCase(CONSTANTS.VARDEF)){ //if a variable is defined its sets the def to 'variable'
      variables += parseTree.tail.head                            //to use as a key for the hashmap
      convertToHtml(parseTree.tail.tail.tail.tail.tail)
    }
    else if(parseTree.head.equalsIgnoreCase(CONSTANTS.VARUSE)){//if a variable is used, 'variable' is used as the keyword
      HTML +=  pullVariable(parseTree.tail.head + " ")             //to retrieve the correct definition
      convertToHtml(parseTree.tail.tail.tail)
    }
    else if(parseTree.head.equalsIgnoreCase(CONSTANTS.PARB)){
      HTML += "<p>"
      convertToHtml(parseTree.tail)
    }
    else if(parseTree.head.equalsIgnoreCase(CONSTANTS.PARE)){
      HTML += "</p>\n"
      convertToHtml(parseTree.tail)
    }
    else if(parseTree.head.equalsIgnoreCase(CONSTANTS.HASH)){
      HTML += ("\t<h1>" + parseTree.tail.head + "</h1>\n")
      convertToHtml(parseTree.tail.tail)
    }
    else if(parseTree.head.equalsIgnoreCase(CONSTANTS.LIST)){
      if(parseTree.tail.head.equalsIgnoreCase(CONSTANTS.VARUSE)) {
        HTML += ("\t<li>" + pullVariable(parseTree.tail.tail.head + " ") + "</li>\n")
        convertToHtml(parseTree.tail.tail.tail.tail)
      }
      else{
        HTML += ("\t<li>" + parseTree.tail.head + "</li>\n")
        convertToHtml(parseTree.tail.tail)
      }
    }
    else if(parseTree.head.equalsIgnoreCase(CONSTANTS.LINKS)){
      HTML += ("<a href = " + parseTree.tail.tail.tail.tail.head +  "> " + parseTree.tail.head + " </a> ")
      convertToHtml(parseTree.tail.tail.tail.tail.tail.tail)
    }
    else if(parseTree.head.equalsIgnoreCase(CONSTANTS.IMAGES)){
      HTML += ("<img src = " + parseTree.tail.tail.tail.tail.head + " alt = " + parseTree.tail.head + ">\n")
      convertToHtml(parseTree.tail.tail.tail.tail.tail.tail)
    }
    else if(CONSTANTS.validText.contains(parseTree.head.charAt(0).toString)) {
      HTML += " " + parseTree.head + " "
      convertToHtml(parseTree.tail)
    }
    else if(CONSTANTS.whiteSpace.contains(parseTree.head)){
      convertToHtml(parseTree.tail)
    }

  }

  //retrieves vardefs from the hashmap
  def pullVariable(k : String) : String = {
    var v = " "
    if(variables.contains(k))
      v = definitions.getOrElseUpdate(k," ")
    return v
  }

  def writeToFile(name : String): Unit = {
    val file = new File(name)
    val wr = new BufferedWriter(new FileWriter(file))
    wr.write(HTML.mkString)
    wr.close()
  }
  def openHTMLFileInBrowser(htmlFileStr : String) = {
    val file : File = new File(htmlFileStr.trim)
    println(file.getAbsolutePath)
    if (!file.exists())
      sys.error("File " + htmlFileStr + " does not exist.")

    try {
      Desktop.getDesktop.browse(file.toURI)
    }
    catch {
      case ioe: IOException => sys.error("Failed to open file:  " + htmlFileStr)
      case e: Exception => sys.error("He's dead, Jim!")
    }
  }




}
