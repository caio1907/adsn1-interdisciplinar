package com.adsn1.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private Connection connection;
	
	private String databaseName = "database.sqlite";
	
	public Database() {
		setConnection();
	}
	
	private String getDatabaseFilePath() {
		return System.getProperty("user.dir") + System.getProperty("file.separator") + databaseName;
	}
	
    private Connection setConnection() {
    	if (connection != null) {
    		return connection;
    	}
        try {
        	String databaseFilePath = getDatabaseFilePath();
            String url = "jdbc:sqlite:"+databaseFilePath;
            
            System.out.println("Path do banco de dados: " + url);
            
            
            File file = new File(databaseFilePath);
            boolean databaseHasExists = file.exists();
            
            connection = DriverManager.getConnection(url);
            
            if (!databaseHasExists) {
            	new MigrationDB(this);
            	executeCUD("INSERT INTO users (nome, email, senha) VALUES ('Admin', 'admin@email.com', '123456')");
            }
            
            System.out.println("Conexão com o banco de dados realizada com sucesso!");
        } catch (SQLException exception) {
            System.out.println("Erro ao conectar com o banco de dados.");
            System.out.println(exception.getMessage());
        }
        return connection;
    }
    
    /**
     * Execute SELECT
     * 
     * @param query
     * @return ResultSet
     */
    public ResultSet executeSelect(String query) {
//    	setConnection();
    	try {
    		PreparedStatement statement = connection.prepareStatement(query);
    		ResultSet resultSet = statement.executeQuery();
//    		connection.close();
    		return resultSet;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /**
     * Execute INSERT, UPDATE and DELETE
     * 
     * @param query
     * @return boolean
     */
    public boolean executeCUD(String query) {
//    	setConnection();
    	try {
			Statement statement = connection.createStatement();
			statement.execute(query);
//			connection.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    }
}
