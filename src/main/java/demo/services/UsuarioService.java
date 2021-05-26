package demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import demo.domain.Usuario;
import demo.exceptions.DataIntegrityExceptionPersonalizado;
import demo.exceptions.ObjectNotFoundExceptionPersonalizado;
import demo.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usrRepo;

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

}
