package br.com.diogolages.app.view;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.diogolages.app.exception.ComprometimentoException;
import br.com.diogolages.app.model.Imovel;
import br.com.diogolages.app.model.Pessoa;
import br.com.diogolages.app.service.EstruturaService;
import br.com.diogolages.app.util.Constantes;
import br.com.diogolages.app.util.ImpressoraUtils;

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
		ImpressoraUtils.imprimeLinha("Informe o nome do imóvel: ");
		String nomeImovel = teclado.nextLine();
		ImpressoraUtils.imprimeLinha("Informe o valor do imóvel: ");
		Double valorImovel = teclado.nextDouble();
		Imovel imovel = service.criarImovel(nomeImovel, valorImovel);
		ImpressoraUtils.imprimeDadosNaTelaLN("Imóvel cadastrado com sucesso, Nome: ".concat(imovel.getNome()).concat(", ID: [")
				.concat(imovel.getId()).concat("], no valor de: ") + imovel.getValor() + Constantes.QUEBRA_DE_LINHA);
	}

	public void vincularImovel(Scanner teclado) {
		List<Pessoa> pessoas = service.getPessoas();
		if (!pessoas.isEmpty()) {
			Pessoa pessoa = null;
			Imovel imovel = null;
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
				imovel = recuperarImovel(teclado, pessoa);
			} else {
				throw new ComprometimentoException("Indice de imóvel inválido".concat(Constantes.QUEBRA_DE_LINHA));
			}
			service.adicionarImovel(pessoa, imovel);
			ImpressoraUtils.imprimeDadosNaTelaLN("Imovém vinculado com sucesso".concat(Constantes.QUEBRA_DE_LINHA));
		} else {
			throw new ComprometimentoException("Nenhuma pessoa cadastrada".concat(Constantes.QUEBRA_DE_LINHA));
		}
	}

	private Imovel recuperarImovel(Scanner teclado, Pessoa pessoa) {
		// recupera as pessoas que ainda não estão vinculadas ao imvóvel
		List<Imovel> imoveis = service.getImoveis().stream().filter(i -> Objects.isNull(i.getProprietario()))
				.collect(Collectors.toList());
		if (!imoveis.isEmpty()) {
			Imovel imovel = null;
			ImpressoraUtils.imprimeDadosNaTelaLN(Constantes.QUEBRA_DE_LINHA.concat("Imoveis: "));
			for (int i = 0; i < imoveis.size(); i++) {
				Imovel _imovel = imoveis.get(i);
				ImpressoraUtils.imprimeDadosNaTelaLN(Constantes.TABULAR + (i + 1) + Constantes.SEPARA.concat(_imovel.getNome())
						.concat(" - UUID: ").concat(_imovel.getId()).concat(" com valor: ") + _imovel.getValor());
			}
			ImpressoraUtils.imprimeLinha(Constantes.QUEBRA_DE_LINHA.concat("Digite o índice do imóvel: "));
			int indice = teclado.nextInt() - 1;
			if (indice >= 0 && indice < imoveis.size()) {
				imovel = imoveis.get(indice);
			} else {
				throw new ComprometimentoException("Indice de imóvel inválido".concat(Constantes.QUEBRA_DE_LINHA));
			}
			return imovel;
		} else {
			throw new ComprometimentoException("Nenhum imóvel disponível para a ser vinculado a pessoa "
					.concat(pessoa.getNome()).concat(Constantes.QUEBRA_DE_LINHA));
		}
	}

}
