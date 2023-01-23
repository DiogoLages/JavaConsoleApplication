package br.com.diogolages.app.view;

import java.util.Scanner;

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
					System.out.println("Opção Inválida, selecione novamente\n");
					break;
				}
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
			opcao = selecionarOpcao(teclado);
		}
		teclado.close();
		System.out.println("Finalizado!");
	}

	private int selecionarOpcao(Scanner teclado) {
		System.out.print(getMenu());
		int opcao = teclado.nextInt();
		System.out.println("\n");
		return opcao;
	}
	
	
	private String getMenu() {
		return "#### Menu ####\n"
				+"Digite uma opção:\n\t"
				+ "1 - Criar Imóvel\n\t"
				+ "2 - Criar Pessoa Física\n\t"
				+ "3 - Criar Pessoa Jurídica-Empresa\n\t"
				+ "4 - Vincular Imóvel\n\t"
				+ "5 - Adicionar Sócio\n\t"
				+ "6 - Calcular Comprometimento Financeiro\n\t"
				+ "7 - Listar Empresas\n\t"
				+ "0 - Finalizar\n\t"
				+ "\n-> ";
	}
	
}
