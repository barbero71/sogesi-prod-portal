package it.sogesispa.prod.web.dto;

import java.io.Serializable;

public class OperatoreDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7283462486337819338L;

	private String nome;
	private long id;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
