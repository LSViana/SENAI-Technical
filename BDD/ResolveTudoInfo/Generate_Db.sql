CREATE DATABASE resolvetudoinfo DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE resolvetudoinfo;

CREATE TABLE administrator (
	id bigint auto_increment primary key,
    name varchar(48) not null,
    password varchar(48) not null
);

CREATE TABLE technician (
	id bigint auto_increment primary key,
	name varchar(48) not null,
    email varchar(48) not null,
    password varchar(32) not null,
    cpf char(12) not null
);

CREATE TABLE report_type (
	id bigint auto_increment primary key,
    name varchar(48) not null
);

CREATE TABLE report_history (
	id bigint auto_increment primary key,
	id_report bigint not null,
    id_technician bigint not null,
    datetime datetime not null,
    description varchar(512) not null
);

CREATE TABLE report (
	id bigint auto_increment primary key,
    id_type bigint not null,
    name varchar(48) not null,
    description varchar(512) not null,
    start datetime not null,
    end datetime null,
    id_recorder bigint not null,
    id_attendant bigint null,
    status ENUM('Não atendido', 'Em atendimento', 'Encerrado'),
    constraint foreign key(id_type) references report_type(id)
);