package demo.exceptions;

public class AuthorizationExceptionPersonalizado extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthorizationExceptionPersonalizado(String pMensagemExcessao) {
		super(pMensagemExcessao);
	}

	public AuthorizationExceptionPersonalizado(String pMensagem, Throwable pCause) {
		super(pMensagem, pCause);
	}

}
