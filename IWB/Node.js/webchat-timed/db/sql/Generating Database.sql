CREATE SCHEMA `webchat-timed` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE `webchat-timed`;

CREATE TABLE `message` (
	id bigint primary key auto_increment,
    content varchar(512) not null,
    author varchar(64),
    datetime datetime not null
);

CREATE VIEW `last-message` AS
SELECT UPDATE_TIME
FROM   information_schema.tables
WHERE  TABLE_SCHEMA = 'webchat-timed'
   AND TABLE_NAME = 'message';

SELECT * FROM `message`;
SELECT * FROM `last-message`;

INSERT INTO `message` VALUES(0, 'Greetings, Universe!', 'Lucas Viana', now());
INSERT INTO `message` VALUES(0, 'The last time is still working!', 'Rafael Thayto', now());