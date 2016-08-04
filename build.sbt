name := "scalike-pg-arrays"

version := "1.0"

scalaVersion := "2.11.8"

scalikejdbcSettings

// libraryDependencies ++= Seq(
//   "org.postgresql" % "postgresql" % "9.3-1103-jdbc41"
//   "org.scalikejdbc" %% "scalikejdbc"        % "2.4.2",
//   "org.scalikejdbc" %% "scalikejdbc-test"   % "2.4.2"   % "test",
//   "ch.qos.logback"  %  "logback-classic"    % "1.1.7")

libraryDependencies += "org.postgresql" % "postgresql" % "9.3-1103-jdbc41"

libraryDependencies += "org.scalikejdbc" %% "scalikejdbc"        % "2.4.2"

libraryDependencies += "org.scalikejdbc" %% "scalikejdbc-test"   % "2.4.2"   % "test"

libraryDependencies += "ch.qos.logback"  %  "logback-classic"    % "1.1.7"
