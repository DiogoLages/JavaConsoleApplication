package br.com.diogolages.app.view;

import java.util.Scanner;

import br.com.diogolages.app.util.Constantes;
import br.com.diogolages.app.util.Utils;

/**
 * 
 * @author Diogo Lages
 *
 */
public class MenuView {

	private CadastroImovelView cadastroImovel;
	private CadastroPessoaView cadastroPessoa;
	private ComprometimentoFinanceiroView comprometimentoFinanceiro;
	private ListagemView listagemView;
	
	public MenuView() {
		this.cadastroImovel = CadastroImovelView.getInstance();
		this.cadastroPessoa = CadastroPessoaView.getInstance();
		this.comprometimentoFinanceiro = ComprometimentoFinanceiroView.getInstance();
		this.listagemView = ListagemView.getInstance();
	}
	
	public void start() {
		Scanner teclado = new Scanner(System.in);
		int opcao = selecionarOpcao(teclado);
		while (opcao != 0) {
			teclado.nextLine();//despresar quebra de linha na leitura de um inteiro
			try {
				switch (opcao) {
				case 1:
					cadastroImovel.cadastrarImovel(teclado);
					break;
				case 2:
					cadastroPessoa.cadastrarPessoaFisica(teclado);
					break;
				case 3:
					cadastroPessoa.cadastrarEmpresa(teclado);
					break;
				case 4:
					cadastroImovel.vincularImovel(teclado);
					break;
				case 5:
					cadastroPessoa.adicionarSocio(teclado);
					break;
				case 6:
					comprometimentoFinanceiro.recuperarComprometimentoFinanceiro(teclado);
					break;
				case 7:
					listagemView.listarEmpresas(teclado);
					break;
				default:
					Utils.imprimeDadosNaTelaLN("Opção Inválida, selecione novamente".concat(getMenu()).concat(Constantes.QUEBRA_DE_LINHA));
					break;
				}
			} catch (RuntimeException e) {
				Utils.imprimeDadosNaTelaLN(e.getMessage());
			}
			opcao = selecionarOpcao(teclado);
		}
		teclado.close();
		Utils.imprimeDadosNaTelaLN("Finalizado!");
	}

	private int selecionarOpcao(Scanner teclado) {
		System.out.print(getMenu());
		int opcao = teclado.nextInt();
		Utils.imprimeDadosNaTelaLN(Constantes.QUEBRA_DE_LINHA);
		return opcao;
	}
	
	
	private String getMenu() {
		return "#### Menu ####" + Constantes.QUEBRA_DE_LINHA
				+"Digite uma opção: " + Constantes.QUEBRA_DE_LINHA_TABULACAO
				+ "1 - Criar Imóvel" + Constantes.QUEBRA_DE_LINHA_TABULACAO
				+ "2 - Criar Pessoa Física" + Constantes.QUEBRA_DE_LINHA_TABULACAO
				+ "3 - Criar Pessoa Jurídica-Empresa" + Constantes.QUEBRA_DE_LINHA_TABULACAO
				+ "4 - Vincular Imóvel" + Constantes.QUEBRA_DE_LINHA_TABULACAO
				+ "5 - Adicionar Sócio" + Constantes.QUEBRA_DE_LINHA_TABULACAO
				+ "6 - Calcular Comprometimento Financeiro" + Constantes.QUEBRA_DE_LINHA_TABULACAO
				+ "7 - Listar Empresas" + Constantes.QUEBRA_DE_LINHA_TABULACAO
				+ "0 - Finalizar" + Constantes.QUEBRA_DE_LINHA_TABULACAO
				+ Constantes.QUEBRA_DE_LINHA + "--> ";
	}
	
}
