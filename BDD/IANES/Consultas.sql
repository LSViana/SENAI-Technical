USE IANESPatrimonio;

DESCRIBE Usuario;
DESCRIBE Patrimonio;
DESCRIBE Ambiente;
DESCRIBE ItemPatrimonio;
DESCRIBE Categoria;
DESCRIBE Movimentacao;

-- Cadastro de usuário
INSERT INTO Usuario (Id, Nome, Sobrenome, Email, Senha, Tipo) VALUES (0, 'Nome', 'Sobrenome', 'Email', 'Senha', 'Tipo');
-- Alteração de usuário
UPDATE Usuario SET Nome = 'Nome', Sobrenome = 'Sobrenome', Email = 'Email', Senha = 'Senha', Tipo = 'Tipo' WHERE Id = 0;
-- Autenticação (email e senha)
SELECT * FROM Usuario WHERE Email = 'Email' AND Senha = 'Senha';
-- Alterar dados do usuário autenticado (Nome e sobrenome)
UPDATE Usuario SET Nome = 'Nome', Sobrenome = 'Sobrenome' WHERE Id = 0;
-- Alterar senha
UPDATE Usuario SET Senha = 'Senha' WHERE Id = 0;
-- Excluir usuário (somente administrador)
DELETE FROM Usuario WHERE Id = 0;
-- Listar usuários (somente administradores)
SELECT * FROM Usuario;
-- Cadastrar patrimônio (somente administrador)
INSERT INTO Patrimonio (Id, Nome, Data, IdCategoria, IdUsuario) VALUES (0, 'Nome', 'Data', 0, 0);
-- Listar patrimônios
SELECT * FROM Patrimonio;
-- Alterar patrimônio
UPDATE Patrimonio SET Nome = 'Nome', Data = 'Data', IdCategoria = 0, IdUsuario = 'IdUsuario' WHERE Id = 0;
-- Excluir patrimônio
DELETE FROM Patrimonio WHERE Id = 0;
-- Cadastrar ambiente
INSERT INTO Ambiente (Id, Nome) VALUES (0, 'Nome');
-- Listar ambiente
SELECT * FROM Ambiente;
-- Alterar ambiente
UPDATE Ambiente SET Nome = 'Nome' WHERE Id = 0;
-- Excluir ambiente
DELETE FROM Ambiente WHERE Id = 0;
-- Cadastrar item de patrimônio
INSERT INTO ItemPatrimonio (Id, IdUsuario, IdPatrimonio, IdAmbiente) VALUES(0, 0, 0, 0);
-- Excluir item de patrimônio
DELETE FROM ItemPatrimonio WHERE Id = 0;
-- Listar itens de patrimônio
SELECT * FROM ItemPatrimonio;
-- Exibir nome do patrimônio
SELECT P.Nome AS 'Nome do Patrimônio' FROM ItemPatrimonio AS IP
INNER JOIN Patrimonio AS P ON IP.IdPatrimonio = P.Id;
-- Exibir categoria do patrimônio
SELECT P.Nome AS 'Nome da Categoria do Patrimônio' FROM ItemPatrimonio AS IP
INNER JOIN Patrimonio AS P ON IP.IdPatrimonio = P.Id
INNER JOIN Categoria AS C ON P.IdCategoria = C.Id;
-- Onde está no momento
SELECT A.Nome AS 'Nome do Ambiente' FROM ItemPatrimonio AS IP
INNER JOIN Ambiente AS A ON IP.IdAmbiente = A.Id;
-- Realizar movimentação
INSERT INTO Movimentacao (Id, Data, ItemPatrimonio, IdUsuario, IdAmbienteOrigem, IdAmbienteDestino) VALUES (0, 'Data', 0, 0, 0, 0);
-- Visualizar movimentações de um determinado item
SELECT P.Nome AS 'Nome do Patrimônio', M.Data AS 'Data da Movimentação', AO.Nome AS 'Ambiente de Origem', AD.Nome AS 'Ambiente de Destino' FROM ItemPatrimonio AS IP
INNER JOIN Patrimonio AS P ON IP.IdPatrimonio = P.Id
INNER JOIN Movimentacao AS M ON M.IdItemPatrimonio = IP.Id
INNER JOIN Ambiente AS AO ON M.IdAmbienteOrigem = AO.Id
INNER JOIN Ambiente AS AD ON M.IdAmbienteDestino = AD.Id;