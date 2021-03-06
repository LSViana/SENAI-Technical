DROP DATABASE IF EXISTS parkshow;

CREATE DATABASE parkshow DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE parkshow;

CREATE TABLE client (
	cpf INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    dateOfBirth DATE NOT NULL
);

CREATE TABLE model (
	id INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(40) NOT NULL
);

CREATE TABLE yard (
	id INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    address VARCHAR(64) NOT NULL,
    capacity INTEGER NOT NULL
);

CREATE TABLE vehicle (
	licenseplate VARCHAR(8) PRIMARY KEY,
    id_model INTEGER UNSIGNED NOT NULL,
    cpf_client INTEGER UNSIGNED NOT NULL,
    color VARCHAR(20) NOT NULL,
    CONSTRAINT FOREIGN KEY(id_model) REFERENCES model(id),
    CONSTRAINT FOREIGN KEY(cpf_client) REFERENCES client(cpf)
);

CREATE TABLE parking (
	id INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    id_yard INTEGER UNSIGNED NOT NULL,
    licenseplate_vehicle VARCHAR(8) NOT NULL,
    dateentrance DATETIME NOT NULL,
    dateexit DATETIME
);