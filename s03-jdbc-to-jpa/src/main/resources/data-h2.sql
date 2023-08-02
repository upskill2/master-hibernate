create table IF NOT EXISTS person
(
    id int auto_increment primary key,
    name varchar(255) ,
    location varchar(255) ,
    birth_date timestamp DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO person
(
`name`,
`location`
)
VALUES
(
'Test_User',
'Kyiv'
);