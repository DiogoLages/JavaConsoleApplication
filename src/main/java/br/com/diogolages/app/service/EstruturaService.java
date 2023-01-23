package br.com.diogolages.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.diogolages.app.model.ComprometimentoException;
import br.com.diogolages.app.model.Empresa;
import br.com.diogolages.app.model.Imovel;
import br.com.diogolages.app.model.Pessoa;
import br.com.diogolages.app.model.PessoaFisica;

/**
 * 
 * @author Diogo Lages
 *
 */
public class EstruturaService {
	
	private List<Empresa> empresas;
	private List<Pessoa> pessoas;
	private List<PessoaFisica> pessoasFisicas;
	private List<Imovel> imoveis;
	
	private static EstruturaService instance;
	private ValidadorDocumentoService validadorDocumentoService;
	
	static {
		instance = new EstruturaService();
	}
	
	private EstruturaService() {
		super();
		this.empresas = new ArrayList<>();
		this.pessoas = new ArrayList<>();
		this.pessoasFisicas = new ArrayList<>();
		this.imoveis = new ArrayList<>();
		this.validadorDocumentoService = ValidadorDocumentoService.getInstance();
	}
	
	public static EstruturaService getInstance() {
		return instance;
	}
	
	public Imovel criarImovel(String nome, Double valor) {
		Imovel imovel = new Imovel(valor, nome);
		imoveis.add(imovel);
		return imovel;
	}
	
	public Pessoa criarPessoaFisica(String nome, String documento) {
		Pessoa pessoa = new Pessoa(nome, documento);
		PessoaFisica pessoaFisica = new PessoaFisica(pessoa); 
		//valida CPF
		validadorDocumentoService.validarCPF(documento);
		//verifica se o documento já está cadastrado
		if (validarCadastro(documento)) {
			this.pessoas.add(pessoa);
			this.pessoasFisicas.add(pessoaFisica);
		} else {
			throw new ComprometimentoException("\nCPF já cadastrado: " + pessoa.getNumeroDocumento() + "\n");
		}
		return pessoa;
	}

	public Empresa criarEmpresa(String nome, String documento, String razaoSocial) {
		Pessoa pessoa = new Pessoa(nome, documento);
		Empresa empresa = new Empresa(razaoSocial, pessoa);
		//valida CNPJ
		validadorDocumentoService.validarCPNJ(documento);
		//verifica se o CNPJ já está cadastrado
		if (validarCadastro(documento)) {
			this.pessoas.add(pessoa);
			this.empresas.add(empresa);
		} else {
			throw new ComprometimentoException("\nCNPJ já cadastrado: " + pessoa.getNumeroDocumento() + "\n");
		}
		return empresa;
	}
	
	public void adicionarImovel(Pessoa pessoa, Imovel imovel) {
		Pessoa proprietario = this.pessoas.stream().filter(p -> p.getImoveis().contains(imovel)).findAny().orElse(null);
		if (Objects.isNull(proprietario)) {
			pessoa.getImoveis().add(imovel);
			imovel.setProprietario(pessoa);
		} else {
			throw new ComprometimentoException("Este imóvel já pertence a " + pessoa.getNome());
		}
	}
	
	public void adicionarSocio(Empresa empresa, Pessoa socio) {
		empresa.addSocio(socio);
	}
	
	private boolean validarCadastro(String documento) {
		return Objects.isNull(this.pessoas.stream().filter(p -> p.getNumeroDocumento().contains(documento)).findAny().orElse(null));
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public List<PessoaFisica> getPessoasFisicas() {
		return pessoasFisicas;
	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}
	
}
