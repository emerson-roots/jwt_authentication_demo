package demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import demo.domain.Usuario;
import demo.repositories.UsuarioRepository;
import demo.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;

	// metodo que vai receber o usuario e vai retornar o UserDetails
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Usuario usr = repo.findByEmail(email);

		if (usr == null) {
			// excessão do spring security por estar no contexto de segurança
			throw new UsernameNotFoundException(email);
		}

		return new UserSS(usr.getId(), usr.getEmail(), usr.getSenha(), usr.getPerfis());
	}

}
