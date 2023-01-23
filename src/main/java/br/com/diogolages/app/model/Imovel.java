package br.com.diogolages.app.model;

import java.util.Objects;
import java.util.UUID;

/**
 * 
 * @author Diogo Lages
 *
 */
public class Imovel {

	private String id;
	private Double valor;
	private String nome;
	private Pessoa proprietario;

	public Imovel(Double valor, String nome) {
		this.id = UUID.randomUUID().toString();
		this.valor = valor;
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pessoa getProprietario() {
		return proprietario;
	}

	public void setProprietario(Pessoa proprietario) {
		this.proprietario = proprietario;
	}
	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imovel other = (Imovel) obj;
		return Objects.equals(id, other.id);
	}
	
}
