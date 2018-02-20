USE `scalapark`; 

-- Brands
INSERT INTO brand VALUES
	(0, 'Fiat'),
	(0, 'Volkswagen'),
    (0, 'Chevrolet'),
    (0, 'Ford');
INSERT INTO brand VALUES (0, 'Subaru');
SELECT * FROM brand;
-- Models
INSERT INTO model VALUES
	(0, 'Palio', 1),
    (0, 'Gol', 2),
    (0, 'Onix', 3),
    (0, 'Fiesta', 4);
INSERT INTO model VALUES (0, 'Impreza', 5);
INSERT INTO model VALUES (0, 'Camaro', 3);
SELECT * FROM model;
-- Color
INSERT INTO color VALUES
	(0, 'Red'),
    (0, 'Blue'),
    (0, 'Green'),
    (0, 'Silver');
SELECT * FROM color;
-- Vehicle
INSERT INTO vehicle VALUES
	(0, 1, 1),
    (0, 1, 2),
    (0, 1, 4),
    (0, 2, 3),
    (0, 2, 4);
INSERT INTO vehicle VALUES (0, 6, 1);
SELECT * FROM vehicle;
-- Parking Lot
INSERT INTO parkinglot VALUES
	(0, 'Pátio 1'),
    (0, 'Pátio 2'),
    (0, 'Pátio 3');
SELECT * FROM parkinglot;
-- Area
-- Restarting Auto Increment Counting Operation
ALTER TABLE area AUTO_INCREMENT = 1;
-- Creating Procedure to Create Parking Areas
delimiter //
CREATE procedure scalapark.while_example()
wholeblock:BEGIN
DECLARE x INT DEFAULT 0;
SET x = 1;
WHILE x <= 8 DO
	INSERT INTO area VALUES (0, 1, CONCAT('A', x));
	INSERT INTO area VALUES (0, 2, CONCAT('B', x));
    INSERT INTO area VALUES (0, 3, CONCAT('C', x));
    SET x = x + 1;
END WHILE;
END//
-- Command to Drop, Test Purpose
DROP PROCEDURE while_example;
-- Calling Procedure to Create Parking Areas
CALL while_example();
SELECT * FROM area;
-- Client
INSERT INTO client VALUES
	(0, 'Rafael Thayto Tani', '2001-03-18'),
    (0, 'Lucas Viana Soares de Souza', '2001-01-22');
INSERT INTO client VALUES (0, 'Carlos Eduardo Tsukamoto', '1987-12-03');
SELECT * FROM client;
-- Client Vehicle
INSERT INTO clientvehicle VALUES
	(1, 1),
    (1, 2),
    (2, 3);
INSERT INTO clientvehicle VALUES (3, 6);
-- Parking
DESCRIBE parking;
-- Adding Parking Operations
INSERT INTO parking VALUES (0, 1, 1, now(), null);
INSERT INTO parking VALUES (0, 6, 25, now(), null);
TRUNCATE TABLE parking;
-- Leaving Parking Lot
UPDATE parking SET `exit` = now() WHERE id = 1;
UPDATE parking SET `exit` = now() WHERE id = 2;
-- Querying Parking Operations
SELECT * FROM parking;
-- Tests
SELECT brand.name AS 'Nome da Marca', model.name AS 'Nome do Modelo' FROM brand
JOIN model ON brand.id = model.idbrand;
--
SELECT model.name AS 'Modelo', color.name AS 'Cor', brand.name AS 'Marca' FROM vehicle
INNER JOIN model ON vehicle.idmodel = model.id
INNER JOIN brand ON model.idbrand = brand.id
INNER JOIN color ON vehicle.idcolor = color.id;
-- Clients and Vehicles
SELECT client.fullname AS 'Cliente', model.name AS 'Modelo', color.name AS 'Cor', brand.name AS 'Marca' FROM client
INNER JOIN clientvehicle ON client.id = clientvehicle.idclient
INNER JOIN vehicle ON vehicle.id = clientvehicle.idvehicle
INNER JOIN model ON vehicle.idmodel = model.id
INNER JOIN brand ON model.idbrand = brand.id
INNER JOIN color ON vehicle.idcolor = color.id;
-- Client, Vehicles and Parkings
SELECT client.fullname AS 'Cliente', model.name AS 'Modelo', color.name AS 'Cor', brand.name AS 'Marca', area.name AS 'Vaga', parking.enter AS 'Data Entrada', parking.exit AS 'Data Saída' FROM client
INNER JOIN clientvehicle ON client.id = clientvehicle.idclient
INNER JOIN vehicle ON vehicle.id = clientvehicle.idvehicle
INNER JOIN model ON vehicle.idmodel = model.id
INNER JOIN brand ON model.idbrand = brand.id
INNER JOIN color ON vehicle.idcolor = color.id
INNER JOIN parking ON parking.idvehicle = vehicle.id
INNER JOIN area ON parking.idarea = area.id;