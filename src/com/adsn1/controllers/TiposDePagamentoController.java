package com.adsn1.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.adsn1.types.TiposDePagamento;
import com.adsn1.utils.Database;

public class TiposDePagamentoController {
	private Database database;

	public TiposDePagamentoController() {
		this.database = new Database();
	}
	
	private ArrayList<TiposDePagamento> getTiposDePagamentoByQuery(String where) {
		String condition = (where != null && !where.isEmpty()) ? ("where " + where) : "";
		ResultSet resultSet = this.database.executeSelect("SELECT * FROM tipos_de_pagamento " + condition);
		if (resultSet != null) {
			try {
				ArrayList<TiposDePagamento> tiposDePagamento = new ArrayList<TiposDePagamento>();
				TiposDePagamento tipoDePagamento;
				while (resultSet.next()) {
					tipoDePagamento = new TiposDePagamento();
					tipoDePagamento.setId(resultSet.getLong("id"));
					tipoDePagamento.setDescricao(resultSet.getString("descricao"));
					tipoDePagamento.setTaxa(resultSet.getDouble("taxa"));
					tipoDePagamento.setData_criacao(resultSet.getDate("data_criacao"));
					tipoDePagamento.setData_atualizacao(resultSet.getDate("data_atualizacao"));
					tiposDePagamento.add(tipoDePagamento);
				}
				return tiposDePagamento;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Retorna todos os tipos de pagamento
	 * @return
	 */
	public ArrayList<TiposDePagamento> getAll() {
		return this.getTiposDePagamentoByQuery(null);
	}

	/**
	 * Buscar primeiro tipo de pagamento por id
	 * 
	 * @param id
	 * @return
	 */
	public TiposDePagamento getById(long id) {
		String condition = "id = " + id;
		ArrayList<TiposDePagamento> tiposDePagamento = this.getTiposDePagamentoByQuery(condition);
		return tiposDePagamento.size() > 0 ? tiposDePagamento.get(0) : null;
	}
	
	/**
	 * Buscar primeiro tipo de pagamento por descrição
	 * 
	 * @param email
	 * @return
	 */
	public TiposDePagamento getByDescricao(String descricao) {
		String condition = "descricao = '" + descricao + "'";
		ArrayList<TiposDePagamento> tiposDePagamento = this.getTiposDePagamentoByQuery(condition);
		return tiposDePagamento.size() > 0 ? tiposDePagamento.get(0) : null;
	}

	/**
	 * Cria ou atualiza o tipo de pagamento
	 * 
	 * @param TiposDePagamento
	 * @return
	 */
	public TiposDePagamento save(TiposDePagamento tipoDePagamento) {
		Long id = tipoDePagamento.getId();
		if (id != null && getById(tipoDePagamento.getId()) != null) {
			database.executeCUD("UPDATE tipos_de_pagamento SET "
					+ "descricao = '"+tipoDePagamento.getDescricao()+"',"
					+ "taxa = "+tipoDePagamento.getTaxa() + ", "
					+ "data_atualizacao = CURRENT_TIMESTAMP "
					+ "WHERE id = " + id);
			return this.getById(id);
		} else {
			database.executeCUD("INSERT INTO tipos_de_pagamento ("
					+ "descricao, taxa) VALUES ("
					+ "'"+tipoDePagamento.getDescricao()+"', "+tipoDePagamento.getTaxa()
					+ ")");
			return this.getByDescricao(tipoDePagamento.getDescricao());
		}
	}
}
