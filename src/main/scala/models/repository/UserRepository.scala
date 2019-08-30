package models.repository

import models._
import org.bson.conversions.Bson
import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates.{combine, set}
import org.mongodb.scala.bson.annotations.BsonProperty
import org.mongodb.scala.result.UpdateResult
import org.mongodb.scala.result.DeleteResult
import scala.util.{Failure, Success}
import scala.concurrent.{ExecutionContext, Future}
import requestUser.requestUserEdit

/// Execution
class UserRepository(collection: MongoCollection[User])(implicit ec: ExecutionContext) {

  def findById(id: Int): Future[Option[User]] = {
    collection
      .find(Document("_id" -> id))   /// { "_id" : 1 } => Document("_id" -> id)
      .first
      .head
      .map(Option(_))  //.map(u => Some(User(u._id, u.username, u.age)))
  }

  def getData(): Future[Seq[User]] = {
    collection.find().toFuture()    //Future(collection.find()).flatMap(_.toFuture())
  }

  // create User

  def save(user: User): Future[Int] ={
    println(s"User ${user}")
    collection
      .insertOne(user)
      .head
      .map { _ => user._id }
  }

  def update(id: Int , userEdit : requestUserEdit ) = {
    println(s"id ${id}")
    println(s"user ${userEdit}")
    var result = collection
      .updateOne(equal("_id",id)
        , combine(set("username", userEdit.username),
          set("age",userEdit.age)))
      .toFutureOption()  // update
    var result2 =result.map {
      case Some(v)  => println(v.getMatchedCount)
      case None => println("None")
    }
    val meassage ="Update Success"
    meassage
  }

  def removeData(id:Int) = {
    collection.deleteOne(equal("_id",id)).toFutureOption()
  }

//  def testSave(id:Int): Future[Int]= Future {
//    val val1=(id + 1)
//    val1
//  }

  def insert(user: User): Future[Option[Completed]] = collection.insertOne(user).toFutureOption()

}
