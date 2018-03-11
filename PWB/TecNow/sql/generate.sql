CREATE DATABASE tecnow DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE tecnow;

CREATE TABLE user (
	id bigint unsigned auto_increment primary key,
    name varchar(60) not null,
    email varchar(60) unique not null,
    password varchar(20) not null,
    dateofbirth datetime not null,
    gender enum('male', 'female') not null
);

CREATE TABLE game (
	id bigint unsigned auto_increment primary key,
    name varchar(40) not null,
    category enum('shoot', 'rpg', 'platform', 'sport', 'hack and slash', 'other') not null,
    dateregister datetime not null
);

CREATE TABLE anime (
	id bigint unsigned auto_increment primary key,
    name varchar(40) not null,
    dateregister datetime not null
);