package nick

import scalikejdbc._

object Main extends App {

  def run() = {
    Class.forName("org.postgresql.Driver")
    DB.localTx { implicit session ⇒
      val foos = Foo.findAll()
      println(foos)
    }
  }

  run()
}
