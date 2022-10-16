package com.adsn1.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.adsn1.types.Venda;
import com.adsn1.types.VendaItem;
import com.adsn1.utils.Database;
import com.adsn1.utils.Utils;

public class VendaController {
	private Database database;

	public VendaController() {
		this.database = new Database();
	}

	private ArrayList<Venda> getVendaByQuery(String where, String whereProds) {
		String condition = (where != null && !where.isEmpty()) ? ("where " + where) : "";
		ResultSet resultSet = this.database.executeSelect("SELECT"
				+ " v.id,"
				+ "	v.cliente,"
				+ "	v.vendedor,"
				+ "	v.total,"
				+ "	v.tipo_pagamento,"
				+ "	v.data_criacao,"
				+ "	tdp.descricao AS tipo_pagamento_descricao,"
				+ "	tdp.taxa,"
				+ " u.nome AS vendedor_nome"
				+ " FROM venda v"
				+ " INNER JOIN venda_item vi ON vi.id_venda = v.id"
				+ " INNER JOIN tipos_de_pagamento tdp ON tdp.id = v.tipo_pagamento"
				+ " INNER JOIN produto p ON p.id = vi.produto"
				+ " INNER JOIN usuario u ON u.id = v.vendedor " + condition + " ORDER BY v.id, vi.id");
		if (resultSet != null) {
			try {
				ArrayList<Venda> vendas = new ArrayList<Venda>();
				Venda venda;
				while (resultSet.next()) {
					venda = new Venda();
					venda.setId(resultSet.getLong("id"));
					venda.setCliente(resultSet.getLong("cliente"));
					venda.setVendedor(resultSet.getLong("vendedor"));
					venda.setVendedor_nome(resultSet.getString("vendedor_nome"));
					venda.setTotal(resultSet.getDouble("total"));
					venda.setTipo_pagamento(resultSet.getLong("tipo_pagamento"));
					venda.setTipo_pagamento_descricao(resultSet.getString("tipo_pagamento_descricao"));
					venda.setTipo_pagamento_taxa(resultSet.getDouble("taxa"));
					venda.setData_criacao(resultSet.getDate("data_criacao"));
					
					venda.setVendaItem(getItensVenda(venda.getId(), whereProds));

					vendas.add(venda);
				}
				return vendas;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private ArrayList<VendaItem> getItensVenda(long idVenda, String where) {
		ResultSet resultSet = this.database.executeSelect("SELECT"
				+ " vi.*,"
				+ " p.descricao AS produto_descricao"
				+ " FROM venda_item vi"
				+ " INNER JOIN produto p on p.id = vi.produto"
				+ " WHERE vi.id_venda = " + idVenda + (where != null ? (" AND " + where) : ""));
		if (resultSet != null) {
			ArrayList<VendaItem> vendaItem = new ArrayList<VendaItem>();
			try {
				VendaItem item;
				while (resultSet.next()) {
					item = new VendaItem();
					item.setId(resultSet.getLong("id"));
					item.setId_venda(idVenda);
					item.setProduto(resultSet.getLong("produto"));
					item.setProduto_descricao(resultSet.getString("produto_descricao"));
					item.setQuantidade(resultSet.getDouble("quantidade"));
					item.setValor_unitario(resultSet.getDouble("valor_unitario"));
					vendaItem.add(item);
				}
				return vendaItem;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Retorna todos as vendas
	 * @return
	 */
	public ArrayList<Venda> getAll(Date dataInicial, Date dataFinal, int produto, int vendedor) {
		ArrayList<String> query = new ArrayList<String>();
		String queryProduto = null;
		query.add("DATE(v.data_criacao) BETWEEN '" + Utils.formatDateToSql(dataInicial) + "' AND '" + Utils.formatDateToSql(dataFinal) + "'");
		if (produto != 0) {
			queryProduto = "p.id = " + produto;
		}
		if (vendedor != 0) {
			query.add("u.id = " + vendedor);
		}
		return this.getVendaByQuery(String.join(" AND ", query), queryProduto);
	}

	/**
	 * Buscar primeira venda por id
	 * 
	 * @param id
	 * @return
	 */
	public Venda getById(long id) {
		String condition = "v.id = " + id;
		ArrayList<Venda> vendas = this.getVendaByQuery(condition, null);
		return vendas.size() > 0 ? vendas.get(0) : null;
	}

	/**
	 * Buscar primeira venda por vendedor
	 * 
	 * @param email
	 * @return
	 */
	public Venda getByVendedor(Long vendedor) {
		String condition = "v.vendedor = '" + vendedor + "'";
		ArrayList<Venda> vendas = this.getVendaByQuery(condition, null);
		return vendas.size() > 0 ? vendas.get(0) : null;
	}

	/**
	 * Cria a venda
	 * 
	 * @param Venda
	 * @return
	 */
	public boolean save(Venda venda) {
		database.executeCUD("INSERT INTO venda ("
				+ "cliente, vendedor, total, tipo_pagamento) VALUES ("
				+ venda.getCliente()+", (SELECT id FROM usuario ORDER BY ultimo_login DESC LIMIT 1), "+venda.getTotal()+", "
				+ venda.getTipo_pagamento()
				+ ")");
		for (int i = 0; i < venda.getVendaItem().size(); i++) {
			VendaItem item = venda.getVendaItem().get(i);
			database.executeCUD("INSERT INTO venda_item (id_venda, produto, valor_unitario, quantidade) VALUES ("
					+ "(SELECT id FROM venda ORDER BY data_criacao DESC LIMIT 1), " + item.getProduto() + ", " + item.getValor_unitario() + ", "
					+ item.getQuantidade() + ")");
		}
		return true;
	}
}
