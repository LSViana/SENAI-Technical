-- Genética
USE genetica;

-- Função COUNT(*) retorna a quantidade de linhas retornadas pelo SELECT no qual está sendo utilizado
SELECT COUNT(id) FROM pessoa;

-- Função GROUP BY(*) agrupa por semelhança de características, categorias
SELECT genero, COUNT(id) FROM pessoa GROUP BY (genero);

-- Challenge: Quantidade de pessoas do gênero feminino
SELECT COUNT(id) FROM pessoa WHERE genero = 'FEMININO';

-- Empresa
USE empresa;

SELECT * FROM funcionario;

-- Buscando a quantidade funcionários por setor (GROUP BY + JOIN)
SELECT setor.nome, COUNT(funcionario.id) FROM funcionario
JOIN setor ON setor.id = funcionario.setor_id
GROUP BY setor_id;

-- AVG: Average (Média)
SELECT AVG(salario) FROM funcionario;

-- Challenge: Do setor, o setor com maior média salarial
SELECT nome, MAX(media_salario) FROM (SELECT s.*, AVG(f.salario) AS media_salario FROM funcionario as f
JOIN setor AS s ON s.id = f.setor_id
GROUP BY f.setor_id) AS setor_avg;