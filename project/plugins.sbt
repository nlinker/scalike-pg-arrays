logLevel := Level.Warn

libraryDependencies += "postgresql" % "postgresql" % "9.1-901-1.jdbc4"

addSbtPlugin("org.scalikejdbc" %% "scalikejdbc-mapper-generator" % "2.4.2")

