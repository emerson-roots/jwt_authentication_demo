package demo.services;

import org.springframework.security.core.context.SecurityContextHolder;

import demo.security.UserSS;

/*
 * classe auxiliar utilizada para restrição de conteúdo
 * trata diretamente dos recursos de implementação de JWT
 * aplicado para auxiliar a restrição de conteúdo onde o Usuario (perfil CONVIDADO) só poderá recuperar ele mesmo
 * exceto nos casos de perfis ADMIN */
public class UserService {

	// método que retorna um usuario logado na forma de um usuário do spring
	// security
	public static UserSS authenticated() {

		// tratamento de excessão - pode gerar excessão caso o metodo
		// SecurityContextHolder retorne um usuário inexistente no banco
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}

	}

}
