package it.sogesispa.prod.web.models;

import java.util.Date;

public class Charts {
	private int stabId;
	private Date data;
	private int ore;
	private long somma;
	private long progr;
	

	public int getStabId() {
		return stabId;
	}
	public void setStabId(int stabId) {
		this.stabId = stabId;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getOre() {
		return ore;
	}
	public void setOre(int ore) {
		this.ore = ore;
	}
	public long getSomma() {
		return somma;
	}
	public void setSomma(long somma) {
		this.somma = somma;
	}
	public long getProgr() {
		return progr;
	}
	public void setProgr(long progr) {
		this.progr = progr;
	}
	@Override
	public String toString() {
		return "EcolabCharts [stabId=" + stabId + ", data=" + data + ", ore=" + ore + ", somma=" + somma + ", progr="
				+ progr + "]";
	}
	

}
