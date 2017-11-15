import scala.collection.mutable.{HashMap,ListBuffer}




var currentToken = "The Simpsons"
var varDefinitions = HashMap[String,String]()

def addVarDef(holdVar : String, holdDec : String) : Unit = {
  varDefinitions += (holdVar -> holdDec)
}
var holdVar = "lastname"
var holdDec = "Simpsons"

addVarDef(holdVar,holdDec)

for ((k,v) <- varDefinitions) printf("%s = %s\n",k,v)

varDefinitions.contains(holdVar)
varDefinitions.get(holdVar)












































