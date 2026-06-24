package it.sogesispa.prod.web.dto;

import java.io.Serializable;
import java.util.Date;

public class PianificazioneAttivitaDTO implements Serializable{

	private static final long serialVersionUID = 7675797199097792788L;
	
	private long id;
	private String stabilimento;
	private String linea;
	private String macchina;
	private String impianto;
	private long codPriorita;
	private String priorita;
	private String tipoPianificazione;
	private String intervento;
	private long codPeriodicita;
	private String periodicita;
	private Date dataInizio;
	private String nome;
	private String descrizione;
	
	private long idLinea;
	private long idStabilimento;
	private long idMacchina;
	private long idImpianto;
	
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
	public long getCodPriorita() {
		return codPriorita;
	}
	public void setCodPriorita(long codPriorita) {
		this.codPriorita = codPriorita;
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
	public long getCodPeriodicita() {
		return codPeriodicita;
	}
	public void setCodPeriodicita(long codPeriodicita) {
		this.codPeriodicita = codPeriodicita;
	}
	public String getPeriodicita() {
		return periodicita;
	}
	public void setPeriodicita(String periodicita) {
		this.periodicita = periodicita;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getStabilimento() {
		return stabilimento;
	}
	public void setStabilimento(String stabilimento) {
		this.stabilimento = stabilimento;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
