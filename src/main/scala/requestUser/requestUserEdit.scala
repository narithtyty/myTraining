package requestUser

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

case class requestUserEdit(username: String, age: Int)
object requestUserEdit  extends SprayJsonSupport{
  implicit val requestUserEditFormat: RootJsonFormat[requestUserEdit] = jsonFormat2(requestUserEdit.apply)
}

