import com.typesafe.config.ConfigFactory
import models.User
import org.mongodb.scala._


object TestConnection extends App {
  lazy val config = ConfigFactory.load("application");
  //println(config.getString("mongo.uri"))
  lazy val mongoClient: MongoClient = MongoClient(config.getString("mongo.uri"))
  //lazy val codecRegistry = fromRegistries(fromProviders(classOf[User]), DEFAULT_CODEC_REGISTRY)
  lazy val database: MongoDatabase = mongoClient.getDatabase(config.getString("mongo.database"))
  lazy val userCollection: MongoCollection[User] = database.getCollection[User]("users1")
  println(userCollection.find())
  var filter=userCollection.find(Document("_id"->3)).first();
  println(filter)
}

