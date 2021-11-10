package br.com.erudio.exception;

public class RegrasDoJogoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegrasDoJogoException(String mensagem) {
		super(mensagem);
	}
	
	public RegrasDoJogoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
