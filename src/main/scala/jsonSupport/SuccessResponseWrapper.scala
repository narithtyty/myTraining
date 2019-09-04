package jsonSupport

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol.jsonFormat1
import spray.json.{JsonFormat, RootJsonFormat}

case class SuccessResponseWrapper[T](data1: T)    /// name data1
object SuccessResponseWrapper extends SprayJsonSupport {
  implicit def successResponseFormat[T: JsonFormat]: RootJsonFormat[SuccessResponseWrapper[T]] = jsonFormat1(SuccessResponseWrapper.apply[T])
}
object SuccessResponse {
  import akka.http.scaladsl.server.Directives._
  def apply[T: JsonFormat](data: T) = complete(SuccessResponseWrapper(data))
}