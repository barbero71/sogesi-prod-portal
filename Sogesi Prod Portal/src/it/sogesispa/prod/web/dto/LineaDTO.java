package it.sogesispa.prod.web.dto;

import java.io.Serializable;

public class LineaDTO implements Serializable {

	private static final long serialVersionUID = -6619211049837295715L;
	private long id;
	private String name;

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
}