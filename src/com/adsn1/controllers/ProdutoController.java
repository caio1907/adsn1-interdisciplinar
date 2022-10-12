package com.adsn1.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.adsn1.types.Produto;
import com.adsn1.utils.Database;

public class ProdutoController {
	private Database database;

	public ProdutoController() {
		this.database = new Database();
	}
	
	private ArrayList<Produto> getProdutoByQuery(String where) {
		String condition = (where != null && !where.isEmpty()) ? ("where " + where) : "";
		ResultSet resultSet = this.database.executeSelect("SELECT * FROM produto " + condition);
		if (resultSet != null) {
			try {
				ArrayList<Produto> produtos = new ArrayList<Produto>();
				Produto produto;
				while (resultSet.next()) {
					produto = new Produto();
					produto.setId(resultSet.getLong("id"));
					produto.setDescricao(resultSet.getString("descricao"));
					produto.setCodigo_de_barras(resultSet.getString("codigo_de_barras"));
					produto.setQtd_estoque(resultSet.getLong("qtd_estoque"));
					produto.setValor(resultSet.getDouble("valor"));
					produto.setData_criacao(resultSet.getDate("data_criacao"));
					produto.setData_atualizacao(resultSet.getDate("data_atualizacao"));
					produtos.add(produto);
				}
				return produtos;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Retorna todos os produtos
	 * @return
	 */
	public ArrayList<Produto> getAll() {
		return this.getProdutoByQuery(null);
	}

	/**
	 * Buscar primeiro produto por id
	 * 
	 * @param id
	 * @return
	 */
	public Produto getById(long id) {
		String condition = "id = " + id;
		ArrayList<Produto> produtos = this.getProdutoByQuery(condition);
		return produtos.size() > 0 ? produtos.get(0) : null;
	}
	
	/**
	 * Buscar primeiro produto por código de barras
	 * 
	 * @param codigoDeBarras
	 * @return
	 */
	public Produto getByCodigoDeBarras(String codigoDeBarras) {
		String condition = "codigo_de_barras = '"+codigoDeBarras+"'";
		ArrayList<Produto> produtos = this.getProdutoByQuery(condition);
		return produtos.size() > 0 ? produtos.get(0) : null;
	}
	
	/**
	 * Cria ou atualiza o produto
	 * 
	 * @param Produto
	 * @return
	 */
	public Produto save(Produto produto) {
		Long id = produto.getId();
		if (id != null && getById(produto.getId()) != null) {
			database.executeCUD("UPDATE produto SET "
					+ "descricao = '"+produto.getDescricao()+"',"
					+ "codigo_de_barras = '"+produto.getCodigo_de_barras()+"',"
					+ "qtd_estoque = "+produto.getQtd_estoque()+","
					+ "valor = "+produto.getValor()+","
					+ "data_atualizacao = CURRENT_TIMESTAMP "
					+ "WHERE id = " + id);
			return this.getById(id);
		} else {
			database.executeCUD("INSERT INTO produto ("
					+ "descricao, codigo_de_barras, qtd_estoque, valor) VALUES ("
					+ "'"+produto.getDescricao()+"', '"+produto.getCodigo_de_barras()+"',"
					+ produto.getQtd_estoque()+", "+produto.getValor()
					+ ")");
			return this.getByCodigoDeBarras(produto.getCodigo_de_barras());
		}
	}
}
