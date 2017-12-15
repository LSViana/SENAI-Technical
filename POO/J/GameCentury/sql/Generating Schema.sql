DROP SCHEMA `gamecentury`;

CREATE SCHEMA `gamecentury` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE `gamecentury`;

CREATE TABLE `user` (
	`id` int primary key auto_increment,
	`name` varchar(128) not null,
	`username` varchar(32) not null,
	`password` varchar(32) not null
);

INSERT INTO `user` VALUES(0, 'Lucas', 'lsviana', 'lv201122');
INSERT INTO `user` VALUES(0, 'Rafael', 'thayto', 'thayto777');

SELECT * FROM `user`;

SELECT * FROM `user` WHERE `username` = 'lsviana';

CREATE TABLE `buy` (
	`id` int primary key auto_increment,
	`user` int not null,
    `datetime` datetime not null
);