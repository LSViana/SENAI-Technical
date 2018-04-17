USE parkshow;
-- Exercise a)
SELECT * FROM client AS c
INNER JOIN vehicle AS v ON v.cpf_client = c.cpf;
-- Exercise b)
SELECT DISTINCT c.name FROM client AS c
INNER JOIN vehicle AS v ON v.cpf_client = c.cpf
WHERE vehicle.licenseplate = 'JJJ-2020';
-- Exercise c)
SELECT v.licenseplate AS 'License Plate', v.color AS 'Color' FROM vehicle AS v
INNER JOIN parking AS p ON p.licenseplate_vehicle = v.licenseplate
WHERE p.id = 1;
-- Exercise d)
-- There is no @year field
-- Exercise e)
-- There is no @year field
-- Exercise f)
SELECT y.address, p.dateentrance, p.dateexit FROM parking AS p
INNER JOIN vehicle AS v ON p.licenseplate_vehicle = v.licenseplate
INNER JOIN yard AS y ON p.id_yard = y.id
WHERE v.licenseplate = 'JEG-1010';
-- Exercise g)
SELECT COUNT(*) FROM parking AS p
INNER JOIN vehicle AS v ON p.licenseplate_vehicle = v.licenseplate
WHERE v.color = 'Green'
GROUP BY v.color;
-- Exercise h)
SELECT c.* FROM client AS c
INNER JOIN vehicle AS v ON v.cpf_client = c.cpf
WHERE vehicle.id = 1;
-- Exercise i)
SELECT v.licenseplate AS 'License Plate', p.dateentrance, p.dateexit FROM parking AS p
INNER JOIN vehicle AS v ON p.licenseplate_vehicle = v.licenseplate
WHERE v.color = 'Green';
-- Exercise j)
SELECT p.* FROM parking AS p
INNER JOIN vehicle AS v ON p.licenseplate_vehicle = v.licenseplate
WHERE v.licenseplate = 'JJJ-2020';
-- Exercise k)
SELECT DISTINCT c.name FROM vehicle AS v
INNER JOIN parking AS p ON p.licenseplate_vehicle = v.licenseplate
INNER JOIN client AS c ON v.cpf_client = c.cpf
WHERE p.id = 2;
-- Exercise l)
SELECT c.cpf FROM vehicle AS v
INNER JOIN parking AS p ON p.licenseplate_vehicle = v.licenseplate
INNER JOIN client AS c ON v.cpf_client = c.cpf
WHERE p.id = 3;
-- Exercise m)
SELECT m.description FROM vehicle AS v
INNER JOIN parking AS p ON p.licenseplate_vehicle = v.licenseplate
INNER JOIN model AS m ON v.id_model = m.id
WHERE p.id = 2;
-- Exercise n)
SELECT v.licenseplate, c.name, m.description FROM vehicle AS v
INNER JOIN client AS c ON v.cpf_client = c.cpf
INNER JOIN model as m ON v.id_model = m.id;