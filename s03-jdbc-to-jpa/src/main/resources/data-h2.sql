create table IF NOT EXISTS person
(
    id int auto_increment primary key,
    name varchar(255) ,
    location varchar(255) ,
    birth_date timestamp
);

INSERT INTO person
(`id`,
`name`,
`location`
)
VALUES
(1,
'Test_User',
'Kyiv'
);

INSERT INTO person
(`id`,
`name`,
`location`
)
VALUES
(2,
'Test_User',
'Kyiv'
);