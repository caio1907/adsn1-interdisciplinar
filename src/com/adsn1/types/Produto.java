package com.adsn1.types;

public class Produto extends Common {
	private String descricao;
	private String codigo_de_barras;
	private long qtd_estoque;
	private double valor;

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCodigo_de_barras() {
		return codigo_de_barras;
	}
	public void setCodigo_de_barras(String codigo_de_barras) {
		this.codigo_de_barras = codigo_de_barras;
	}
	public long getQtd_estoque() {
		return qtd_estoque;
	}
	public void setQtd_estoque(long qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
}
