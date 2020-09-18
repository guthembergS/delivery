package com.delivery.modelsrepository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.delivery.model.entity.Usuario;
import com.delivery.model.repository.UsuarioRepository;
import com.delivery.service.UsuarioService;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void testEmailExists() {
		Usuario usuario = Usuario.builder().nome("Guthemberg").email("guthemberg@mail.com").build();
		entityManager.persist(usuario); //repository.save(usuario);
		
		boolean existe = repository.existsByEmail("guthemberg@mail.com");
		Assertions.assertThat(existe).isTrue();
		
	}
	
	@Test
	public void validarEmailExistente() {
		boolean result = repository.existsByEmail("guthemberg@mail.com");
		Assertions.assertThat(result).isFalse();
	}
	
	@Test
	public void testarSalvarUSuario() {
		Usuario usuario = Usuario.builder().nome("Guthemberg").email("guthemberg@mail.com").senha("123456").build();
		Usuario usuSalvo = repository.save(usuario);
		Assertions.assertThat(usuSalvo.getIdUsuario()).isNotNull();
	}
	
	
	
}
