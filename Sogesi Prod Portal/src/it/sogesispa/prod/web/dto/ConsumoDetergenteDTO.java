package it.sogesispa.prod.web.dto;

import java.io.Serializable;

public class ConsumoDetergenteDTO implements Serializable {

	private static final long serialVersionUID = 700377132899794250L;
	
	private String stabilimento;
	private Long idStabilimento;
	private String nomeProdotto;
	private float quantita;
	private float costoquantitakg;
	private float costokg;
	private float quantitaDesiderata;
	public String getStabilimento() {
		return stabilimento;
	}
	public void setStabilimento(String stabilimento) {
		this.stabilimento = stabilimento;
	}
	public Long getIdStabilimento() {
		return idStabilimento;
	}
	public void setIdStabilimento(Long idStabilimento) {
		this.idStabilimento = idStabilimento;
	}
	public String getNomeProdotto() {
		return nomeProdotto;
	}
	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}
	public float getQuantita() {
		return quantita;
	}
	public void setQuantita(float quantita) {
		this.quantita = quantita;
	}
	public float getCostoquantitakg() {
		return costoquantitakg;
	}
	public void setCostoquantitakg(float costoquantitakg) {
		this.costoquantitakg = costoquantitakg;
	}
	public float getCostokg() {
		return costokg;
	}
	public void setCostokg(float costokg) {
		this.costokg = costokg;
	}
	public float getQuantitaDesiderata() {
		return quantitaDesiderata;
	}
	public void setQuantitaDesiderata(float quantitaDesiderata) {
		this.quantitaDesiderata = quantitaDesiderata;
	}

}
