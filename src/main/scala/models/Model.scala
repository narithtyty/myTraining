package models
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import org.mongodb.scala.bson.annotations.BsonProperty
import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol._


case class User(@BsonProperty("_id") _id: Int,
                @BsonProperty("username") username: String,
                @BsonProperty("age") age: Int) {
        require(username != null, "username not informed")
        require(username.nonEmpty, "username cannot be empty")
        require(age > 0, "age cannot be lower than 1")
}
object User extends SprayJsonSupport{
  implicit val userFormat: RootJsonFormat[User] = jsonFormat3(User.apply)
  //implicit val userFormat = jsonFormat3(User.apply)
}



