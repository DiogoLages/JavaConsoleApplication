package br.com.diogolages.app.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.diogolages.app.exception.ComprometimentoException;
import br.com.diogolages.app.model.Empresa;
import br.com.diogolages.app.model.Pessoa;
import br.com.diogolages.app.service.EstruturaService;
import br.com.diogolages.app.util.Constantes;
import br.com.diogolages.app.util.ImpressoraUtils;

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
		ImpressoraUtils.imprimeLinha("Informe o nome da pessoa: ");
		String nomePessoa = teclado.nextLine();
		ImpressoraUtils.imprimeLinha("Informe CPF da pessoa [999.999.999-99]: ");
		String cpf = teclado.nextLine();
		Pessoa pessoa = service.criarPessoaFisica(nomePessoa, cpf);
		ImpressoraUtils.imprimeDadosNaTelaLN(Constantes.QUEBRA_DE_LINHA.concat("Pessoa cadastrado com sucesso, Nome: ")
				.concat(pessoa.getNome()).concat(", CPF: [").concat(pessoa.getNumeroDocumento()).concat("]")
				.concat(Constantes.QUEBRA_DE_LINHA));
	}

	public void cadastrarEmpresa(Scanner teclado) {
		ImpressoraUtils.imprimeLinha("Informe o nome da empresa: ");
		String nomeEmpresa = teclado.nextLine();
		ImpressoraUtils.imprimeLinha("Informe a Razão Social da empresa: ");
		String razaoSocial = teclado.nextLine();
		ImpressoraUtils.imprimeLinha("Informe CNPJ da empresa [99.999.999/9999-99]: ");
		String cnpj = teclado.nextLine();
		Empresa empresa = service.criarEmpresa(nomeEmpresa, cnpj, razaoSocial);
		ImpressoraUtils.imprimeDadosNaTelaLN(Constantes.QUEBRA_DE_LINHA.concat("Empresa cadastrada com sucesso, Nome: ")
				.concat(empresa.getPessoa().getNome()).concat(", CNPJ: [")
				.concat(empresa.getPessoa().getNumeroDocumento()).concat("], Razão Social: ")
				.concat(empresa.getRazaoSocial()).concat(Constantes.QUEBRA_DE_LINHA));
	}

	public void adicionarSocio(Scanner teclado) {
		List<Empresa> empresas = service.getEmpresas();
		if (!empresas.isEmpty()) {
			Empresa empresa = null;
			Pessoa pessoa = null;
			ImpressoraUtils.imprimeDadosNaTelaLN(Constantes.QUEBRA_DE_LINHA.concat("Empresas: "));
			for (int i = 0; i < empresas.size(); i++) {
				Empresa _empresa = empresas.get(i);
				ImpressoraUtils.imprimeDadosNaTelaLN(Constantes.TABULAR + (i + 1)
						+ Constantes.SEPARA.concat(_empresa.getPessoa().getNome()).concat(" - Razão Social: ")
								.concat(_empresa.getRazaoSocial()).concat(Constantes.SEPARA)
								.concat(_empresa.getPessoa().getNumeroDocumento()));
			}
			ImpressoraUtils.imprimeLinha(Constantes.QUEBRA_DE_LINHA.concat("Digite o índice da empresa: "));
			int indice = teclado.nextInt() - 1;
			if (indice >= 0 && indice < empresas.size()) {
				empresa = empresas.get(indice);
				pessoa = recuperarSocio(teclado, empresa);
			} else {
				throw new ComprometimentoException("Indice de imóvel inválido".concat(Constantes.QUEBRA_DE_LINHA));
			}
			ImpressoraUtils.imprimeDadosNaTelaLN(Constantes.QUEBRA_DE_LINHA.concat("Imovél vinculado com sucesso")
					.concat(Constantes.QUEBRA_DE_LINHA));
			service.adicionarSocio(empresa, pessoa);
		} else {
			throw new ComprometimentoException("Nenhuma empresa cadastrada".concat(Constantes.QUEBRA_DE_LINHA));
		}
	}

	private Pessoa recuperarSocio(Scanner teclado, Empresa empresa) {
		List<Pessoa> pessoas = service.getPessoas().stream().filter(p -> !empresa.getSocios().contains(p))
				.collect(Collectors.toList());
		if (!pessoas.isEmpty()) {
			Pessoa pessoa = null;
			ImpressoraUtils.imprimeDadosNaTelaLN("Pessoas:");

			for (int i = 0; i < pessoas.size(); i++) {
				Pessoa p = pessoas.get(i);
				ImpressoraUtils.imprimeDadosNaTelaLN(Constantes.TABULAR + (i + 1) + Constantes.SEPARA.concat(p.getNome())
						.concat(Constantes.SEPARA).concat(p.getNumeroDocumento()));
			}

			ImpressoraUtils.imprimeLinha(Constantes.QUEBRA_DE_LINHA.concat("Digite o índice da pessoa: "));
			int indice = teclado.nextInt() - 1;
			if (indice >= 0 && indice < pessoas.size()) {
				pessoa = pessoas.get(indice);
			} else {
				throw new ComprometimentoException("Indice de imóvel inválido".concat(Constantes.QUEBRA_DE_LINHA));
			}
			return pessoa;
		} else {
			throw new ComprometimentoException("Nenhuma pessoa eletiva para ser sócia da empresa "
					.concat(empresa.getRazaoSocial()).concat(Constantes.QUEBRA_DE_LINHA));
		}
	}

}
