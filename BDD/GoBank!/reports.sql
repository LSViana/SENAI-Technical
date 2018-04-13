USE gobank_tt3;
-- Lista de Funcionários
SELECT * FROM Funcionario AS F
JOIN Pessoa AS P ON F.PessoaId = P.Id;
-- Lista de Usuários
SELECT * FROM Usuario AS U
JOIN Pessoa AS P ON U.PessoaId = P.Id;
-- Lista de Agências
SELECT * FROM Agencia;
-- Lista de Contas de uma determina agência, escolher agência pelo id
SET @AgenciaEscolhida = 1;
SELECT * FROM Conta WHERE AgenciaId = @AgenciaEscolhida;