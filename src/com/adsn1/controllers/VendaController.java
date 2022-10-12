package com.adsn1.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.adsn1.types.Venda;
import com.adsn1.types.VendaItem;
import com.adsn1.utils.Database;

public class VendaController {
	private Database database;

	public VendaController() {
		this.database = new Database();
	}

	private ArrayList<Venda> getVendaByQuery(String where) {
		String condition = (where != null && !where.isEmpty()) ? ("where " + where) : "";
		ResultSet resultSet = this.database.executeSelect("SELECT * FROM venda " + condition);
		if (resultSet != null) {
			try {
				ArrayList<Venda> vendas = new ArrayList<Venda>();
				Venda venda;
				while (resultSet.next()) {
					venda = new Venda();
					venda.setId(resultSet.getLong("id"));
					venda.setCliente(resultSet.getLong("cliente"));
					venda.setVendedor(resultSet.getLong("vendedor"));
					venda.setTotal(resultSet.getDouble("total"));
					venda.setTipo_pagamento(resultSet.getLong("tipo_pagamento"));
					venda.setData_criacao(resultSet.getDate("data_criacao"));
					venda.setData_atualizacao(resultSet.getDate("data_atualizacao"));
					vendas.add(venda);
				}
				return vendas;
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
	public ArrayList<Venda> getAll() {
		return this.getVendaByQuery(null);
	}

	/**
	 * Buscar primeira venda por id
	 * 
	 * @param id
	 * @return
	 */
	public Venda getById(long id) {
		String condition = "id = " + id;
		ArrayList<Venda> vendas = this.getVendaByQuery(condition);
		return vendas.size() > 0 ? vendas.get(0) : null;
	}

	/**
	 * Buscar primeira venda por vendedor
	 * 
	 * @param email
	 * @return
	 */
	public Venda getByVendedor(Long vendedor) {
		String condition = "vendedor = '" + vendedor + "'";
		ArrayList<Venda> vendas = this.getVendaByQuery(condition);
		return vendas.size() > 0 ? vendas.get(0) : null;
	}

	/**
	 * Cria ou atualiza o venda
	 * 
	 * @param Venda
	 * @return
	 */
	public boolean save(Venda venda, VendaItem[] vendaItem) {
		database.executeCUD("INSERT INTO venda ("
				+ "cliente, vendedor, total, tipo_pagamento) VALUES ("
				+ ""+venda.getCliente()+", (SELECT id FROM usuario ORDER BY ultimo_login DESC LIMIT 1), "+venda.getTotal()+","
				+ venda.getTipo_pagamento()
				+ ")");
		for (int i = 0; i < vendaItem.length; i++) {
			VendaItem item = vendaItem[i];
			database.executeCUD("INSERT INTO venda_item (id_venda, produto, valor_unitario, quantidade) VALUES ("
					+ item.getId_venda() + ", " + item.getProduto() + ", " + item.getValor_unitario() + ", "
					+ item.getQtd_estoque() + ")");
		}
		return true;
	}
}
