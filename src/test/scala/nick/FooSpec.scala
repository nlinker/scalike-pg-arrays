package nick

import org.scalatest._
import scalikejdbc._
import scalikejdbc.scalatest.AutoRollback

// TODO fix the tests

class FooSpec extends fixture.FlatSpec with Matchers with AutoRollback {
  Class.forName("org.postgresql.Driver")
  ConnectionPool.singleton("jdbc:postgresql://localhost:5432/test",
                           "postgres", "postgres")

  behavior of "Foo"

  it should "find by primary keys" in { implicit session =>
    val maybeFound = Foo.find(1L)
    maybeFound.isDefined should be(true)
  }
  it should "find by where clauses" in { implicit session =>
    val maybeFound = Foo.findBy(sqls"id = ${1L}")
    maybeFound.isDefined should be(true)
  }
  it should "find all records" in { implicit session =>
    val allResults = Foo.findAll()
    allResults.size should be >(0)
  }
  it should "count all records" in { implicit session =>
    val count = Foo.countAll()
    count should be >(0L)
  }
  it should "find all by where clauses" in { implicit session =>
    val results = Foo.findAllBy(sqls"id = ${1L}")
    results.size should be >(0)
  }
  it should "count by where clauses" in { implicit session =>
    val count = Foo.countBy(sqls"id = ${1L}")
    count should be >(0L)
  }
  it should "create new record" in { implicit session =>
    val created = Foo.create(name = "MyString",
                             barz = List[Int](),
                             clrz = List[Color]())
    created should not be null
  }
  it should "save a record" in { implicit session =>
    val entity = Foo.findAll().head
    // TODO modify something
    val modified = entity
    val updated = Foo.save(modified)
    updated should not equal(entity)
  }
  it should "destroy a record" in { implicit session =>
    val entity = Foo.findAll().head
    Foo.destroy(entity)
    val shouldBeNone = Foo.find(1L)
    shouldBeNone.isDefined should be(false)
  }
  it should "perform batch insert" in { implicit session =>
    val entities = Foo.findAll()
    entities.foreach(e => Foo.destroy(e))
    val batchInserted = Foo.batchInsert(entities)
    batchInserted.size should be >(0)
  }
}
