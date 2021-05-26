package demo.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import demo.domain.Usuario;

public class UsuarioNewDTO {
	
	private Long id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3, max = 80, message = "O tamanho deve ser entre {min} e {max} caracteres")
	private String nome;
	
	@NotEmpty(message = "E-mail obrigatório")
	@Email(message = "E-mail inválido.")
	private String email;
	
	@NotEmpty(message = "Senha obrigatória")
	@Length(min = 3, max = 20, message = "A senha deve ser entre {min} e {max} caracteres")
	private String senha;
	
	public UsuarioNewDTO() {
	}

	public UsuarioNewDTO(Usuario obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		senha = obj.getSenha();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
