package com.delivery.service;

import java.util.List;

import com.delivery.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);
	
	List<Usuario> listarUSuarios();
}
