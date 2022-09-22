package com.adsn1.utils;

public class MigrationDB {
	private Database database;
	
	public MigrationDB(Database database) {
		this.database = database;
		createTables();
	}
	
	private void createTables() {
		database.executeCUD("CREATE TABLE users ("
				+ "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "nome VARCHAR(50) NOT NULL,"
				+ "email VARCHAR(50) NOT NULL,"
				+ "senha VARCHAR(50) NOT NULL"
				+ ");");
	}
}
