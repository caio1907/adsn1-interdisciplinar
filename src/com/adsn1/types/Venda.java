package com.adsn1.types;

import java.util.ArrayList;

public class Venda extends Common {
	private long cliente;
	private long vendedor;
	private String vendedor_nome;
	private double total;
	private long tipo_pagamento;
	private String tipo_pagamento_descricao;
	private double tipo_pagamento_taxa;
	private ArrayList<VendaItem> vendaItem;

	public long getCliente() {
		return cliente;
	}
	public void setCliente(long cliente) {
		this.cliente = cliente;
	}
	public long getVendedor() {
		return vendedor;
	}
	public void setVendedor(long vendedor) {
		this.vendedor = vendedor;
	}
	public String getVendedor_nome() {
		return vendedor_nome;
	}
	public void setVendedor_nome(String vendedor_nome) {
		this.vendedor_nome = vendedor_nome;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public long getTipo_pagamento() {
		return tipo_pagamento;
	}
	public void setTipo_pagamento(long tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
	}
	public String getTipo_pagamento_descricao() {
		return tipo_pagamento_descricao;
	}
	public void setTipo_pagamento_descricao(String tipo_pagamento_descricao) {
		this.tipo_pagamento_descricao = tipo_pagamento_descricao;
	}
	public double getTipo_pagamento_taxa() {
		return tipo_pagamento_taxa;
	}
	public void setTipo_pagamento_taxa(double tipo_pagamento_taxa) {
		this.tipo_pagamento_taxa = tipo_pagamento_taxa;
	}
	public ArrayList<VendaItem> getVendaItem() {
		return vendaItem;
	}
	public void setVendaItem(ArrayList<VendaItem> vendaItem) {
		this.vendaItem = vendaItem;
	}
}
