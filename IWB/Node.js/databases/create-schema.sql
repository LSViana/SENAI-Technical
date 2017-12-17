CREATE SCHEMA `node-intro` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE `node-intro`;

CREATE TABLE `company` (
	`id` int primary key auto_increment,
	`name` varchar(64) not null,
	`description` varchar(256) not null,
    `image` varchar(512) not null
);

INSERT INTO `company` VALUES(0, 'Pizza Hut', 'Make It Great', 'https://vignette.wikia.nocookie.net/logopedia/images/9/99/Pizza_Hut.svg/revision/latest/scale-to-width-down/200?cb=20170909043942');
INSERT INTO `company` VALUES(0, 'Google', 'Don\'t Be Evil', 'https://www.festisite.com/static/partylogo/img/logos/google.png');

SELECT * FROM `company`;