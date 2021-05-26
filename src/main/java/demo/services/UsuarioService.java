package demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import demo.domain.Usuario;
import demo.domain.dto.UsuarioDTO;
import demo.domain.dto.UsuarioNewDTO;
import demo.exceptions.DataIntegrityExceptionPersonalizado;
import demo.exceptions.ObjectNotFoundExceptionPersonalizado;
import demo.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usrRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Usuario find(Long id) {

		Optional<Usuario> obj = usrRepo.findById(id);

		// tratamento de excessao - caso faça busca no repositório e retorne nulo e
		// LANÇA EXCESSAO personalizada para a camada de recurso
		return obj.orElseThrow(() -> new ObjectNotFoundExceptionPersonalizado(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	public Usuario insert(Usuario obj) {
		
		// garante que o novo objeto a ser inserido tem id nulo, caso contrario entende
		// como uma atualização
		obj.setId(null);

		return usrRepo.save(obj);
	}
	
	public Usuario update(Usuario obj) {
		//chama o metodo de busca para verificar se o id existe
		find(obj.getId());
		return usrRepo.save(obj);
	}
	
	public void delete(Long id) {
		
		find(id);
		
		try {
			usrRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityExceptionPersonalizado("Não é possível excluir uma Usuario que possui outras tabelas relacionados a ele");
		}
	}
	
	public List<Usuario> findAll(){
		return usrRepo.findAll();
	}
	
	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), null, null);
	}
	
	//usado para novos Usuarios
	public Usuario fromDTO(UsuarioNewDTO objNewDto) {
		return new Usuario(null, objNewDto.getNome(), objNewDto.getEmail(), passwordEncoder.encode(objNewDto.getSenha()));
	}

}
