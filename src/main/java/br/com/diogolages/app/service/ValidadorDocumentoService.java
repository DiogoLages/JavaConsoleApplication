package br.com.diogolages.app.service;

import java.util.List;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.diogolages.app.exception.ComprometimentoException;

/**
 * @author Diogo Lages
 *
 */
public class ValidadorDocumentoService {

	private static ValidadorDocumentoService instance;

	static {
		instance = new ValidadorDocumentoService();
	}

	public static ValidadorDocumentoService getInstance() {
		return instance;
	}

	public void validarCPF(String cpf) {
		CPFValidator cpfValidator = new CPFValidator();
		List<ValidationMessage> erros = cpfValidator.invalidMessagesFor(cpf);
		if (!erros.isEmpty()) {
			throw new ComprometimentoException("\n" + erros + "\n");
		}
	}

	public void validarCPNJ(String cnpj) {
		CNPJValidator cnpjValidator = new CNPJValidator();
		List<ValidationMessage> erros = cnpjValidator.invalidMessagesFor(cnpj);
		if (!erros.isEmpty()) {
			throw new ComprometimentoException("\n" + erros + "\n");
		}
	}

}
