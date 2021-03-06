DROP DATABASE IF EXISTS gobank_tt3;
CREATE DATABASE gobank_tt3;

USE gobank_tt3;

CREATE TABLE Pessoa (
Id BIGINT(10) PRIMARY KEY AUTO_INCREMENT,
NomeCompleto VARCHAR(32) NOT NULL,
Cpf VARCHAR(11) UNIQUE NOT NULL,
DataNascimento DATETIME NOT NULL,
EnderecoId BIGINT(10) NOT NULL
);

CREATE TABLE Usuario (
Id BIGINT(10) PRIMARY KEY AUTO_INCREMENT,
PessoaId BIGINT(10) NOT NULL,
FOREIGN KEY(PessoaId) REFERENCES Pessoa (Id)
);

CREATE TABLE Conta (
NumeroIdentificacao BIGINT(16) PRIMARY KEY AUTO_INCREMENT,
UsuarioId BIGINT(10) NOT NULL,
AgenciaId BIGINT(10) NOT NULL,
FOREIGN KEY(UsuarioId) REFERENCES Usuario (Id)
);

CREATE TABLE Agencia (
Id BIGINT(10) PRIMARY KEY AUTO_INCREMENT,
EnderecoId BIGINT(10) NOT NULL,
GerenteId BIGINT(10) NOT NULL
);

CREATE TABLE Transacao (
Id BIGINT(10) PRIMARY KEY AUTO_INCREMENT,
DataHora DATETIME NOT NULL,
Valor NUMERIC(18,2) NOT NULL,
ContaOrigemNI BIGINT(16) NOT NULL,
ContaDestinoNI BIGINT(16) NOT NULL,
FOREIGN KEY(ContaOrigemNI) REFERENCES Conta (NumeroIdentificacao),
FOREIGN KEY(ContaDestinoNI) REFERENCES Conta (NumeroIdentificacao)
);

CREATE TABLE Trabalha (
AgenciaId BIGINT(10) NOT NULL,
FuncionarioId BIGINT(10) NOT NULL,
FOREIGN KEY(AgenciaId) REFERENCES Agencia (Id)
);

CREATE TABLE Funcionario (
Id BIGINT(10) PRIMARY KEY AUTO_INCREMENT,
PessoaId BIGINT(10) NOT NULL,
CargoId BIGINT(10) NOT NULL,
FOREIGN KEY(PessoaId) REFERENCES Pessoa (Id)
);

CREATE TABLE Cargo (
Id BIGINT(10) PRIMARY KEY AUTO_INCREMENT,
Nome VARCHAR(32) NOT NULL
);

CREATE TABLE Endereco (
Id BIGINT(10) PRIMARY KEY AUTO_INCREMENT,
Estado VARCHAR(48) NOT NULL,
Cidade VARCHAR(48) NOT NULL,
Logradouro VARCHAR(64) NOT NULL,
Numero NUMERIC(5) NOT NULL,
Complemento VARCHAR(128) NOT NULL
);

CREATE TABLE Gerente (
Id BIGINT(10) PRIMARY KEY AUTO_INCREMENT,
FuncionarioId BIGINT(10) NOT NULL,
FOREIGN KEY(FuncionarioId) REFERENCES Funcionario (Id)
);

ALTER TABLE Pessoa ADD FOREIGN KEY(EnderecoId) REFERENCES Endereco (Id);
ALTER TABLE Conta ADD FOREIGN KEY(AgenciaId) REFERENCES Agencia (Id);
ALTER TABLE Agencia ADD FOREIGN KEY(EnderecoId) REFERENCES Endereco (Id);
ALTER TABLE Agencia ADD FOREIGN KEY(GerenteId) REFERENCES Gerente (Id);
ALTER TABLE Trabalha ADD FOREIGN KEY(FuncionarioId) REFERENCES Funcionario (Id);
ALTER TABLE Funcionario ADD FOREIGN KEY(CargoId) REFERENCES Cargo (Id);