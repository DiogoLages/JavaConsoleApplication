package br.com.diogolages.app.view;

import java.util.List;
import java.util.Scanner;

import br.com.diogolages.app.exception.ComprometimentoException;
import br.com.diogolages.app.model.Empresa;
import br.com.diogolages.app.service.EstruturaService;
import br.com.diogolages.app.util.Constantes;
import br.com.diogolages.app.util.Utils;

/**
 * 
 * @author Diogo Lages
 *
 */
public class ListagemView {

	private static ListagemView instance;
	private EstruturaService estruturaService;

	static {
		instance = new ListagemView();
	}

	public static ListagemView getInstance() {
		return instance;
	}

	public ListagemView() {
		estruturaService = EstruturaService.getInstance();
	}

	public void listarEmpresas(Scanner teclado) {
		List<Empresa> empresas = estruturaService.getEmpresas();
		if (!empresas.isEmpty()) {
			empresas.forEach(e -> {
				Utils.imprimeDadosNaTelaLN("Empresa: ".concat(e.getPessoa().getNome()).concat(Constantes.SEPARA)
						.concat(" Razão Social: ").concat(e.getRazaoSocial()));
				Utils.imprimeDadosNaTelaLN(Constantes.TABULAR.concat("Imóveis: "));
				e.getPessoa().getImoveis().forEach(i -> {
					Utils.imprimeDadosNaTelaLN(
							Constantes.TABULAR_2X.concat(i.getNome()).concat(Constantes.SEPARA) + i.getValor());
				});
				Utils.imprimeDadosNaTelaLN(Constantes.TABULAR_2X.concat(" Sócios: "));
				e.getSocios().forEach(s -> {
					Utils.imprimeDadosNaTelaLN(Constantes.TABULAR_3X.concat(s.getNome()).concat(Constantes.SEPARA)
							.concat(s.getNumeroDocumento()));
					Utils.imprimeDadosNaTelaLN(Constantes.TABULAR_4X.concat(" Imóveis: "));
					s.getImoveis().forEach(i -> {
						Utils.imprimeDadosNaTelaLN(
								Constantes.TABULAR_5X.concat(i.getNome()).concat(Constantes.SEPARA) + i.getValor());
					});
				});
			});
		} else {
			throw new ComprometimentoException(
					Constantes.QUEBRA_DE_LINHA.concat("Nenhuma empresa cadastrada").concat(Constantes.QUEBRA_DE_LINHA));
		}
	}

}
