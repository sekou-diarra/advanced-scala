package languagefeature.scalaZ

import scalaz._, Scalaz._

class Disjunctions {

  def queryNextNumber : Either[Exception, Long] = {
    val source = Math.round(Math.random() * 100)
    if (source <=100) Right(source)
    else Left(new Exception("The generated number is too big"))
  }

  def queryNextNumberSclaz: Exception \/ Long = {
    val source = Math.round(Math.random * 100)
    if (source <=100) \/- (source)
    else -\/ (new Exception("The generated number is too big;!"))
  }
  

}

class ReplacingTry{
  def queryNextNumber: Throwable \/ Long = \/.fromTryCatchNonFatal{
    val source = Math.round(Math.random() * 100)
    if (source <= 60) source
    else throw new Exception("The generated number is too big")
  }
}

class GenerationException(number:Long, message:String) extends Exception  {

  implicit val getNotNothing = NotNothing.isNotNothing[GenerationException]

  def queryNextNumber: GenerationException \/ Long = \/.fromTryCatchThrowable{
    val source = Math.round(Math.random() * 100)
    if (source <= 60) source
    else throw new GenerationException(source,"The generated number is too big")
  }
}





