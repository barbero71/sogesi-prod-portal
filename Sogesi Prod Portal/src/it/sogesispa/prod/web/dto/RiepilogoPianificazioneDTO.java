package it.sogesispa.prod.web.dto;

import java.io.Serializable;

public class RiepilogoPianificazioneDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8387928377184465571L;

	
	private String impianto;
	private String priorita;
	private String tipopianificazione;
	private String intervento;
	public String getImpianto() {
		return impianto;
	}
	public void setImpianto(String impianto) {
		this.impianto = impianto;
	}
	public String getPriorita() {
		return priorita;
	}
	public void setPriorita(String priorita) {
		this.priorita = priorita;
	}
	public String getTipopianificazione() {
		return tipopianificazione;
	}
	public void setTipopianificazione(String tipopianificazione) {
		this.tipopianificazione = tipopianificazione;
	}
	public String getIntervento() {
		return intervento;
	}
	public void setIntervento(String intervento) {
		this.intervento = intervento;
	}
	
}
