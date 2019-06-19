package it.sogesispa.prod.web.dto;

import java.io.Serializable;

public class PeriodicitaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1040070749286365697L;

	private long id;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
