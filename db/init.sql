DROP TABLE IF EXISTS foos;
DROP TYPE IF EXISTS color;

CREATE TYPE color AS ENUM ('Red', 'Green', 'Blue');

CREATE TABLE foos (
  id   SERIAL     NOT NULL PRIMARY KEY,
  name TEXT       NOT NULL,
  barz INTEGER [] NOT NULL,
  clrz color []   NOT NULL
);

INSERT INTO foos VALUES (1, 'aaa', '{1,2,3}', ARRAY ['Red', 'Red'] :: color []);
INSERT INTO foos VALUES (2, 'bbb', '{4,5,6}', ARRAY ['Red', 'Green'] :: color []);
INSERT INTO foos VALUES (3, 'ccc', '{2,4,6}', ARRAY ['Red', 'Blue'] :: color []);

