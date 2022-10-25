package com.adsn1.utils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adsn1.controllers.UsuarioController;
import com.adsn1.types.Usuario;

public class Auth {
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	private UsuarioController usuarioController;
	
	public Auth() {
		this.usuarioController = new UsuarioController();
	}
	
	public boolean validateEmail(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
	}
	
	public boolean login(Usuario usuarioLogin) throws Exception {
		Usuario usuario = usuarioController.getByEmail(usuarioLogin.getEmail());
		if (usuario != null && usuario.getSenha().equals(usuarioLogin.getSenha())) {
			usuario.setUltimo_login(new Date());
			usuarioController.save(usuario);
			return true;
		}
		throw new Exception("Usuário ou senha incorretos");
	}
}
