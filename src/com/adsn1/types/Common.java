package com.adsn1.types;

import java.util.Date;

public class Common {
	private long id;
	private Date data_criacao;
	private Date data_atualizacao;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getData_criacao() {
		return data_criacao;
	}
	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}
	public Date getData_atualizacao() {
		return data_atualizacao;
	}
	public void setData_atualizacao(Date data_atualizacao) {
		this.data_atualizacao = data_atualizacao;
	}
}
