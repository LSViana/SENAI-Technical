-- Creating the schema to test Java Servlet
CREATE SCHEMA Greetings DEFAULT CHARACTER SET utf8;

USE Greetings;
-- Creating an almost dummy table
CREATE TABLE Agenda (
	Id INT PRIMARY KEY AUTO_INCREMENT,
	`Name` VARCHAR(255),
    Email VARCHAR(128),
	Address VARCHAR(255),
	DateOfBirth DATE,
    CONSTRAINT Email_Unique UNIQUE(Email)
);

-- Introduction to SQL 'SELECT' command
SELECT * FROM Agenda;
SELECT Id, `Name` FROM Agenda;

-- Creating some new registers, using 'INSERT INTO' command
INSERT INTO Agenda VALUES(0, 'Lucas Viana Soares de Souza', 'lv201122@gmail.com', 'R. Inácio Monteiro, 600', '2001-01-22');
INSERT INTO Agenda VALUES(0, 'João Vitor Olsen Fernandes', 'jolsentecnico@gmail.com', 'R. Niterói, 280', '2000-04-25');
INSERT INTO Agenda VALUES(0, 'Rafael Thayto Tani', 'rafathayto@gmail.com', 'R. C, 200', '2001-03-18');

SELECT * FROM Agenda;

-- Using 'WHERE' command to filer the queries
SELECT * FROM Agenda WHERE `Name` = 'Lucas Viana Soares de Souza';

-- Using 'UPDATE ... SET ...' command to change the valuses stored on tables
UPDATE Agenda SET `Address` = 'R. D, 300' WHERE Id = 3;

-- Using 'DROP' to delete Database objects
DROP TABLE Agenda; -- Delete the table
DROP SCHEMA Greetings; -- Delete the schema