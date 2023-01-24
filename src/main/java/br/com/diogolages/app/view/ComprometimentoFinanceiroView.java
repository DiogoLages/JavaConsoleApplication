package br.com.diogolages.app.view;

import java.util.List;
import java.util.Scanner;

import br.com.diogolages.app.exception.ComprometimentoException;
import br.com.diogolages.app.model.Empresa;
import br.com.diogolages.app.model.EstruturaSocietaria;
import br.com.diogolages.app.service.EstruturaService;
import br.com.diogolages.app.service.FinanceiroService;
import br.com.diogolages.app.util.Constantes;
import br.com.diogolages.app.util.ImpressoraUtils;

/**
 * 
 * @author Diogo Lages
 *
 */
public class ComprometimentoFinanceiroView {

	private static ComprometimentoFinanceiroView instance;
	private FinanceiroService financeiroService;
	private EstruturaService estruturaService;

	static {
		instance = new ComprometimentoFinanceiroView();
	}

	public static ComprometimentoFinanceiroView getInstance() {
		return instance;
	}

	public ComprometimentoFinanceiroView() {
		this.financeiroService = FinanceiroService.getInstance();
		this.estruturaService = EstruturaService.getInstance();
	}

	public void recuperarComprometimentoFinanceiro(Scanner teclado) {
		List<Empresa> empresas = estruturaService.getEmpresas();
		if (!empresas.isEmpty()) {
			ImpressoraUtils.imprimeDadosNaTelaLN(Constantes.QUEBRA_DE_LINHA.concat("Digite o número da empresa para retornar o total: "));
			for (int i = 0; i < empresas.size(); i++) {
				Empresa _empresa = empresas.get(i);
				ImpressoraUtils.imprimeDadosNaTelaLN(Constantes.TABULAR + (i + 1) + Constantes.SEPARA + _empresa.getRazaoSocial()
						+ Constantes.SEPARA + _empresa.getPessoa().getNumeroDocumento());
			}
			ImpressoraUtils.imprimeLinha(Constantes.QUEBRA_DE_LINHA.concat("Digite o índice da empresa: "));
			int indice = teclado.nextInt() - 1;
			if (indice >= 0 && indice < empresas.size()) {
				Empresa empresa = empresas.get(indice);
				ImpressoraUtils.imprimeDadosNaTelaLN(Constantes.QUEBRA_DE_LINHA.concat("O Comprometimento financeiro da empresa ")
						.concat(empresa.getRazaoSocial()).concat(" é: ")
								+ financeiroService.comprometimentoFinanceiro(new EstruturaSocietaria(empresa))
								+ Constantes.QUEBRA_DE_LINHA);
			} else {
				throw new ComprometimentoException("Indice de imóvel inválido");
			}
		} else {
			throw new ComprometimentoException("Nenhuma empresa cadastrada");
		}
	}

}
