package com.adsn1.utils;

public class SeedsDB {
	private Database database;
	
	public SeedsDB(Database database) {
		this.database = database;
		insertSeeds();
	}
	
	private void insertSeeds() {
		// Usuario
		database.executeCUD("INSERT INTO usuario (nome, email, senha) VALUES ('Suporte', 'admin@email.com', '123456')");
		
		if (!Utils.DEVELOP_MODE.equals("development")) {
			return;
		}
		// Clientes
		database.executeCUD("INSERT INTO cliente ("
				+ "nome, email, datanascimento, telefone, end_logradouro, end_complemento,"
				+ "end_bairro, end_cidade, end_uf, data_criacao, data_atualizacao) VALUES ("
				+ "'João', 'joaozinho@email.com', '1990-05-05', '81987654321', 'Rua dos testes, 35', '', 'Boa Vista', 'Recife', 'PE',"
				+ "'2022-09-29 22:33:05', '2022-09-29 22:33:05'"
				+ ");");
		database.executeCUD("INSERT INTO cliente ("
				+ "nome, email, datanascimento, telefone, end_logradouro, end_complemento,"
				+ "end_bairro, end_cidade, end_uf, data_criacao, data_atualizacao) VALUES ("
				+ "'Maria', 'maiazinha@email.com', '1985-05-05', '81912345678', 'Rua dos devs, 255', 'Bloco A Ap 198', 'Peixinhos', 'Olinda', 'PE',"
				+ "'2022-09-29 22:45:15', '2022-09-29 22:45:15'"
				+ ");");
		
		// Tipos de pagamento
		database.executeCUD("INSERT INTO tipos_de_pagamento (descricao, taxa, data_criacao, data_atualizacao) VALUES ("
				+ "'Pix', 0, '2022-09-29 22:33:05', '2022-09-29 22:33:05')");
		database.executeCUD("INSERT INTO tipos_de_pagamento (descricao, taxa, data_criacao, data_atualizacao) VALUES ("
				+ "'Dinheiro', 0, '2022-09-29 22:33:05', '2022-09-29 22:33:05')");
		database.executeCUD("INSERT INTO tipos_de_pagamento (descricao, taxa, data_criacao, data_atualizacao) VALUES ("
				+ "'Cartão Mastercard - Débito', 1.2, '2022-09-29 22:33:05', '2022-09-29 22:33:05')");
		database.executeCUD("INSERT INTO tipos_de_pagamento (descricao, taxa, data_criacao, data_atualizacao) VALUES ("
				+ "'Cartão Mastercard - Crédito', 1.85, '2022-09-29 22:33:05', '2022-09-29 22:33:05')");
		database.executeCUD("INSERT INTO tipos_de_pagamento (descricao, taxa, data_criacao, data_atualizacao) VALUES ("
				+ "'Cartão Visa - Débito', 1.5, '2022-09-29 22:33:05', '2022-09-29 22:33:05')");
		database.executeCUD("INSERT INTO tipos_de_pagamento (descricao, taxa, data_criacao, data_atualizacao) VALUES ("
				+ "'Cartão Visa - Crédito', 2.2, '2022-09-29 22:33:05', '2022-09-29 22:33:05')");
		
		// Produtos
		database.executeCUD("INSERT INTO produto (descricao, codigo_de_barras, qtd_estoque, valor, data_criacao, data_atualizacao) VALUES ("
				+ "'Croped branca - Cyclone', '78954645', 14, 34.99, '2022-09-29 22:33:05', '2022-09-29 22:33:05');");
		database.executeCUD("INSERT INTO produto (descricao, codigo_de_barras, qtd_estoque, valor, data_criacao, data_atualizacao) VALUES ("
				+ "'Short Jeans rasgado - Brytch', '789454542', 8, 89.90, '2022-09-29 22:33:05', '2022-09-29 22:33:05');");
		database.executeCUD("INSERT INTO produto (descricao, codigo_de_barras, qtd_estoque, valor, data_criacao, data_atualizacao) VALUES ("
				+ "'Top vermelho - Colcci', '78945465', 5, 19.90, '2022-09-29 22:33:05', '2022-09-29 22:33:05');");
		
		// Vendas
		database.executeCUD("INSERT INTO venda (cliente, total, vendedor, tipo_pagamento, data_criacao, data_atualizacao) VALUES ("
				+ "1, 69.98, 1, 4, '2022-09-29 22:33:05', '2022-09-29 22:33:05');");
		database.executeCUD("INSERT INTO venda (cliente, total, vendedor, tipo_pagamento, data_criacao, data_atualizacao) VALUES ("
				+ "2, 19.90, 1, 1, '2022-09-29 22:33:05', '2022-09-29 22:33:05');");
		database.executeCUD("INSERT INTO venda (cliente, total, vendedor, tipo_pagamento, data_criacao, data_atualizacao) VALUES ("
				+ "2, 124.89, 1, 2, '2022-09-29 22:33:05', '2022-09-29 22:33:05');");
		
		// Vendas - Itens
		database.executeCUD("INSERT INTO venda_item (id_venda, produto, valor_unitario, quantidade, data_criacao, data_atualizacao) VALUES ("
				+ "1, 1, 34.99, 2, '2022-09-29 22:33:05', '2022-09-29 22:33:05')");
		database.executeCUD("INSERT INTO venda_item (id_venda, produto, valor_unitario, quantidade, data_criacao, data_atualizacao) VALUES ("
				+ "2, 3, 19.90, 1, '2022-09-29 22:33:05', '2022-09-29 22:33:05')");
		database.executeCUD("INSERT INTO venda_item (id_venda, produto, valor_unitario, quantidade, data_criacao, data_atualizacao) VALUES ("
				+ "3, 1, 34.99, 1, '2022-09-29 22:33:05', '2022-09-29 22:33:05')");
		database.executeCUD("INSERT INTO venda_item (id_venda, produto, valor_unitario, quantidade, data_criacao, data_atualizacao) VALUES ("
				+ "3, 2, 89.90, 1, '2022-09-29 22:33:05', '2022-09-29 22:33:05')");
	}
}
