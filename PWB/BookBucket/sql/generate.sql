CREATE DATABASE bookbucket DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE bookbucket;

CREATE TABLE user (
	id bigint unsigned primary key auto_increment,
    firstname varchar(32) not null,
    lastname varchar(32) not null,
    dateofbirth datetime not null,
    email varchar(64) not null,
    password varchar(64) not null
);

CREATE TABLE category (
	id bigint unsigned primary key auto_increment,
    name varchar(64) not null,
    id_user bigint unsigned not null,
    constraint foreign key(id_user) references user(id)
);

CREATE TABLE book (
	id bigint unsigned primary key auto_increment,
    name varchar(64) not null,
    id_category bigint unsigned not null,
    constraint foreign key(id_category) references category(id)
);