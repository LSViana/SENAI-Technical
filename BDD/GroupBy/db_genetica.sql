DROP DATABASE IF EXISTS genetica;

CREATE DATABASE genetica;

USE genetica;

CREATE TABLE pessoa
(
	id BIGINT UNSIGNED AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    dt_nascimento DATE NOT NULL,
    genero ENUM('MASCULINO', 'FEMININO', 'OUTRO'), 
    cor_cabelo ENUM ('PRETO', 'CASTANHO', 'LOIRO', 'VERMELHO') NOT NULL,
    tom_pele ENUM('ESCURA', 'MEDIA', 'CLARA') NOT NULL,
    cor_olhos ENUM('AZUL', 'CINZA', 'VERDE', 'CASTANHO') NOT NULL,
    
    
    PRIMARY KEY(id)
);

INSERT INTO
	pessoa (nome, dt_nascimento, genero, cor_cabelo, tom_pele, cor_olhos)
VALUES
	('Maria', '1990-01-01', 'FEMININO', 'PRETO', 'ESCURA', 'CASTANHO'),
    ('João', '1990-02-01', 'MASCULINO', 'CASTANHO', 'CLARA', 'AZUL'),
    ('Ana', '1991-01-01', 'OUTRO', 'VERMELHO', 'CLARA', 'VERDE'),
    ('Davi', '1991-02-01', 'OUTRO', 'PRETO', 'ESCURA', 'VERDE'),
    ('Adriana', '1992-01-01', 'FEMININO', 'CASTANHO', 'MEDIA', 'CASTANHO'),
    ('Márcia', '1992-02-01', 'FEMININO', 'CASTANHO', 'MEDIA', 'CASTANHO'),
    ('Aparecida', '1992-02-01', 'FEMININO', 'PRETO', 'ESCURA', 'CASTANHO');
    
    
SELECT * FROM pessoa LIMIT 0,4;