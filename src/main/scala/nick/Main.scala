package nick

import scalikejdbc._

object Main extends App {

  def run() = {

    GlobalSettings.loggingSQLAndTime = new LoggingSQLAndTimeSettings(
      enabled = true,
      singleLineMode = true,
      logLevel = 'DEBUG
    )

    Class.forName("org.postgresql.Driver")
    val poolSettings = new ConnectionPoolSettings(initialSize = 100, maxSize = 100)
    val url = "jdbc:postgresql://localhost:5432/test"
    val user = "postgres"
    val password = "postgres"

    // create singleton(default) connection pool
    ConnectionPool.singleton(url, user, password, poolSettings)

    DB.localTx { implicit session ⇒
      val foos = Foo.findAll()
      println(s"destroying all $foos")
      foos.foreach(Foo.destroy)
      println(s"insert several foos")
      Foo.create("aaa", List(1,2,3))
      Foo.create("bbb", List(2,3,4))
      Foo.create("ccc", List(3,4,5))
      val theFoo = Foo.create("ddd", List(4,5,6))
      println(s"update the last one")
      Foo.save(theFoo.copy(name = "eee", barz = List(1,1,1,1,1,1)))
      println(s"final table contents = ${Foo.findAll()}")
    }
  }

  run()
}
