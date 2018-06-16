DROP DATABASE IF EXISTS livrocara;

CREATE DATABASE livrocara;

USE livrocara;

CREATE TABLE hash_salts
(
	nome CHAR(20) NOT NULL UNIQUE,
    valor TINYTEXT NOT NULL
);

CREATE TABLE perfil
(
	id BIGINT UNSIGNED AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    email VARCHAR(120) NOT NULL,
    senha CHAR(32) NOT NULL,
    
    PRIMARY KEY(id)
);

CREATE TABLE foto
(
	id BIGINT UNSIGNED AUTO_INCREMENT,
    perfil_id BIGINT UNSIGNED NOT NULL,
    nome CHAR(32) NOT NULL,
    
    PRIMARY KEY(id),
    
    CONSTRAINT fk_perfil_IN_foto
		FOREIGN KEY (perfil_id)
			REFERENCES perfil(id)
);

#INSERT HASHES
INSERT INTO 
	hash_salts (nome, valor)
VALUES
	('hash_perfil', "JAKkjskKSJASSHi9828E9HkJHJkhkKjahsKK"),
    ('hash_foto', "KALlksakdaj98dsa90SKDASLJlksdsdpISDLSDLK");
    
SELECT * FROM foto;
SELECT * FROM hash_salts;
SELECT * FROM perfil;

#GETTING HASH
SELECT valor FROM hash_salts WHERE nome = 'hash_perfil';

#INSERT PEOPLE
DESCRIBE perfil;
INSERT INTO perfil VALUES
	(0, 'Lucas Viana', 'lucas@email.com', MD5(
		CONCAT('password',
		(SELECT * FROM view_hashfoto))
    ));
    
INSERT INTO perfil VALUES
	(0, 'Felipe Oliveira', 'foliveira@email.com', MD5(
		CONCAT('senha',
		(SELECT * FROM view_hashfoto))
    ));
    
DESCRIBE foto;

INSERT INTO foto VALUES
	(1, 1, MD5(id + (select * from view_hashfoto)));
    
SELECT MD5(id + (select * from view_hashfoto));

SELECT * FROM foto;

#INSERT INTO 
	#perfil (nome, email, senha) 
#VALUES 
	#('Adriano', 'adriano@gmail.com', MD5(CONCAT('senha132', (SELECT valor FROM hash_salts WHERE nome = 'hash_perfil')))), 
    #('Bianca', 'bianca@gmail.com', MD5(CONCAT('senha123', (SELECT * FROM viewHashPerfil))));
    
#CREATING VIEW TO GET HASH
CREATE VIEW view_hashfoto AS SELECT valor FROM hash_salts WHERE nome = 'hash_foto';
CREATE VIEW view_hashperfil AS SELECT valor FROM hash_salts WHERE nome = 'hash_perfil';