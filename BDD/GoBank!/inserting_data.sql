-- Endereco
DESCRIBE Endereco;
INSERT INTO Endereco VALUES
	(0, 'São Paulo', 'São Paulo', 'Al. Barão de Limeira', 539, 'Sede GoBank! Brasil'),
    (0, 'Rio de Janeiro', 'Rio de Janeiro', 'Av. Bartolomeu Mitre', 340, 'Filial GoBank! Rio de Janeiro'),
	(0, 'Rio de Janeiro', 'Rio de Janeiro', 'R. Humberto de Campos', 671, 'Casa da Andressa'),
    (0, 'Rio de Janeiro', 'Rio de Janeiro', 'R. Humberto de Campos', 128, 'Casa do Carlos'),
    (0, 'São Paulo', 'São Paulo', 'R. Glicério', 724, 'Casa do Bernardo');
SELECT * FROM Endereco;
-- Cargo
DESCRIBE Cargo;
INSERT INTO Cargo VALUES
	(0, 'Funcionário'),
    (0, 'Gerente');
SELECT * FROM Cargo;
-- Pessoa
DESCRIBE Pessoa;
INSERT INTO Pessoa VALUES
	(0, 'Andressa Mesquita', '86745886030', '1988-05-31', 3),
    (0, 'Carlos Oliveira', '37263534371', '1984-11-17', 4),
    (0, 'Bernardo Queiroz', '44436316823', '1993-01-18', 5);
SELECT * FROM Pessoa;
-- Usuario
DESCRIBE Usuario;
INSERT INTO Usuario VALUES
	(0, 1),
    (0, 2),
    (0, 3);
SELECT * FROM Usuario;
-- Funcionario
DESCRIBE Funcionario;
INSERT INTO Pessoa VALUES
	(0, 'Antenor Almeida', '85402289476', '1992-05-01', 3),
    (0, 'Beatriz Beata', '47884859823', '1987-12-25', 4),
    (0, 'Clemente Canino', '84456604115', '1971-07-11', 3),
    (0, 'Daniela Denis', '62153183022', '1997-04-30', 3),
    (0, 'Eduardo Elegante', '54734603995', '1991-06-24', 5);
INSERT INTO Funcionario VALUES
	(0, 4, 1),
    (0, 5, 2),
    (0, 6, 2),
    (0, 7, 1),
    (0, 8, 1);
SELECT * FROM Pessoa;
SELECT * FROM Funcionario;
-- Gerente
DESCRIBE Gerente;
INSERT INTO Gerente VALUES
	(0, 2),
    (0, 3);
SELECT * FROM Gerente;
-- Agencia
DESCRIBE Agencia;
INSERT INTO Agencia VALUES
	(0, 1, 1),
    (0, 2, 1);
SELECT * FROM Agencia;
-- Trabalha
DESCRIBE Trabalha;
INSERT INTO Trabalha VALUES
	(1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (2, 5);
SELECT * FROM Trabalha;
-- Conta
DESCRIBE Conta;
INSERT INTO Conta VALUES
	(0, 1, 1),
    (0, 1, 1),
    (0, 3, 2);
SELECT * FROM Conta;
-- Transacao
DESCRIBE Transacao;
INSERT INTO Transacao VALUES
	(0, NOW(), 100, 1, 3),
    (0, NOW(), 200, 2, 3),
    (0, NOW(), 300, 1, 3),
    (0, NOW(), 400, 3, 1),
    (0, NOW(), 500, 3, 2);
SELECT * FROM Transacao;