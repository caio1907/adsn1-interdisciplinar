package com.adsn1.types;

public class VendaItem extends Common {
	private long id_venda;
	private long produto;
	private String produto_descricao;
	private double valor_unitario;
	private double quantidade;
	
	public long getId_venda() {
		return id_venda;
	}
	public void setId_venda(long id_venda) {
		this.id_venda = id_venda;
	}
	public long getProduto() {
		return produto;
	}
	public void setProduto(long produto) {
		this.produto = produto;
	}
	public String getProduto_descricao() {
		return produto_descricao;
	}
	public void setProduto_descricao(String produto_descricao) {
		this.produto_descricao = produto_descricao;
	}
	public double getValor_unitario() {
		return valor_unitario;
	}
	public void setValor_unitario(double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
}
