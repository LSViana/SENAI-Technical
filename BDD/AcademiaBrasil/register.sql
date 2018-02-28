USE academiabrasil;
-- Students
DESCRIBE student;
INSERT INTO student VALUES
	(0, 'Lucas Viana', '3423-9921', '2014-06-08', 1.7, 75),
    (0, 'Gustavo Donegá', '7856-7456', '1960-01-01', 1.82, 82),
    (0, 'Rafael Thayto', '2154-8761', '2001-03-18', 1.79, 50),
    (0, 'Felipe Pereira', '7782-7538', '1988-12-25', 1.77, 78);
-- Disciplines
DESCRIBE discipline;
INSERT INTO discipline VALUES
	(0, 'Swimming'),
    (0, 'Wrestling'),
    (0, 'Judo');
-- Instructors
DESCRIBE instructor;
INSERT INTO instructor VALUES
	(0, 'Helena Strada', '78.569.598-2', '1979-07-12', 'Programação Java'),
    (0, 'Carlos Eduardo Tsukamoto', '54.721.123-9', '1972-01-03', 'Programação PHP');
-- Telephones
DESCRIBE telephone;
INSERT INTO telephone VALUES
	(0, '1121-9901'),
    (0, '1121-9902'),
    (0, '1121-9903');
-- Instructors' Telephones
DESCRIBE telephone_instructor;
INSERT INTO telephone_instructor VALUES
	(1, 1),
    (2, 2),
    (1, 3),
    (2, 3);
-- Classes
DESCRIBE class;
INSERT INTO class VALUES
	(0, 1, 1, 1, '14:00', '2:00', '2018-01-01', '2018-12-31'),
    (0, 2, 1, 2, '17:00', '2:00', '2018-01-01', '2018-12-31'),
    (0, 3, 2, NULL, '07:30', '4:00', '2018-06-01', '2018-12-31');
SELECT * FROM class;
-- Enrollments
DESCRIBE enrollment;
INSERT INTO enrollment VALUES
	('000-001', 4, 1, '2018-02-27'),
    ('000-002', 4, 2, '2018-02-26'),
    ('000-003', 5, 1, '2018-02-01'),
    ('000-004', 5, 2, '2018-02-01');
SELECT * FROM enrollment;
-- Presence Registers
DESCRIBE presence_register;
INSERT INTO presence_register VALUES
	(0, '000-001', '2018-02-27', 1, NULL),
    (0, '000-002', '2018-02-27', 1, NULL),
    (0, '000-003', '2018-02-28', 0, NULL),
    (0, '000-004', '2018-02-28', 1, NULL);