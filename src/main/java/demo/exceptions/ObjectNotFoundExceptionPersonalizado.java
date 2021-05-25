package demo.exceptions;


public class ObjectNotFoundExceptionPersonalizado extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// recebe uma string com a mensgem de excessao e repassa a mensagem pra quem a
	// chama
	public ObjectNotFoundExceptionPersonalizado(String pMensagemExcessao) {
		super(pMensagemExcessao);
	}

	// sobrecarga - recebe a mensagem e uma outra excessão pCause de alguma coisa q
	// aconteceu antes
	public ObjectNotFoundExceptionPersonalizado(String pMensagem, Throwable pCause) {
		super(pMensagem, pCause);
	}
}
