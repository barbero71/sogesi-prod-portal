package it.sogesispa.prod.web.dto;

import java.io.Serializable;

public class OreLavorateDTO implements Serializable{

	private static final long serialVersionUID = 8522886727707094366L;
	
	private String stabilimento;
	private String cdcStabilimento;
	private float oreLavorateOrdinarie;
	private float oreStraordinario;
	private float oreFerie;
	private float oreMaternita;
	private float malattie;
	private float infortuni;
	private float legge104;
	
	public String getStabilimento() {
		return stabilimento;
	}
	public void setStabilimento(String stabilimento) {
		this.stabilimento = stabilimento;
	}
	public float getOreLavorateOrdinarie() {
		return oreLavorateOrdinarie;
	}
	public void setOreLavorateOrdinarie(float oreLavorateOrdinarie) {
		this.oreLavorateOrdinarie = oreLavorateOrdinarie;
	}
	public float getOreStraordinario() {
		return oreStraordinario;
	}
	public void setOreStraordinario(float oreStraordinario) {
		this.oreStraordinario = oreStraordinario;
	}
	public float getOreFerie() {
		return oreFerie;
	}
	public void setOreFerie(float oreFerie) {
		this.oreFerie = oreFerie;
	}
	public float getOreMaternita() {
		return oreMaternita;
	}
	public void setOreMaternita(float oreMaternita) {
		this.oreMaternita = oreMaternita;
	}
	public float getMalattie() {
		return malattie;
	}
	public void setMalattie(float malattie) {
		this.malattie = malattie;
	}
	public float getInfortuni() {
		return infortuni;
	}
	public void setInfortuni(float infortuni) {
		this.infortuni = infortuni;
	}
	public float getLegge104() {
		return legge104;
	}
	public void setLegge104(float legge104) {
		this.legge104 = legge104;
	}
	public String getCdcStabilimento() {
		return cdcStabilimento;
	}
	public void setCdcStabilimento(String cdcStabilimento) {
		this.cdcStabilimento = cdcStabilimento;
	}
}
