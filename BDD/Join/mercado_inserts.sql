USE bdo_mercado;

INSERT INTO 
	endereco (cidade, bairro, logradouro, numero)
VALUES
	('São Paulo', 'Guaianases', 'R. das Flores', 255), 		#1
    ('São Paulo', 'Itaim Paulista', 'R. dos Camões', 666); 	#2
    
INSERT INTO
	cliente(endereco_id, nome, cpf)
VALUES
	(1, 'Felipe Pereira de Oliveira', '44955566699'), 	#1
    (2, 'Caio Oliveira', '55599988875'); 				#2
    
INSERT INTO
	categoria (nome)
VALUES
	('Bebidas'), 			#1
	('Cereais e Sementes'), #2
    ('Verduras'); 			#3
    
INSERT INTO
	produto (categoria_id, nome, preco)
VALUES
	(1, 'Cerveja Heineken - Long Neck 330ml', 3.30), 	#1
    (1, 'Refrigerante Dolly - Laranja 2l', 3), 			#2
    (1, 'Toddynho - Caixa 100ml', 4), 					#3
    (2, 'Pacote de Arroz - Camil 5Kg', 5), 				#4
    (2, 'Pacote de Feijão - Tio João 2Kg', 2.8),		#5
    (3, 'Alface americano - 1g', 0.05), 				#6
    (3, 'Repolho - 1g', 0.3);							#7
    
INSERT INTO
	venda (cliente_id)
VALUES
	(NULL), #1
    (NULL), #2
    (1), 	#3
    (1), 	#4
    (2); 	#5
    
INSERT INTO
    itens_venda (venda_id, produto_id, preco, qntd)
VALUES
	(1, 1, 3.30, 8),
    (1, 2, 3.30, 2), #Venda 1
    
    (2, 1, 4, 1), #Venda 2
    
    (3, 1, 3.30, 4), #Venda 3
    
    (4, 4, 5,  2),
	(4, 5, 2.8, 3), #Venda 4
    
    (5, 6, 0.05, 100),
	(5, 7, 0.3, 100) #Venda 15
    
    ;
