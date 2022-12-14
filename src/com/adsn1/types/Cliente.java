package com.adsn1.types;

import java.util.Date;

public class Cliente extends Common {
	private String nome;
	private String email;
	private Date datanascimento;
	private String telefone;
	private String end_logradouro;
	private String end_complemento;
	private String end_bairro;
	private String end_cidade;
	private String end_uf;

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
	public Date getDatanascimento() {
		return datanascimento;
	}
	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEnd_logradouro() {
		return end_logradouro;
	}
	public void setEnd_logradouro(String end_logradouro) {
		this.end_logradouro = end_logradouro;
	}
	public String getEnd_complemento() {
		return end_complemento;
	}
	public void setEnd_complemento(String end_complemento) {
		this.end_complemento = end_complemento;
	}
	public String getEnd_bairro() {
		return end_bairro;
	}
	public void setEnd_bairro(String end_bairro) {
		this.end_bairro = end_bairro;
	}
	public String getEnd_cidade() {
		return end_cidade;
	}
	public void setEnd_cidade(String end_cidade) {
		this.end_cidade = end_cidade;
	}
	public String getEnd_uf() {
		return end_uf;
	}
	public void setEnd_uf(String end_uf) {
		this.end_uf = end_uf;
	}
}
