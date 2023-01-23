package br.com.diogolages.app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Diogo Lages
 *
 */
public class Pessoa {

	private String nome;
	private String numeroDocumento;

	private List<Imovel> imoveis;
	
	public Pessoa(String nome, String numeroDocumento, List<Imovel> imoveis) {
		this.numeroDocumento = numeroDocumento;
		this.nome = nome;
		this.imoveis = new ArrayList<>();
		imoveis.forEach(this.imoveis::add);
	}
	
	public Pessoa(String nome, String numeroDocumento) {
		this(nome, numeroDocumento, Collections.emptyList());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

}
