name := "AngularJs_Play"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.mongodb" % "mongo-java-driver" % "2.12.4",
  "com.google.inject" % "guice" % "3.0",
  "com.google.inject.extensions" % "guice-assistedinject" % "3.0",
  "com.trinhtv3.fsoft" % "DesignBackEndLearning" % "1.0-SNAPSHOT"
)     

play.Project.playJavaSettings
