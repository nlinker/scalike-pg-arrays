DROP TABLE foos;

CREATE TABLE foos (
    id bigserial NOT NULL PRIMARY KEY,
    name text,
    barz integer[]
);

INSERT INTO foos VALUES (1, 'aaa', '{1,2,3}');
INSERT INTO foos VALUES (2, 'bbb', '{4,5,6}');
INSERT INTO foos VALUES (3, 'ccc', '{2,4,6}');

