name := "AngularJs_Play"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.mongodb" % "mongo-java-driver" % "2.12.4",
  "com.google.inject" % "guice" % "4.0-beta"
)     

play.Project.playJavaSettings
