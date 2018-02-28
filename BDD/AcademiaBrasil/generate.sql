CREATE DATABASE academiabrasil DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE academiabrasil;

CREATE TABLE student (
	id bigint unsigned auto_increment,
    name varchar(64) not null,
    telephone varchar(11) not null,
    dateofbirth datetime not null,
    height float not null,
    weight float not null,
    constraint primary key(id)
);

CREATE TABLE telephone (
	id bigint unsigned auto_increment,
    telephone varchar(11) not null,
    constraint primary key(id)
);

CREATE TABLE discipline (
	id bigint unsigned auto_increment,
    name varchar(64) not null,
    constraint primary key(id)
);

CREATE TABLE instructor (
	id bigint unsigned auto_increment,
    name varchar(64) not null,
    rg varchar(64) not null,
    dateofbirth datetime not null,
    qualification varchar(64) not null,
    constraint primary key(id)
);

CREATE TABLE telephone_instructor (
	idtelephone bigint unsigned,
    idinstructor bigint unsigned,
    constraint primary key(idtelephone, idinstructor)
);

CREATE TABLE class (
	id bigint unsigned auto_increment,
    iddiscipline bigint unsigned not null,
    idinstructor bigint unsigned not null,
    idstudent_instructor bigint unsigned null,
    classtime time not null,
    classduration time not null,
    initialdate datetime not null,
    enddate datetime not null,
    constraint primary key(id),
    constraint foreign key(iddiscipline) references discipline(id),
    constraint foreign key(idinstructor) references instructor(id)
);

CREATE TABLE enrollment (
	code varchar(24) not null,
    idclass bigint unsigned not null,
    idstudent bigint unsigned not null,
    dateenrollment datetime not null,
    constraint primary key(code),
    constraint foreign key(idclass) references class(id),
    constraint foreign key(idstudent) references student(id)
);

CREATE TABLE presence_register (
	id bigint unsigned auto_increment,
    enrollmentcode varchar(24) not null,
    datetime datetime not null,
    present bit not null,
    reason varchar(128),
    constraint primary key(id),
    constraint foreign key(enrollmentcode) references enrollment(code)
);