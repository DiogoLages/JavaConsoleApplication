package br.com.diogolages.app.model;

/**
 * @author Diogo Lages
 *
 */
public class PessoaFisica {
	
	private Pessoa pessoa;
	
	public PessoaFisica(Pessoa pessoa) {
		super();
		this.pessoa = pessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
