import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol._
object TestJson extends App{
  case class Address(street: String, city: String)
  case class Person(name: String, address: Address)

  // create the formats and provide them implicitly
  implicit val addressFormat = jsonFormat2(Address)
  implicit val personFormat = jsonFormat2(Person)

  // serialize a Person
  val person=Person("Fred", Address("Awesome Street 9", "SuperCity"))
  println(person)
}
