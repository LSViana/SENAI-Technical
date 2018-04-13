CREATE DATABASE ianestt3 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
--
DROP DATABASE rhtt3;
CREATE DATABASE rhtt3 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
--
DROP DATABASE jucacontrol_production;
CREATE DATABASE jucacontrol_production;
USE jucacontrol_production;
SELECT * FROM usuario;
TRUNCATE TABLE usuario;
--
USE ianestt3;
SELECT * FROM user;