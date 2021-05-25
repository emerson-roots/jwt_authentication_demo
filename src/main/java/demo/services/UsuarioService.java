package demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.domain.Usuario;
import demo.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usrRepo;

	public Usuario buscar(Long id) {

		Optional<Usuario> obj = usrRepo.findById(id);
		return obj.orElse(null);
	}

}
