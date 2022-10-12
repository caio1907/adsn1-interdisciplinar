package com.adsn1.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.adsn1.types.Usuario;
import com.adsn1.utils.Database;
import com.adsn1.utils.Utils;

public class UsuarioController {
	private Database database;

	public UsuarioController() {
		this.database = new Database();
	}
	
	private ArrayList<Usuario> getUsuarioByQuery(String where) {
		String condition = (where != null && !where.isEmpty()) ? ("where " + where) : "";
		ResultSet resultSet = this.database.executeSelect("SELECT * FROM usuario " + condition);
		if (resultSet != null) {
			try {
				ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
				Usuario usuario;
				while (resultSet.next()) {
					usuario = new Usuario();
					usuario.setId(resultSet.getLong("id"));
					usuario.setNome(resultSet.getString("nome"));
					usuario.setEmail(resultSet.getString("email"));
					usuario.setSenha(resultSet.getString("senha"));
					usuario.setUltimo_login(resultSet.getDate("ultimo_login"));
					usuario.setData_criacao(resultSet.getDate("data_criacao"));
					usuario.setData_atualizacao(resultSet.getDate("data_atualizacao"));
					usuarios.add(usuario);
				}
				return usuarios;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Retorna todos os usuarios
	 * @return
	 */
	public ArrayList<Usuario> getAll() {
		return this.getUsuarioByQuery(null);
	}

	/**
	 * Buscar primeiro usuario por id
	 * 
	 * @param id
	 * @return
	 */
	public Usuario getById(long id) {
		String condition = "id = " + id;
		ArrayList<Usuario> usuarios = this.getUsuarioByQuery(condition);
		return usuarios.size() > 0 ? usuarios.get(0) : null;
	}
	
	/**
	 * Buscar primeiro usuario por email
	 * 
	 * @param email
	 * @return
	 */
	public Usuario getByEmail(String email) {
		String condition = "email = '"+email+"'";
		ArrayList<Usuario> usuarios = this.getUsuarioByQuery(condition);
		return usuarios.size() > 0 ? usuarios.get(0) : null;
	}
	
	/**
	 * Cria ou atualiza o usuario
	 * 
	 * @param Usuario
	 * @return
	 */
	public Usuario save(Usuario usuario) {
		Long id = usuario.getId();
		if (id != null && getById(usuario.getId()) != null) {
			database.executeCUD("UPDATE usuario SET "
					+ "nome = '"+usuario.getNome()+"',"
					+ "email = '"+usuario.getEmail()+"',"
					+ "senha = '"+usuario.getSenha()+"',"
					+ "ultimo_login = '"+Utils.formatDateToSql(usuario.getUltimo_login(), true)+"',"
					+ "data_atualizacao = '"+Utils.formatDateToSql(new Date(), true)+"' "
					+ "WHERE id = " + id);
			return this.getById(id);
		} else {
			database.executeCUD("INSERT INTO usuario ("
					+ "nome, email, senha) VALUES ("
					+ "'"+usuario.getNome()+"', '"+usuario.getEmail()+"',"
					+ "'"+usuario.getSenha()+"', '"+usuario.getUltimo_login()+"'"
					+ ")");
			return this.getByEmail(usuario.getEmail());
		}
	}
}
