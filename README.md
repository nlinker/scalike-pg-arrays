# Scalikejdbc example to use Postgresql ARRAY column

The example shows how to use ARRAY of integers and enums with (wonderful) [scalikejdbc](http://scalikejdbc.org) library. The ready-to-run class is `Main`, the mapping class is `Foo` corresponding to the table `foos` in the database.

## How to run the example

1. Install sbt [SBT documentation][1]
2. Install postgresql
3. Prepare the database `./db/init.sh`
4. Execute `sbt run`
[1]: http://www.scala-sbt.org/0.13/docs/Setup.html

## The essential part

```scala
    case class Foo(
      id: Long,
      name: String,
      barz: List[Int],    // mapped to the column of type 'array of ints'
      clrz: List[Color]   // mapped to the column of type 'array of colors' (color is a users type)
    )

    // usage
    println(s"insert several foos")
    Foo.create("aaa", List(1,2,3), List(Color.Red, Color.Red))
    Foo.create("bbb", List(2,3,4), List(Color.Red, Color.Green))
    Foo.create("ccc", List(3,4,5), List(Color.Red, Color.Blue))
```

## The expected log

```
19:55:38.312 [main] DEBUG scalikejdbc.ConnectionPool$ - Registered singleton connection pool : ConnectionPool(url:jdbc:postgresql://localhost:5432/test, user:postgres)
19:55:38.697 [main] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] SELECT f.id as i_on_f, f.name as n_on_f, f.barz as b_on_f, f.clrz as c_on_f FROM public.foos f; (19 ms)
destroying all List(Foo(1,aaa,List(1, 2, 3),List(Red, Red)), Foo(2,bbb,List(4, 5, 6),List(Red, Green)), Foo(3,ccc,List(2, 4, 6),List(Red, Blue)))
19:55:38.954 [main] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] DELETE FROM public.foos WHERE id = 1; (0 ms)
19:55:38.957 [main] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] DELETE FROM public.foos WHERE id = 2; (1 ms)
19:55:38.967 [main] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] DELETE FROM public.foos WHERE id = 3; (5 ms)
insert several foos
19:55:38.987 [main] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] INSERT INTO public.foos ( name , barz , clrz ) VALUES ( 'aaa' , ARRAY[1, 2, 3] , ARRAY['Red', 'Red']::color[] ); (0 ms)
19:55:38.992 [main] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] INSERT INTO public.foos ( name , barz , clrz ) VALUES ( 'bbb' , ARRAY[2, 3, 4] , ARRAY['Red', 'Green']::color[] ); (1 ms)
19:55:38.995 [main] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] INSERT INTO public.foos ( name , barz , clrz ) VALUES ( 'ccc' , ARRAY[3, 4, 5] , ARRAY['Red', 'Blue']::color[] ); (0 ms)
19:55:38.999 [main] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] INSERT INTO public.foos ( name , barz , clrz ) VALUES ( 'ddd' , ARRAY[4, 5, 6] , ARRAY[]::color[] ); (1 ms)
update the last one
19:55:39.005 [main] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] UPDATE public.foos SET id = 8, name = 'eee', barz = ARRAY[1, 1, 1, 1, 1, 1], clrz = ARRAY['Green', 'Green', 'Green']::color[] WHERE id = 8; (1 ms)
19:55:39.008 [main] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] SELECT f.id as i_on_f, f.name as n_on_f, f.barz as b_on_f, f.clrz as c_on_f FROM public.foos f; (0 ms)
final table contents = List(Foo(5,aaa,List(1, 2, 3),List(Red, Red)), Foo(6,bbb,List(2, 3, 4),List(Red, Green)), Foo(7,ccc,List(3, 4, 5),List(Red, Blue)), Foo(8,eee,List(1, 1, 1, 1, 1, 1),List(Green, Green, Green)))
```
