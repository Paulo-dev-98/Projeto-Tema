package br.com.erudio.services.validator;

import br.com.erudio.data.vo.v1.CartaVO;
import br.com.erudio.exception.RegrasDoJogoException;

public class CartaValidator implements Validator<CartaVO> {

	@Override
	public void valida(CartaVO cartaVO) throws RegrasDoJogoException {
		
		if(cartaVO.getAtaque() > 10) {
			throw new RegrasDoJogoException("O ataque da carta não pode ter mais de 10 pontos");
		}
		
		if(cartaVO.getDefesa() > 10) {
			throw new RegrasDoJogoException("A defesa da carta não pode ter mais de 10 pontos");
		}
		
	}

}
