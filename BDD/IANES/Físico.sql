CREATE DATABASE IANESPatrimonio DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
DROP DATABASE IANESPatrimonio;

USE IANESPatrimonio;

CREATE TABLE Patrimonio (
Id BIGINT(10) PRIMARY KEY,
Nome VARCHAR(32),
Data VARCHAR(32),
IdCategoria BIGINT(10),
IdUsuario BIGINT(10)
);

CREATE TABLE Movimentacao (
Id BIGINT(10) PRIMARY KEY,
Data DATETIME,
IdItemPatrimonio BIGINT(10),
IdUsuario BIGINT(10),
IdAmbienteOrigem BIGINT(10),
IdAmbienteDestino BIGINT(10)
);

CREATE TABLE Usuario (
Nome VARCHAR(32),
Id BIGINT(10) PRIMARY KEY,
Sobrenome VARCHAR(32),
Email VARCHAR(32),
Senha VARCHAR(32),
Tipo ENUM('Administrador', 'Comum')
);

CREATE TABLE Categoria (
Id BIGINT(10) PRIMARY KEY,
Nome VARCHAR(32)
);

CREATE TABLE Ambiente (
Id BIGINT(10) PRIMARY KEY,
Nome VARCHAR(32)
);

CREATE TABLE ItemPatrimonio (
Id BIGINT(10) PRIMARY KEY,
IdUsuario BIGINT(10),
IdPatrimonio BIGINT(10),
IdAmbiente BIGINT(10),
FOREIGN KEY(IdUsuario) REFERENCES Usuario (Id),
FOREIGN KEY(IdPatrimonio) REFERENCES Patrimonio (Id),
FOREIGN KEY(IdAmbiente) REFERENCES Ambiente (Id)
);

ALTER TABLE Patrimonio ADD FOREIGN KEY(IdCategoria) REFERENCES Categoria (Id);
ALTER TABLE Patrimonio ADD FOREIGN KEY(IdUsuario) REFERENCES Usuario (Id);
ALTER TABLE Movimentacao ADD FOREIGN KEY(IdItemPatrimonio) REFERENCES ItemPatrimonio (Id);
ALTER TABLE Movimentacao ADD FOREIGN KEY(IdUsuario) REFERENCES Usuario (Id);
ALTER TABLE Movimentacao ADD FOREIGN KEY(IdAmbienteOrigem) REFERENCES Ambiente (Id);
ALTER TABLE Movimentacao ADD FOREIGN KEY(IdAmbienteDestino) REFERENCES Ambiente (Id);