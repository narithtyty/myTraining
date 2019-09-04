import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
object TestJson extends App with Directives with SprayJsonSupport {
  case class Address(street: String, city: String)
  case class Person(name: String, address: Address)

  // create the formats and provide them implicitly
  implicit val addressFormat = jsonFormat2(Address)
  implicit val personFormat = jsonFormat2(Person)

  // serialize a Person
  val person=Person("Fred", Address("Awesome Street 9", "SuperCity"))
  println(person)
}
