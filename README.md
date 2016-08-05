# Scalikejdbc example to use Postgresql ARRAY column

## How to run the example

1. Install sbt (http://www.scala-sbt.org/0.13/docs/Setup.html)
2. Install postgresql
3. Prepare the database `./db/init.sh`
4. Execute `sbt run`

## The expected log

```
destroying all List(Foo(13,aaa,List(1, 2, 3)), Foo(14,bbb,List(2, 3, 4)), Foo(15,ccc,List(3, 4, 5)), Foo(16,eee,List(1, 1, 1, 1, 1, 1)))
18:49:45.313 [run-main-0] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] DELETE FROM public.foos WHERE id = 13; (0 ms)
18:49:45.314 [run-main-0] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] DELETE FROM public.foos WHERE id = 14; (0 ms)
18:49:45.315 [run-main-0] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] DELETE FROM public.foos WHERE id = 15; (0 ms)
18:49:45.317 [run-main-0] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] DELETE FROM public.foos WHERE id = 16; (0 ms)
insert several foos
18:49:45.322 [run-main-0] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] INSERT INTO public.foos ( name , barz ) VALUES ( 'aaa' , ARRAY[1, 2, 3] ); (1 ms)
18:49:45.323 [run-main-0] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] INSERT INTO public.foos ( name , barz ) VALUES ( 'bbb' , ARRAY[2, 3, 4] ); (1 ms)
18:49:45.325 [run-main-0] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] INSERT INTO public.foos ( name , barz ) VALUES ( 'ccc' , ARRAY[3, 4, 5] ); (0 ms)
18:49:45.326 [run-main-0] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] INSERT INTO public.foos ( name , barz ) VALUES ( 'ddd' , ARRAY[4, 5, 6] ); (1 ms)
update the last one
18:49:45.328 [run-main-0] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] UPDATE public.foos SET id = 20, name = 'eee', barz = ARRAY[1, 1, 1, 1, 1, 1] WHERE id = 20; (0 ms)
18:49:45.329 [run-main-0] DEBUG scalikejdbc.StatementExecutor$$anon$1 - [SQL Execution] SELECT f.id as i_on_f, f.name as n_on_f, f.barz as b_on_f FROM public.foos f; (1 ms)
final table contents = List(Foo(17,aaa,List(1, 2, 3)), Foo(18,bbb,List(2, 3, 4)), Foo(19,ccc,List(3, 4, 5)), Foo(20,eee,List(1, 1, 1, 1, 1, 1)))
```
