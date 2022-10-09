package com.adsn1.types;

public class Venda extends Common {
	private long cliente;
	private long vendedor;
	private double total;
	private long tipo_pagamento;
	private VendaItem[] vendaItem;

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
	public VendaItem[] getVendaItem() {
		return vendaItem;
	}
	public void setVendaItem(VendaItem[] vendaItem) {
		this.vendaItem = vendaItem;
	}
}
