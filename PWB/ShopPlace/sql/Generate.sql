CREATE DATABASE shopplace DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE shopplace;

CREATE TABLE user (
	id bigint auto_increment primary key,
    name varchar(64) not null,
    username varchar(32) not null,
    password varchar(512) not null
);

SELECT * FROM user;