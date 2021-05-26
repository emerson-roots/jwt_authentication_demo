package demo.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.domain.Usuario;
import demo.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Usuario usr1 = new Usuario(null, "Emerson", "emerson@emerson", "123");
		Usuario usr2 = new Usuario(null, "Jurema", "jurema@jurema", "321");
		Usuario usr3 = new Usuario(null, "Skarlath", "skarlath@skarlath", "1364");

		// sempre que for chamar um repository para salvar uma LISTA de dados
		// usar saveAll devido a versão do Spring Boot 2.x.x:
		usuarioRepository.saveAll(Arrays.asList(usr1, usr2, usr3));
	}
	
}