package br.com.diogolages.app.service;

import br.com.diogolages.app.model.Empresa;
import br.com.diogolages.app.model.EstruturaSocietaria;
import br.com.diogolages.app.model.Imovel;
import br.com.diogolages.app.model.Pessoa;

/**
 * @author Diogo Lages
 *
 */
public class FinanceiroService {

	private static FinanceiroService instance;
	
	static {
		instance = new FinanceiroService();
	}
	
	private FinanceiroService() {
		super();
	}
	
	public static FinanceiroService getInstance() {
		return instance;
	}
	
	// varre a estrutura societária e retorna o valor total dos bens imóveis
	// da empresa e de seus sócios
	public Double comprometimentoFinanceiro(EstruturaSocietaria estruturaSocietaria) {
		Double valorComprometimento = 0d;
		Empresa empresa = estruturaSocietaria.getEmpresa();
		
		for (Imovel imovel : empresa.getPessoa().getImoveis()) {
			valorComprometimento += imovel.getValor();
		}
		
		for (Pessoa socio : empresa.getSocios()) {
			for (Imovel imovel : socio.getImoveis()) {
				// Compara se a empresa é sócia dela mesma
				// A contabilização dos bens imóveis de uma pessoa (física ou jurídica) só deve ocorrer uma única vez
				if (!socio.getNumeroDocumento().equals(empresa.getPessoa().getNumeroDocumento())) {
					valorComprometimento += imovel.getValor();
				}
			}
		}

		return valorComprometimento;
	}
	
}
