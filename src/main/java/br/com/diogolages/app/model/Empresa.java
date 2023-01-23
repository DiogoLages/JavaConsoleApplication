package br.com.diogolages.app.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Diogo Lages 
 * Toda empresa possui uma estrutura societária.
 */
public class Empresa {

	private Pessoa pessoa;
	private List<Pessoa> socios;

	public Empresa(List<Pessoa> socios, String razaoSocial) {
		super();
		this.socios = socios;
		this.razaoSocial = razaoSocial;
	}

	public Empresa(String razaoSocial, Pessoa pessoa, Pessoa... socios) {
		super();
		this.socios = new ArrayList<>();
		this.pessoa = pessoa;
		Arrays.asList(socios).forEach(this.socios::add);
		this.razaoSocial = razaoSocial;
	}

	public void addSocio(Pessoa socio) {
		socios.add(socio);
	}

	private String razaoSocial;

	public List<Pessoa> getSocios() {
		return socios;
	}

	public void setSocios(List<Pessoa> socios) {
		this.socios = socios;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
