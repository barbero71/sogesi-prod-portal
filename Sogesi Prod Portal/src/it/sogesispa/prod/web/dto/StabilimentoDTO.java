package it.sogesispa.prod.web.dto;

import java.io.Serializable;

public class StabilimentoDTO implements Serializable {

	private static final long serialVersionUID = 8175187500993901255L;
	private long id;
	private String name;
	private String cdc;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCdc() {
		return cdc;
	}

	public void setCdc(String cdc) {
		this.cdc = cdc;
	}
}
