package br.com.diogolages.app.view;

import java.util.List;
import java.util.Scanner;

import br.com.diogolages.app.model.ComprometimentoException;
import br.com.diogolages.app.model.Empresa;
import br.com.diogolages.app.model.EstruturaSocietaria;
import br.com.diogolages.app.service.EstruturaService;
import br.com.diogolages.app.service.FinanceiroService;

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
			System.out.println("\nDigite o número da empresa para retornar o total: ");
			for (int i = 0; i < empresas.size(); i++) {
				Empresa _empresa = empresas.get(i);
				System.out.println("\t" + (i + 1) + " - " + _empresa.getRazaoSocial() + " - " + _empresa.getPessoa().getNumeroDocumento());
			}
			System.out.print("\nDigite o índice da empresa: ");
			int indice = teclado.nextInt() - 1;
			if (indice >= 0 && indice < empresas.size()) {
				Empresa empresa = empresas.get(indice);
				System.out.println("O Comprometimento financeiro da empresa " + empresa.getRazaoSocial() + " é: " + financeiroService.comprometimentoFinanceiro(new EstruturaSocietaria(empresa)) + "\n");
			} else {
				throw new ComprometimentoException("Indice de imóvel inválido");
			}
		} else {
			throw new ComprometimentoException("Nenhuma empresa cadastrada");
		}
	}
	
}
