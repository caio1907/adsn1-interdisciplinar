package com.adsn1.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Auth {
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	private Database database;
	
	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean validateEmail() {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(getEmail());
        return matcher.find();
	}
	
	public boolean login() throws Exception {
		if (database == null) {
			database = new Database();
		}
		try {
			ResultSet resultSet = database.executeSelect("SELECT * FROM users WHERE email = '" + getEmail() + "'");
			while (resultSet.next()) {
				String password = resultSet.getString("senha");
				if (password.equals(getPassword())) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Falha ao realizar o login.");
			e.printStackTrace();
		}
		throw new Exception("Usuário ou senha incorretos");
	}
}
