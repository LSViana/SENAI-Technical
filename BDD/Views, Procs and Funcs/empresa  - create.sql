DROP DATABASE IF EXISTS empresa;

CREATE DATABASE empresa;

USE empresa;

CREATE TABLE cargo
(
	id BIGINT UNSIGNED AUTO_INCREMENT,
    nome VARCHAR(40) NOT NULL,
    salario_base NUMERIC(12, 2) UNSIGNED NOT NULL,
    
    PRIMARY KEY(id)
);

CREATE TABLE funcionario
(
	id BIGINT UNSIGNED AUTO_INCREMENT,
    cargo_id BIGINT UNSIGNED NOT NULL,
    nome VARCHAR(80) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    salario NUMERIC(12, 2) UNSIGNED NOT NULL,
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP,
    data_desativacao DATE NULL,
    
    PRIMARY KEY(id),
    
    FOREIGN KEY (cargo_id)
		REFERENCES cargo(id)
);

#Cadastro de cargos
INSERT INTO cargo (nome, salario_base) VALUES ('Programador Junior', 3000), ('Programador Pleno', 5000), ('Programador Senior', 8000);

SELECT valor FROM cargo WHERE id = ?;

#Cadastro de funcionarios
#INSERT INTO funcionario (cargo_id, nome, cpf, salario, data_desativacao) 
#VALUES
	#(1, 'Vanessa Camargo', '754326532', buscarSalarioDoCargo(1), NULL),
	#(2, 'Zezé Di Camargo', '134524522', buscarSalarioDoCargo(2), NOW()), 
	#(3, 'Thales Faggiano', '128475743', buscarSalarioDoCargo(3), NULL), 
	#(4, 'Roger Waters', '123243432', buscarSalarioDoCargo(3), '2018-01-01');