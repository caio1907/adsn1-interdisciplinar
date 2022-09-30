package com.adsn1.utils;

public class MigrationDB {
	private Database database;
	
	public MigrationDB(Database database) {
		this.database = database;
		createTables();
	}
	
	private void createTables() {
		database.executeCUD("CREATE TABLE usuario ("
				+ "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "nome VARCHAR(50) NOT NULL,"
				+ "email VARCHAR(50) NOT NULL,"
				+ "senha VARCHAR(50) NOT NULL"
				+ ");");
		database.executeCUD("CREATE TABLE cliente ("
				+ "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "nome VARCHAR(50) NOT NULL,"
				+ "email VARCHAR(50) NOT NULL,"
				+ "datanascimento DATE NOT NULL,"
				+ "telefone VARCHAR(11) NOT NULL,"
				+ "end_logradouro VARCHAR(50) NOT NULL,"
				+ "end_complemento VARCHAR(50),"
				+ "end_bairro VARCHAR(50) NOT NULL,"
				+ "end_cidade VARCHAR(50) NOT NULL,"
				+ "end_uf VARCHAR(2) NOT NULL,"
				+ "data_criacao TIMESTAMP NOT NULL,"
				+ "data_atualizacao TIMESTAMP NOT NULL"
				+ ");");
		database.executeCUD("CREATE TABLE produto ("
				+ "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "descricao VARCHAR(50) NOT NULL,"
				+ "codigo_de_barras VARCHAR(20) NOT NULL,"
				+ "qtd_estoque INTEGER NOT NULL,"
				+ "valor DECIMAL NOT NULL,"
				+ "data_criacao TIMESTAMP NOT NULL,"
				+ "data_atualizacao TIMESTAMP NOT NULL"
				+ ");");
		database.executeCUD("CREATE TABLE tipo_de_pagamento ("
				+ "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "descricao VARCHAR(50) NOT NULL,"
				+ "taxa DECIMAL NOT NULL,"
				+ "data_criacao TIMESTAMP NOT NULL,"
				+ "data_atualizacao TIMESTAMP NOT NULL"
				+ ");");
		database.executeCUD("CREATE TABLE venda ("
				+ "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "cliente INTEGER NOT NULL,"
				+ "total DECIMAL NOT NULL,"
				+ "tipo_pagamento INTEGER NOT NULL,"
				+ "data_criacao TIMESTAMP NOT NULL,"
				+ "data_atualizacao TIMESTAMP NOT NULL,"
				+ "FOREIGN KEY (cliente) REFERENCES cliente(id),"
				+ "FOREIGN KEY (tipo_pagamento) REFERENCES tipo_de_pagamento(id)"
				+ ");");
		database.executeCUD("CREATE TABLE venda_item ("
				+ "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "id_venda INTEGER NOT NULL,"
				+ "produto INTEGER NOT NULL,"
				+ "valor_unitario DECIMAL NOT NULL,"
				+ "quantidade INTEGER NOT NULL,"
				+ "data_criacao TIMESTAMP NOT NULL,"
				+ "data_atualizacao TIMESTAMP NOT NULL,"
				+ "FOREIGN KEY (id_venda) REFERENCES venda(id),"
				+ "FOREIGN KEY (produto) REFERENCES produto(id)"
				+ ");");
	}
}
