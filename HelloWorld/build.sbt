name := "helloworld"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache, "mysql" % "mysql-connector-java" % "5.1.5"
)     

play.Project.playJavaSettings
