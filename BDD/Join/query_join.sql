-- Exercise 1
SELECT s.name AS 'Nome do Assinante', a.description AS 'Descrição', a.complement AS 'Complemento', a.district AS 'Bairro', a.CEP AS 'CEP', CONCAT('(', c.ddd, ') ', c.telephone) AS 'Telefone'
FROM subscriber AS s
INNER JOIN address AS a ON a.id_subscriber = s.id
INNER JOIN contact AS c ON c.id_address = a.id;

-- Exercise 2
SELECT s.name AS 'Nome', b.description AS 'Ramo' FROM subscriber AS s
INNER JOIN branch AS b ON s.id_branch = b.id
ORDER BY b.description, s.name;

-- Exercise 3
SELECT * FROM subscriber AS s
INNER JOIN address AS a ON a.id_subscriber = s.id
INNER JOIN county AS c ON a.id_county = c.id
INNER JOIN subscriber_type AS st ON s.id_subscriber_type = st.id
WHERE c.description = 'Pelotas' AND st.description = 'Residencial';

-- Exercise 4
SELECT subscriber_name AS 'Nome do Assinante' FROM (SELECT DISTINCT s.name AS 'subscriber_name', COUNT(c.id) AS 'contact_amount' FROM contact AS c
INNER JOIN address AS a ON c.id_address = a.id
INNER JOIN subscriber AS s ON a.id_subscriber = s.id
GROUP BY s.id) AS subscriber_contact
WHERE contact_amount > 1;

-- Exercise 5
SELECT s.name AS 'Nome do Assinante', CONCAT('(', c.ddd, ') ', c.telephone) AS 'Telefone', st.description AS 'Tipo de Assinante', cy.description AS 'Município'
FROM subscriber AS s
INNER JOIN address AS a ON a.id_subscriber = s.id
INNER JOIN county AS cy ON a.id_county = cy.id
INNER JOIN contact AS c ON c.id_address = a.id
INNER JOIN subscriber_type AS st ON s.id_subscriber_type = st.id
WHERE st.description = 'Comercial' AND (cy.description = 'Natal' OR cy.description = 'João Câmara');