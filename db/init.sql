DROP TABLE foos;

CREATE TABLE foos (
  id   SERIAL     NOT NULL PRIMARY KEY,
  name TEXT       NOT NULL,
  barz INTEGER [] NOT NULL
);

INSERT INTO foos VALUES (1, 'aaa', '{1,2,3}');
INSERT INTO foos VALUES (2, 'bbb', '{4,5,6}');
INSERT INTO foos VALUES (3, 'ccc', '{2,4,6}');

