CREATE DATABASE shopplace DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE shopplace;

CREATE TABLE user (
	id bigint unsigned auto_increment primary key,
    name varchar(64) not null,
    email varchar(32) not null,
    password varchar(512) not null,
    dateofbirth date not null
);

CREATE TABLE product (
	id bigint unsigned auto_increment primary key,
	name varchar(64) not null,
	price decimal(10, 2) not null,
	registerdate datetime not null
);

SELECT * FROM user;

SELECT * FROM product;