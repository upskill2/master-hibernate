create table IF NOT EXISTS person
(
    id int auto_increment primary key,
    name varchar(255) ,
    location varchar(255) ,
    birth_date timestamp
);

INSERT INTO person
(
name,
location,
birth_date)
VALUES
(
'OLD',
'OLD',
sysdate())
;