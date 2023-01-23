package br.com.diogolages.app.view;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.diogolages.app.model.ComprometimentoException;
import br.com.diogolages.app.model.Imovel;
import br.com.diogolages.app.model.Pessoa;
import br.com.diogolages.app.service.EstruturaService;

/**
 * 
 * @author Diogo Lages
 *
 */
public class CadastroImovelView {
	
	private static CadastroImovelView instance;
	private EstruturaService service;
	
	static {
		instance = new CadastroImovelView();
	}
	
	private CadastroImovelView() {
		this.service = EstruturaService.getInstance();
	}

	public static CadastroImovelView getInstance() {
		return instance;
	}
	
	public void cadastrarImovel(Scanner teclado) {
		System.out.print("Informe o nome do imóvel: ");
		String nomeImovel = teclado.nextLine();
		System.out.print("Informe o valor do imóvel: ");
		Double valorImovel = teclado.nextDouble();
		Imovel imovel = service.criarImovel(nomeImovel, valorImovel);
		System.out.println("Imóvel cadastrado com sucesso, Nome: " + imovel.getNome() + ", ID: [" + imovel.getId() + "], no valor de: " + imovel.getValor() + "\n");
	}
	
	public void vincularImovel(Scanner teclado) {
		List<Pessoa> pessoas = service.getPessoas();
		if (!pessoas.isEmpty()) {
			Pessoa pessoa = null;
			Imovel imovel = null;
			System.out.println("Pessoas:");
			for (int i = 0; i < pessoas.size(); i++) {
				Pessoa p = pessoas.get(i);
				System.out.println("\t" + (i + 1) + " - " + p.getNome() + " - " + p.getNumeroDocumento());
			}
			System.out.print("\nDigite o índice da pessoa: ");
			int indice = teclado.nextInt() - 1;
			if (indice >= 0 && indice < pessoas.size()) {
				pessoa = pessoas.get(indice);
				imovel = recuperarImovel(teclado, pessoa);
			} else {
				throw new ComprometimentoException("Indice de imóvel inválido\n");
			}
			service.adicionarImovel(pessoa, imovel);
			System.out.println("Imovém vinculado com sucesso\n");
		} else {
			throw new ComprometimentoException("Nenhuma pessoa cadastrada\n");
		}
	}

	private Imovel recuperarImovel(Scanner teclado, Pessoa pessoa) {
		//recupera as pessoas que ainda não estão vinculadas ao imvóvel
		List<Imovel> imoveis = service.getImoveis().stream().filter(i -> Objects.isNull(i.getProprietario())).collect(Collectors.toList());
		if (!imoveis.isEmpty()) {
			Imovel imovel = null;
			System.out.println("\nImoveis: ");
			for (int i = 0; i < imoveis.size(); i++) {
				Imovel _imovel = imoveis.get(i);
				System.out.println("\t" + (i + 1) + " - " + _imovel.getNome() + " - UUID: " + _imovel.getId() + " com valor: " + _imovel.getValor());
			}
			System.out.print("\nDigite o índice do imóvel: ");
			int indice = teclado.nextInt() - 1;
			if (indice >= 0 && indice < imoveis.size()) {
				imovel = imoveis.get(indice);
			} else {
				throw new ComprometimentoException("Indice de imóvel inválido\n");
			}
			return imovel;
		} else {
			throw new ComprometimentoException("Nenhum imóvel disponível para a ser vinculado a pessoa " + pessoa.getNome()+"\n");
		}
	}
	
}
