package br.com.diogolages.app.view;

import java.util.List;
import java.util.Scanner;

import br.com.diogolages.app.model.ComprometimentoException;
import br.com.diogolages.app.model.Empresa;
import br.com.diogolages.app.service.EstruturaService;

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
				System.out.println("Empresa: " + e.getPessoa().getNome() + " - Razão Social: "+ e.getRazaoSocial());
				System.out.println("\tImóveis: ");
				e.getPessoa().getImoveis().forEach(i -> {
					System.out.println("\t\t" + i.getNome() + " - " + i.getValor());
				});
				System.out.println("\t\tSócios: ");
				e.getSocios().forEach(s -> {
					System.out.println("\t\t\t" + s.getNome() + " - " + s.getNumeroDocumento());
					System.out.println("\t\t\t\tImóveis: ");
					s.getImoveis().forEach(i -> {
						System.out.println("\t\t\t\t\t" + i.getNome() + " - " + i.getValor());
					});
				});
			});
		} else {
			throw new ComprometimentoException("\nNenhuma empresa cadastrada\n");
		}
	}
	
}
