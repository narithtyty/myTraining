package mongo

import com.typesafe.config.ConfigFactory
import models._
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala._

object mongo{
    lazy val config = ConfigFactory.load("application");
    lazy val mongoClient: MongoClient = MongoClient(config.getString("mongo.uri"))
    lazy val codecList = CodecRegistries.fromRegistries(fromProviders(classOf[User]), DEFAULT_CODEC_REGISTRY)
    lazy val database: MongoDatabase = mongoClient.getDatabase(config.getString("mongo.database"))
      .withCodecRegistry(codecList)
    lazy val userCollection: MongoCollection[User] = database.getCollection[User]("users1")
}
