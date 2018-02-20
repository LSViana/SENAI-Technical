CREATE DATABASE `scalapark` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE `scalapark`;

CREATE TABLE `brand` (
	`id` bigint auto_increment primary key,
    `name` varchar(48) not null
);

CREATE TABLE `model` (
	`id` bigint auto_increment primary key,
    `name` varchar(64) not null,
    `idbrand` bigint not null,
    constraint foreign key(`idbrand`) references `brand`(`id`)
);

CREATE TABLE `color` (
	`id` bigint auto_increment primary key,
    `name` varchar(48) not null
);

CREATE TABLE `vehicle` (
	`id` bigint auto_increment primary key,
    `idmodel` bigint not null,
    `idcolor` bigint not null,
    constraint foreign key(`idmodel`) references `model`(`id`),
    constraint foreign key(`idcolor`) references `color`(`id`)
);

CREATE TABLE `parkinglot` (
	`id` bigint auto_increment primary key,
    `name` varchar(32) not null
);

CREATE TABLE `area` (
	`id` bigint auto_increment primary key,
    `idparkinglot` bigint not null,
    `name` varchar(8) not null,
    constraint foreign key(`idparkinglot`) references `parkinglot`(`id`)
);

CREATE TABLE `parking` (
	`id` bigint auto_increment primary key,
	`idvehicle` bigint not null,
    `idarea` bigint not null,
    `enter` datetime not null,
    `exit` datetime null,
    constraint foreign key(`idvehicle`) references `vehicle`(`id`),
    constraint foreign key(`idarea`) references `area`(`id`)
);

CREATE TABLE `client` (
	`id` bigint auto_increment primary key,
    `fullname` varchar(64) not null,
    `dateOfBirth` datetime not null
);

CREATE TABLE `clientvehicle` (
	`idclient` bigint not null,
    `idvehicle` bigint not null
);