package com.delivery.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.exeption.ErroAutenticacao;
import com.delivery.exeption.RegraNegoxioExeption;
import com.delivery.model.entity.Usuario;
import com.delivery.model.repository.UsuarioRepository;
import com.delivery.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository repository;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public Usuario autenticar(String email, String senha) {

		Optional<Usuario> usuLog = repository.findByEmail(email);
		if(!usuLog.isPresent()) {
			throw new ErroAutenticacao("Email inválido!");
		} 
		if(!usuLog.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida!");
		}
		
		return usuLog.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		
		boolean existeEmail = repository.existsByEmail(email); 

		if(existeEmail) {
			throw new RegraNegoxioExeption("Email já utilizado por outro usuário!");
		}
		
	}

}
