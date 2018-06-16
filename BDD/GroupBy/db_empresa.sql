DROP DATABASE IF EXISTS empresa;

CREATE DATABASE empresa;

USE empresa;

CREATE TABLE setor
(
	id BIGINT UNSIGNED AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL UNIQUE,
    
    PRIMARY KEY(id)
);

CREATE TABLE funcionario
(
	id BIGINT UNSIGNED AUTO_INCREMENT,
    setor_id BIGINT UNSIGNED NOT NULL,
    nome VARCHAR(80) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
	salario NUMERIC(12, 2) UNSIGNED NOT NULL,
    genero ENUM('M', 'F', 'O'),
    
    PRIMARY KEY(id),
    
    FOREIGN KEY(setor_id)
		REFERENCES setor(id)
);

CREATE TABLE cliente
(
	id BIGINT UNSIGNED AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    
    PRIMARY KEY(id)
);

INSERT INTO
	setor (nome)
VALUES
	('Financeiro'), 	#1
    ('TI'), 			#2
    ('Fiscal');			#3
    
INSERT INTO
	funcionario (setor_id, nome, cpf, salario, genero)
VALUES
	(1, 'Adriano Aclina', '11122233311', 4000, 'M'),
    (2, 'Bianca Mancino', '22233344422', 5000, 'F'),
    (2, 'Camila Andrade', '333445533', 5000, 'F'),
    (2, 'Daniel Clemente', '44455566644', 3000, 'M'),
    (3, 'Eduardo Santana', '55566677766', 4000, 'O'),
    (1, 'Fernanda Andrade', '66677788866', 10000, 'F'),
    (2, 'Gerson Roberston', '77788899977', 800, 'M');
    
INSERT INTO
	cliente (nome)
VALUES
	('Cliente'),
    ('Eduardo');
    
#--------------- UNION -----------------------#

#Buscando funcionários e clientes
SELECT 
	id, nome
FROM 
	funcionario
UNION  #Une dois SELECTs com o mesmo número de colunas
SELECT 
	id, nome
FROM 
	cliente; 
    
	
