package br.com.diogolages.app.model;

/**
 * @author Diogo Lages
 *
 */
public class EstruturaSocietaria {
	//Toda empresa possui uma estrutura societária.
	//onde em empresa tem pessoa que pode ser do tipo Fisica ou Juridica e seus socios
	private Empresa empresa;
	
	public EstruturaSocietaria(Empresa empresa) {
		super();
		this.empresa = empresa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}
