package com.delivery.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.api.dto.UsuarioDTO;
import com.delivery.exeption.ErroAutenticacao;
import com.delivery.exeption.RegraNegoxioExeption;
import com.delivery.model.entity.Usuario;
import com.delivery.service.UsuarioService;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioResource {


	private UsuarioService service;

	public UsuarioResource(UsuarioService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
		
		Usuario usuario = Usuario.builder().nome(dto.getNome()).email(dto.getEmail()).senha(dto.getSenha()).build();
		
		try {
			service.validarEmail(dto.getEmail());
			Usuario usuSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity(usuSalvo, HttpStatus.CREATED);
		} catch (RegraNegoxioExeption e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
		
		
		try {
			
			Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
			return new ResponseEntity(usuarioAutenticado,HttpStatus.OK);
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	
}
