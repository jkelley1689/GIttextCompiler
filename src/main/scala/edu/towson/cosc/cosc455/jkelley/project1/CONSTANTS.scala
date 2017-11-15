package edu.towson.cosc.cosc455.jkelley.project1

object CONSTANTS {
  val DOCB : String = 	"\\BEGIN"
  val DOCE : String = 	"\\END"
  val TITLEB : String = "\\TITLE["
  val BRACKETE : String = "]"
  val PARB : String = "\\PARAB"
  val PARE : String = "\\PARAE"
  val BOLD : String = "*"
  val LIST : String = "+"
  val NEWLINE : String = "\n"
  val LINKS : String = "["
  val IMAGES : String = "!["
  val HASH : String = "#"
  val VARDEF : String = "\\DEF["
  val VARUSE : String = "\\USE["
  val PARAS : String = "("
  val PARAE : String = ")"
  val EQUAL : String = "="

  val keywords : List[String] = List(DOCB, DOCE, TITLEB, BRACKETE, PARB, PARE, BOLD, LIST, NEWLINE, LINKS, IMAGES, HASH, VARDEF, VARUSE,PARAS,PARAE,EQUAL,"\\")
  val keyStart : List[Char] = List('\\','*','+','[','!','#','\n',']','(',')','=')

  val letters : List[String] = List("a","b","c","d","e","f","g","h","i","j","k","l","m",
    "n","o","p","q","r","s","t","u","v","w","x","y","z")
  val upperCase : List[String] = letters.map(_ .toUpperCase)
  val numbersEtc : List[String] = List("1","2","3","4","5","6","7","8","9","0",
    ",",".","\"",":","?","_","/", "'", "")
  val whiteSpace : List[String] = List(" ", "\t", "\n", "\b","\f","\r","\\")
  val validText : List[String] = whiteSpace ::: letters ::: numbersEtc ::: upperCase

  val innerText : List[String] = List(HASH,BOLD,LINKS,LIST,IMAGES,VARUSE)

}
