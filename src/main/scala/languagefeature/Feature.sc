import simulacrum.typeclass

import scala.reflect.ClassTag
//def sayHello(implicit name: String): String = s"Hello $name"
implicit val name: String = "Joe"


object Person {
  //  implicit val person: Person = new Person("User")
  implicit val maybePerson: Option[Person] = Some(new Person("User"))
  implicit def stringToPerson(str: String): Person = Person(str)
}

case class Person(name: String)


// shorcut
implicit class StringToPerson(str: String) {
  def greet: String = s"Hello! I'm $str"
}

name.greet

//Type erasure and type tags
def createArray[T: ClassTag](length: Int, element: T)(implicit tag: ClassTag[T]) = new Array[T](length)
createArray(5, 1.0)
//
//def sayHello(implicit person: Option[Person]) :String = s"Hello ${person.name}"
//println(sayHello)



trait InfoPrinter[T] {
  def toInfo(value: T): String
}



object DefaultInfoPrinters {
  implicit val stringPrinter = new InfoPrinter[String] {
    override def toInfo(value: String): String = s"[String] $value"
  }

  implicit val intPrinter = new InfoPrinter[Int] {
    override def toInfo(value: Int): String = s"[Int] $value"
  }
}

case class User(name:String, age:Int)
object User{
  implicit val  userPrinter= new InfoPrinter[User] {
 override def toInfo(value:User): String =
 s"[User] (${value.name}, ${value.age})"
  }
}

object PrintInfoSyntax {
  implicit class PrintInfoOps[T](value: T) {
    def printInfo(implicit printer: InfoPrinter[T]): Unit = {
      println(printer.toInfo(value))
    }
  }
}

import PrintInfoSyntax._
      val number= 42
number.printInfo
//////////////////////////////////////////////////////



@typeclass
trait InfoPrinterS[T]{
  def toInfo(valu:T): String
}
import InfoPrinterS.ops._
val user = User("Joe", 42)
println(user.toInfo)
