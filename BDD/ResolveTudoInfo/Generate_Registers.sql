-- Administrators
DESCRIBE administrator;
INSERT INTO administrator VALUES
	(0, 'lsviana', 'lsviana'),
    (0, 'rafa-thayto', 'rafa-thayto'),
    (0, 'jolsen', 'jolsen');
SELECT * FROM administrator;
-- Technician
DESCRIBE technician;
INSERT INTO technician VALUES
	(0, 'Gustavo Donegá', 'gdonega@gmail.com', 'gdone!@#', '78512512432'),
	(0, 'Matheus José', 'josemat@yahoo.com.br', 'gdone!@#', '78512512432'),
    (0, 'Gabriel Quintas', 'quintas@hotmail.com', 'gdone!@#', '78512512432'),
    (0, 'Carlos Eduardo Tsukamoto', 'tsukamoto@outlook.com', 'gdone!@#', '78512512432'),
    (0, 'Raynã Caetano', 'rcaetano@ymail.com', 'gdone!@#', '78512512432');
SELECT * FROM technician;
-- Report Type
DESCRIBE report_type;
INSERT INTO report_type VALUES
	(0, 'Problema de Hardware'),
    (0, 'Problema de Software'),
    (0, 'Impressora sem Tinta');
SELECT * FROM report_type;
-- Report
DESCRIBE report;
INSERT INTO report VALUES (0, 1, 'Computador reiniciando', 'Após uso contínuo o computador insiste em desligar sozinho', now(), null, 1, null, 'Não atendido');
INSERT INTO report VALUES (0, 2, 'Windows travando', 'Computador trava ao ser conectado na Internet', now(), null, 2, null, 'Não atendido');
INSERT INTO report VALUES (0, 2, 'Teclado não pressiona teclas adequadas', 'Acentuação não é inserida corretamente', now(), null, 3, null, 'Não atendido');
SELECT * FROM report;
-- Report History
DESCRIBE report_history;
INSERT INTO report_history VALUES (0, 1, 2, now(), 'Foi realizada a troca da pasta térmica do processador');
INSERT INTO report_history VALUES (0, 3, 1, now(), 'O ajuste no idioma do teclado foi realizado e configurado para ABNT2');
SELECT * FROM report_history;
-- Tests
SELECT technician.name as 'Nome do técnico', report.name AS 'Chamado', report.description as 'Descrição', report.start AS 'Data de Cadastro' FROM report
INNER JOIN technician ON report.id_recorder = technician.id
INNER JOIN report_type ON report.id_type = report_type.id;