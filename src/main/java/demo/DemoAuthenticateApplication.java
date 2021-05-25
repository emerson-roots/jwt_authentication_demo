package demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import demo.domain.Usuario;
import demo.repositories.UsuarioRepository;

@SpringBootApplication
public class DemoAuthenticateApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoAuthenticateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario usr1 = new Usuario(null, "Emerson", "emerson@emerson", "123");
		Usuario usr2 = new Usuario(null, "Jurema", "jurema@jurema", "321");
		Usuario usr3 = new Usuario(null, "Skarlath", "skarlath@skarlath", "1364");

		// sempre que for chamar um repository para salvar uma LISTA de dados
		// usar saveAll devido a versão do Spring Boot 2.x.x:
		usuarioRepository.saveAll(Arrays.asList(usr1, usr2, usr3));

	}

}
