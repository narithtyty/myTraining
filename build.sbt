name := "MyApi"

version := "0.1"

scalaVersion := "2.13.0"

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.7.0"  /// mongo scala driver
libraryDependencies += "com.typesafe" % "config" % "1.3.1"            /// file config
libraryDependencies += "io.circe" %% "circe-generic" % "0.12.0-RC3"
libraryDependencies += "com.typesafe.akka" %% "akka-http"   % "10.1.9"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.25"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.23"
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.9"   // lib return json
libraryDependencies += "io.spray" %% "spray-json" % "1.3.2"




