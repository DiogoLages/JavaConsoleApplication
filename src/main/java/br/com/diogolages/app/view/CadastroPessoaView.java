package br.com.diogolages.app.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.diogolages.app.model.ComprometimentoException;
import br.com.diogolages.app.model.Empresa;
import br.com.diogolages.app.model.Pessoa;
import br.com.diogolages.app.service.EstruturaService;

/**
 * 
 * @author Diogo Lages
 *
 */
public class CadastroPessoaView {
	
	private static CadastroPessoaView instance;
	private EstruturaService service;
	
	static {
		instance = new CadastroPessoaView();
	}
	
	public static CadastroPessoaView getInstance() {
		return instance;
	}
	
	private CadastroPessoaView() {
		this.service = EstruturaService.getInstance();
	}

	public void cadastrarPessoaFisica(Scanner teclado) {
		System.out.print("Informe o nome da pessoa: ");
		String nomePessoa = teclado.nextLine();
		System.out.print("Informe CPF da pessoa [999.999.999-99]: ");
		String cpf = teclado.nextLine();
		Pessoa pessoa = service.criarPessoaFisica(nomePessoa, cpf);
		System.out.println("\nPessoa cadastrado com sucesso, Nome: " + pessoa.getNome() + ", CPF: [" + pessoa.getNumeroDocumento() + "]" + "\n");
	}
	
	public void cadastrarEmpresa(Scanner teclado) {
		System.out.print("Informe o nome da empresa: ");
		String nomeEmpresa = teclado.nextLine();
		System.out.print("Informe a Razão Social da empresa: ");
		String razaoSocial = teclado.nextLine();
		System.out.print("Informe CNPJ da empresa [99.999.999/9999-99]: ");
		String cnpj = teclado.nextLine();
		Empresa empresa = service.criarEmpresa(nomeEmpresa, cnpj, razaoSocial);
		System.out.println("\nEmpresa cadastrada com sucesso, Nome: " + empresa.getPessoa().getNome() + ", CNPJ: [" + empresa.getPessoa().getNumeroDocumento() + "], Razão Social: " + empresa.getRazaoSocial() + "\n");
	}

	public void adicionarSocio(Scanner teclado) {
		List<Empresa> empresas = service.getEmpresas();
		if (!empresas.isEmpty()) {
			Empresa empresa = null;
			Pessoa pessoa = null;
			System.out.println("\nEmpresas: ");
			for (int i = 0; i < empresas.size(); i++) {
				Empresa _empresa = empresas.get(i);
				System.out.println("\t" + (i + 1) + " - " + _empresa.getRazaoSocial() + " - " + _empresa.getPessoa().getNumeroDocumento());
			}
			System.out.print("\nDigite o índice da empresa: ");
			int indice = teclado.nextInt() - 1;
			if (indice >= 0 && indice < empresas.size()) {
				empresa = empresas.get(indice);
				pessoa = recuperarSocio(teclado, empresa);
			} else {
				throw new ComprometimentoException("Indice de imóvel inválido\n");
			}
			System.out.println("\nImovél vinculado com sucesso\n");
			service.adicionarSocio(empresa, pessoa);
		} else {
			throw new ComprometimentoException("Nenhuma empresa cadastrada\n");
		}
	}
	
	private Pessoa recuperarSocio(Scanner teclado, Empresa empresa) {
		List<Pessoa> pessoas = service.getPessoas().stream().filter(p -> !empresa.getSocios().contains(p)).collect(Collectors.toList());
		if (!pessoas.isEmpty()) {
			Pessoa pessoa = null;
			System.out.println("Pessoas:");
			for (int i = 0; i < pessoas.size(); i++) {
				Pessoa p = pessoas.get(i);
				System.out.println("\t" + (i + 1) + " - " + p.getNome() + " - " + p.getNumeroDocumento());
			}
			System.out.print("\nDigite o índice da pessoa: ");
			int indice = teclado.nextInt() - 1;
			if (indice >= 0 && indice < pessoas.size()) {
				pessoa = pessoas.get(indice);
			} else {
				throw new ComprometimentoException("Indice de imóvel inválido\n");
			}
			return pessoa;
		} else {
			throw new ComprometimentoException("Nenhuma pessoa eletiva para ser sócia da empresa " + empresa.getRazaoSocial()+"\n");
		}
	}
	
}
