package demo.domain.dto;

import demo.domain.Usuario;

public class UsuarioDTO {
	
	private Long id;
	private String nome;
	
	public UsuarioDTO() {
	}

	public UsuarioDTO(Usuario obj) {
		id = obj.getId();
		nome = obj.getNome();
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
		
}
