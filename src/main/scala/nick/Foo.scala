package nick

import scalikejdbc._


case class Foo(id: Long,
               name: String,
               barz: List[Int])

object Foo extends SQLSyntaxSupport[Foo] {

  override val schemaName = Some("public")

  override val tableName = "foos"

  override val columns = Seq("id", "name", "barz")

  def apply(f: SyntaxProvider[Foo])(rs: WrappedResultSet): Foo = apply(f.resultName)(rs)
  def apply(f: ResultName[Foo])(rs: WrappedResultSet): Foo = {
    new Foo(
      id = rs.get(f.id),
      name = rs.get(f.name),
      barz = rs.array(f.barz).getArray()
          .asInstanceOf[Array[Integer]]
          .map(_.intValue())
          .toList
    )
  }
  val f = Foo.syntax("f")

  override val autoSession = AutoSession

  def find(id: Long)(implicit session: DBSession): Option[Foo] = {
    sql"""SELECT ${f.result.*} FROM ${Foo as f} WHERE ${f.id} = ${id}"""
        .map(Foo(f.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession): List[Foo] = {
    sql"""SELECT ${f.result.*} FROM ${Foo as f}"""
        .map(Foo(f.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession): Long = {
    sql"""SELECT count(1) FROM ${Foo.table}"""
        .map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession): Option[Foo] = {
    sql"""SELECT ${f.result.*} FROM ${Foo as f} WHERE ${where}"""
        .map(Foo(f.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession): List[Foo] = {
    sql"""SELECT ${f.result.*} FROM ${Foo as f} WHERE ${where}"""
        .map(Foo(f.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession): Long = {
    sql"""SELECT count(1) FROM ${Foo as f} WHERE ${where}"""
        .map(_.long(1)).single.apply().get
  }

  def create(name: String,
             barz: List[Int])(implicit session: DBSession): Foo = {
    val generatedKey =
      sql"""
      INSERT INTO ${Foo.table} (
        ${column.name}
      , ${column.barz}
      ) VALUES (
        ${name}
      , ARRAY[${barz}]
      )
      """.updateAndReturnGeneratedKey.apply()
    Foo(
      id = generatedKey,
      name = name,
      barz = barz
    )
  }

  def batchInsert(entities: Seq[Foo])(implicit session: DBSession): Seq[Int] = {
    val params: Seq[Seq[(Symbol, Any)]] = entities.map(
      entity =>
        Seq(
          'name -> entity.name,
          'barz -> entity.barz))
    SQL(
      """insert into foos(
        name,
        barz
      ) values (
        {name},
        ARRAY[{barz}]
      )""").batchByName(params: _*).apply()
  }

  def save(entity: Foo)(implicit session: DBSession): Foo = {
    sql"""
      UPDATE
        ${Foo.table}
      SET
        ${column.id} = ${entity.id},
        ${column.name} = ${entity.name},
        ${column.barz} = ARRAY[${entity.barz}]
      WHERE
        ${column.id} = ${entity.id}
      """.update.apply()
    entity
  }

  def destroy(entity: Foo)(implicit session: DBSession): Unit = {
    sql"""DELETE FROM ${Foo.table} WHERE ${column.id} = ${entity.id}"""
        .update.apply()
  }

}
