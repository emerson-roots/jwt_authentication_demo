package demo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import demo.domain.Usuario;
import demo.domain.dto.UsuarioDTO;
import demo.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable Long id) {

		Usuario obj = usuarioService.find(id);

		return ResponseEntity.ok().body(obj);

	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Usuario obj){//@RequestBody faz o Json ser convertido para o objeto java automaticamente

		obj = usuarioService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(obj.getId()).toUri();//URI do java.net

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Usuario obj, @PathVariable Long id) {
		obj.setId(id);
		obj = usuarioService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		
		usuarioService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> findAll(){
		
		List<Usuario> list = usuarioService.findAll();
		List<UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
}