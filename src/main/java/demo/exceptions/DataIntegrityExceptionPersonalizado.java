package demo.exceptions;

/*
 * Classe criada para tratar excessões de integridade entre tabelas relacionadas. 
 * no momento de sua criação, não existia nenhuma relacionamento com a tabela de Usuarios */
public class DataIntegrityExceptionPersonalizado extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntegrityExceptionPersonalizado(String pMensagemExcessao) {
		super(pMensagemExcessao);
	}

	public DataIntegrityExceptionPersonalizado(String pMensagem, Throwable pCause) {
		super(pMensagem, pCause);
	}
}
