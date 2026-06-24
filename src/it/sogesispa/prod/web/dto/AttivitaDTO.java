package it.sogesispa.prod.web.dto;

import java.io.Serializable;
import java.util.Date;

public class AttivitaDTO implements Serializable {

	private static final long serialVersionUID = -6237962961264605659L;
	private long idAttivita;
	private String linea;
	private String macchina;
	private String impianto;
	private Date dataPrevista;
	private String priorita;
	private String tipoPianificazione;
	private String intervento;
	private String nomeIntervento;
	private boolean eseguita;
	private Date dataEsecuzione;
	private String oreImpiegate;
	private String costi;
	private Long operatore;
	private String nomeOperatore;
	private String note;
	private String stabilimento;
	
	private long idLinea;
	private long idStabilimento;
	private long idMacchina;
	private long idImpianto;
	
	public long getIdAttivita() {
		return idAttivita;
	}
	public long getIdLinea() {
		return idLinea;
	}
	public void setIdLinea(long idLinea) {
		this.idLinea = idLinea;
	}
	public long getIdStabilimento() {
		return idStabilimento;
	}
	public void setIdStabilimento(long idStabilimento) {
		this.idStabilimento = idStabilimento;
	}
	public long getIdMacchina() {
		return idMacchina;
	}
	public void setIdMacchina(long idMacchina) {
		this.idMacchina = idMacchina;
	}
	public long getIdImpianto() {
		return idImpianto;
	}
	public void setIdImpianto(long idImpianto) {
		this.idImpianto = idImpianto;
	}
	public void setIdAttivita(long idAttivita) {
		this.idAttivita = idAttivita;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public String getMacchina() {
		return macchina;
	}
	public void setMacchina(String macchina) {
		this.macchina = macchina;
	}
	public String getImpianto() {
		return impianto;
	}
	public void setImpianto(String impianto) {
		this.impianto = impianto;
	}
	public Date getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public String getPriorita() {
		return priorita;
	}
	public void setPriorita(String priorita) {
		this.priorita = priorita;
	}
	public String getTipoPianificazione() {
		return tipoPianificazione;
	}
	public void setTipoPianificazione(String tipoPianificazione) {
		this.tipoPianificazione = tipoPianificazione;
	}
	public String getIntervento() {
		return intervento;
	}
	public void setIntervento(String intervento) {
		this.intervento = intervento;
	}
	public boolean isEseguita() {
		return eseguita;
	}
	public void setEseguita(boolean eseguita) {
		this.eseguita = eseguita;
	}

	public Date getDataEsecuzione() {
		return dataEsecuzione;
	}
	public void setDataEsecuzione(Date dataEsecuzione) {
		this.dataEsecuzione = dataEsecuzione;
	}
	public String getOreImpiegate() {
		return oreImpiegate;
	}
	public void setOreImpiegate(String oreImpiegate) {
		this.oreImpiegate = oreImpiegate;
	}
	public String getCosti() {
		return costi;
	}
	public void setCosti(String costi) {
		this.costi = costi;
	}
	public Long getOperatore() {
		return operatore;
	}
	public void setOperatore(Long operatore) {
		this.operatore = operatore;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getStabilimento() {
		return stabilimento;
	}
	public void setStabilimento(String stabilimento) {
		this.stabilimento = stabilimento;
	}
	public String getNomeIntervento() {
		return nomeIntervento;
	}
	public void setNomeIntervento(String nomeIntervento) {
		this.nomeIntervento = nomeIntervento;
	}
	public String getNomeOperatore() {
		return nomeOperatore;
	}
	public void setNomeOperatore(String nomeOperatore) {
		this.nomeOperatore = nomeOperatore;
	}

}
