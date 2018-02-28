-- Classes and Instructors and Disciplines and Student Instructors
SELECT
	discipline.name AS 'Nome da Disciplina',
	instructor.name AS 'Nome do Instrutor',
	student.name AS 'Nome do Aluno Instrutor'
    FROM class
INNER JOIN instructor ON class.idinstructor = instructor.id
INNER JOIN discipline ON class.iddiscipline = discipline.id
LEFT JOIN student ON class.idstudent_instructor = student.id;

USE academiabrasil;

SELECT * FROM student;

INSERT INTO student VALUES
	(0, 'Bill Gates', '7894-1231', '1988-12-12', 1.9, 98);
    
SELECT * FROM discipline;

SELECT * FROM class WHERE iddiscipline = 3;

DESCRIBE enrollment;
SELECT * FROM enrollment;
INSERT INTO enrollment VALUES
	('000-005', 6, 9, '2018-02-27');

SELECT student.name AS 'Nome do Estudante', discipline.name AS 'Nome da Disciplina', class.classtime, class.initialdate, class.enddate FROM enrollment
INNER JOIN student ON enrollment.idstudent = student.id
INNER JOIN class ON class.id = enrollment.idclass
INNER JOIN discipline ON discipline.id = class.iddiscipline;