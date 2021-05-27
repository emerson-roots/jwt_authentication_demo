package demo.domain.enums;

public enum Perfil {

	// o prefixo "ROLE_" é uma peculiaridade do Spring Security que
	// deve ser respeitado caso seja adicionado novos perfis
	ADMIN(1, "ROLE_ADMIN"), 
	CONVIDADO(2, "ROLE_CONVIDADO");

	private int cod;
	private String descricao;

	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	// static para nao ser necessario instanciar
	public static Perfil toEnum(Integer cod) {

		// proteção - se for nulo, retorna nulo
		if (cod == null) {
			return null;
		}

		// percorre todos valores possiveis do tipo enumerado TipoUsuario de acordo com
		// as declarações
		for (Perfil tipoUsuario : Perfil.values()) {
			if (cod.equals(tipoUsuario.getCod())) {
				return tipoUsuario;
			}

		}
		// caso esgote o for e não retornar nenhum valor valido - lança exception
		throw new IllegalArgumentException("Id Inválido: " + cod);

	}

}