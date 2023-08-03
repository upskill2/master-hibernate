create table IF NOT EXISTS person
(
    id int auto_increment primary key,
    name varchar(255) ,
    location varchar(255) ,
    birth_date DATETIME
);

INSERT INTO person
(
name,
location,
birth_date)
VALUES
(
'Taras',
'Kyiv',
sysdate())
;

INSERT INTO person
(
name,
location,
birth_date)
VALUES
(
'Peter',
'Odesa',
sysdate())
;

INSERT INTO person
(
name,
location,
birth_date)
VALUES
(
'Oleg',
'See',
sysdate())
;