package demo.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.domain.Usuario;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<Usuario> listar() {
		
		Usuario usuario1 = new Usuario(1l, "Emerson", "Emerson@sardinha", "123");
		Usuario usuario2 = new Usuario(2l, "Joanete", "Joanete@silva", "321");
		
		List<Usuario> lista = new ArrayList<>();
		
		lista.add(usuario1);
		lista.add(usuario2);
		
		return lista;
	}
}