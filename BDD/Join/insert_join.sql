DESCRIBE address;
INSERT INTO address VALUES
	(0, 'Al. Barão de Limeira', 'Número 539', 'Santa Cecília', '01202902', 1, 1),
    (0, 'Rua da Carioca', 'Número 28', 'Centro', '20050020', 2, 2),
    (0, 'R. Pedro Vicente', 'Número 625', 'Canindé', '01109010', 1, 1);
SELECT * FROM address;
--
DESCRIBE branch;
INSERT INTO branch VALUES
	(0, 'Internet'),
    (0, 'Telefone'),
    (0, 'Televisão');
SELECT * FROM branch;
--
DESCRIBE contact;
INSERT INTO contact VALUES
	(0, 11, 32735000, 1),
    (0, 22, 22400126, 2),
    (0, 11, 27637501, 3);
SELECT * FROM contact;
--
DESCRIBE county;
INSERT INTO county VALUES
	(0, 'São Paulo'),
    (0, 'Rio de Janeiro');
SELECT * FROM county;
--
DESCRIBE subscriber;
INSERT INTO subscriber VALUES
	(0, 'Lucas Viana', 1, 1),
    (0, 'Rafael Thayto', 2, 2);
SELECT * FROM subscriber;
--
DESCRIBE subscriber_type;
INSERT INTO subscriber_type VALUES
	(0, 'Pré-Pago'),
	(0, 'Pós-Pago'),
    (0, 'Cartão Fidelidade');
SELECT * FROM subscriber_type;