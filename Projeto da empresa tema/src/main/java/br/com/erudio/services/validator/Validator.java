package br.com.erudio.services.validator;

import br.com.erudio.exception.RegrasDoJogoException;

public interface Validator<T> {
	void valida(T object) throws RegrasDoJogoException;
}
