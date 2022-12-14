package com.adsn1.types;

import java.util.Date;

public class Usuario extends Common {
	private String nome;
	private String email;
	private String senha;
	private Date ultimo_login;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Date getUltimo_login() {
		return ultimo_login;
	}
	public void setUltimo_login(Date ultimo_login) {
		this.ultimo_login = ultimo_login;
	}
}
