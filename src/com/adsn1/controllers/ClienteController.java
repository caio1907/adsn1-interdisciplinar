package com.adsn1.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.adsn1.types.Cliente;
import com.adsn1.utils.Database;
import com.adsn1.utils.Utils;

public class ClienteController {
	private Database database;

	public ClienteController() {
		this.database = new Database();
	}
	
	private ArrayList<Cliente> getClienteByQuery(String where) {
		String condition = (where != null && !where.isEmpty()) ? ("where " + where) : "";
		ResultSet resultSet = this.database.executeSelect("SELECT * FROM cliente " + condition);
		if (resultSet != null) {
			try {
				ArrayList<Cliente> clientes = new ArrayList<Cliente>();
				Cliente cliente;
				while (resultSet.next()) {
					cliente = new Cliente();
					cliente.setId(resultSet.getLong("id"));
					cliente.setNome(resultSet.getString("nome"));
					cliente.setEmail(resultSet.getString("email"));
					cliente.setTelefone(resultSet.getString("telefone"));
					cliente.setDatanascimento(Utils.formatStringToDate(resultSet.getString("datanascimento")));
					cliente.setEnd_logradouro(resultSet.getString("end_logradouro"));
					cliente.setEnd_bairro(resultSet.getString("end_bairro"));
					cliente.setEnd_complemento(resultSet.getString("end_complemento"));
					cliente.setEnd_cidade(resultSet.getString("end_cidade"));
					cliente.setEnd_uf(resultSet.getString("end_uf"));
					cliente.setData_criacao(resultSet.getDate("data_criacao"));
					cliente.setData_atualizacao(resultSet.getDate("data_atualizacao"));
					clientes.add(cliente);
				}
				return clientes;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Retorna todos os clientes
	 * @return
	 */
	public ArrayList<Cliente> getAll() {
		return this.getClienteByQuery(null);
	}

	/**
	 * Buscar primeiro cliente por id
	 * 
	 * @param id
	 * @return
	 */
	public Cliente getById(long id) {
		String condition = "id = " + id;
		ArrayList<Cliente> clientes = this.getClienteByQuery(condition);
		return clientes.size() > 0 ? clientes.get(0) : null;
	}
	
	/**
	 * Buscar primeiro cliente por e-mail
	 * 
	 * @param email
	 * @return
	 */
	public Cliente getByEmail(String email) {
		String condition = "email = '" + email + "'";
		ArrayList<Cliente> clientes = this.getClienteByQuery(condition);
		return clientes.size() > 0 ? clientes.get(0) : null;
	}

	/**
	 * Cria ou atualiza o cliente
	 * 
	 * @param Cliente
	 * @return
	 */
	public Cliente save(Cliente cliente) {
		Long id = cliente.getId();
		if (id != null && getById(cliente.getId()) != null) {
			database.executeCUD("UPDATE cliente SET "
					+ "nome = '"+cliente.getNome()+"',"
					+ "email = '"+cliente.getEmail()+"',"
					+ "telefone = '"+cliente.getTelefone()+"',"
					+ "datanascimento = '"+Utils.formatDateToSql(cliente.getDatanascimento())+"',"
					+ "end_logradouro = '"+cliente.getEnd_logradouro()+"',"
					+ "end_bairro = '"+cliente.getEnd_bairro()+"',"
					+ "end_complemento = '"+cliente.getEnd_complemento()+"',"
					+ "end_cidade = '"+cliente.getEnd_cidade()+"',"
					+ "end_uf = '"+cliente.getEnd_uf()+"',"
					+ "data_atualizacao = CURRENT_TIMESTAMP "
					+ "WHERE id = " + id);
			return this.getById(id);
		} else {
			database.executeCUD("INSERT INTO cliente ("
					+ "nome, email, datanascimento, telefone, end_logradouro, end_complemento,"
					+ "end_bairro, end_cidade, end_uf) VALUES ("
					+ "'"+cliente.getNome()+"', '"+cliente.getEmail()+"',"
					+ "'"+Utils.formatDateToSql(cliente.getDatanascimento())+"',"
					+ "'"+cliente.getTelefone()+"', '"+cliente.getEnd_logradouro()+"',"
					+ "'"+cliente.getEnd_complemento()+"', '"+cliente.getEnd_bairro()+"',"
					+ "'"+cliente.getEnd_cidade()+"', '"+cliente.getEnd_uf()+"'"
					+ ")");
			return this.getByEmail(cliente.getEmail());
		}
	}
}
