create database casinoroyale;

use casinoroyale;

create table user (
	id bigint auto_increment primary key,
    firstname varchar(24) not null,
    lastname varchar(24) not null,
    username varchar(24) not null,
    password varchar(128) not null
) default charset=utf8;

create table store (
	id bigint auto_increment primary key,
    name varchar(64) not null,
    address varchar(128) not null
) default charset=utf8;

create table brand (
	id bigint auto_increment primary key,
	name varchar(64) not null,
    image varchar(128) not null
) default charset=utf8;

create table product (
	id bigint auto_increment primary key,
    idbrand bigint not null,
    price decimal(8, 2) not null,
	name varchar(64) not null,
	description varchar(256) not null,
    image varchar(256) not null,
    constraint foreign key (idbrand) references brand(id)
) default charset=utf8;

create table sale (
	id bigint auto_increment primary key,
    iduser bigint not null,
    datetime datetime not null,
    idstore bigint not null,
    constraint foreign key (idstore) references store(id)
) default charset=utf8;

create table saleitem (
	id bigint auto_increment primary key,
	idsale bigint not null,
    idproduct bigint not null,
	price decimal(8, 2) not null,
    constraint foreign key (idsale) references sale(id),
    constraint foreign key (idproduct) references product(id)
) default charset=utf8;