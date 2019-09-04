package endpoints

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.Materializer
import models._
import models.repository._
import spray.json.{JsonFormat, RootJsonFormat}
import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol._
import jsonSupport._

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
import requestUser.requestUserEdit

class UserEndpoint(repository: UserRepository)(implicit ec: ExecutionContext, mat: Materializer) {
  val userRoutes = pathPrefix("api" / "users") {
    pathEnd {
      get { // --get ("api/users")
        onComplete(repository.getData()) {
          case Success(value) =>
            //complete(value)
           SuccessResponse(value)
          case Failure(e) =>
            complete(s"Fail with: ${e.getMessage}")
        }
      } // End get ----
    } ~ path(Segment) { id => // get ----- ("api/users/1")
      get {
        onComplete(repository.findById(id.toInt)) {
          case Success(Some(user)) =>
            complete(user)
          case Success(None) => complete("cannot find")
          case Failure(e) =>
            complete(s"Fail with: ${e.getMessage}")
        }
      } ~ post {
          entity(as[requestUserEdit]){
             userEdit =>
               val success1=repository.update(id.toInt,userEdit)
               complete(success1)
          }
      } ~ delete { // post ------------("api/users/1")
          repository.removeData(id.toInt)
          complete("Delete Success")
      }
    } ~ (post & entity(as[User])) { user =>
      onComplete(repository.save(user)) {
        case Success(v) => complete(v.toString)
        case Failure(e) => complete(s"Fail with: ${e.getMessage}")
      }
    }
  }
  }

