name := "scalike-pg-arrays"

version := "1.0"

scalaVersion := "2.11.8"

scalikejdbcSettings

lazy val scalikejdbcCore = "org.scalikejdbc" %% "scalikejdbc" % "2.4.2"

lazy val scalatest = "org.scalatest" %% "scalatest" % "2.2.6"

lazy val scalikejdbcTest = "org.scalikejdbc" %% "scalikejdbc-test" % "2.4.2"   % "test"

lazy val postgres = "org.postgresql" % "postgresql" % "9.3-1103-jdbc41"

lazy val logback = "ch.qos.logback"  %  "logback-classic"    % "1.1.7"

libraryDependencies ++= Seq(
  postgres,
  logback,
  scalatest,
  scalikejdbcCore,
  scalikejdbcTest
)

