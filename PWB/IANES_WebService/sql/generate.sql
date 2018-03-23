CREATE DATABASE ianes_ws DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE ianes_ws;

CREATE TABLE user (
	id bigint unsigned auto_increment primary key,
	name varchar(20) not null,
    email varchar(40) not null,
    password varchar(40) not null,
    type enum('admin', 'common')
);

CREATE TABLE patrimony_category (
	id bigint unsigned auto_increment primary key,
    name varchar(30) not null
);

CREATE TABLE patrimony (
	id bigint unsigned auto_increment primary key,
    name varchar(40) not null,
    category bigint unsigned not null,
    user bigint unsigned not null,
    date datetime not null,
    constraint foreign key (category) references patrimony_category(id)
);

CREATE TABLE environment (
	id bigint unsigned auto_increment primary key,
    name varchar(40) not null unique
);

CREATE TABLE patrimony_item (
	id bigint unsigned auto_increment primary key,
    patrimony bigint unsigned not null,
    environment bigint unsigned not null,
    user bigint unsigned not null,
    constraint foreign key (patrimony) references patrimony(id),
    constraint foreign key (environment) references environment(id)
);

CREATE TABLE movement (
	id bigint unsigned auto_increment primary key,
    patrimony_item bigint unsigned not null,
    origin_env bigint unsigned not null,
    destiny_env bigint unsigned not null,
    datetime datetime not null,
    user bigint unsigned not null,
    constraint foreign key (patrimony_item) references patrimony_item(id),
    constraint foreign key (origin_env) references environment(id),
    constraint foreign key (destiny_env) references environment(id)
);