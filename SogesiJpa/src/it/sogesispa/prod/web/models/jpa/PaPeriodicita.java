package it.sogesispa.prod.web.models.jpa;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the PA_PERIODICITA database table.
 * 
 */
@Entity
@Table(name="PA_PERIODICITA")
@NamedQuery(name="PaPeriodicita.findAll", query="SELECT p FROM PaPeriodicita p")
public class PaPeriodicita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String descrizione;
	

	@Column(name="INT_GIORNI")
	private Integer intGiorni;

	//bi-directional many-to-one association to PaPianificazioni
	@OneToMany(mappedBy="paPeriodicita")
	private List<PaPianificazioni> paPianificazionis;

	public PaPeriodicita() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<PaPianificazioni> getPaPianificazionis() {
		return this.paPianificazionis;
	}

	public void setPaPianificazionis(List<PaPianificazioni> paPianificazionis) {
		this.paPianificazionis = paPianificazionis;
	}

	public PaPianificazioni addPaPianificazioni(PaPianificazioni paPianificazioni) {
		getPaPianificazionis().add(paPianificazioni);
		paPianificazioni.setPaPeriodicita(this);

		return paPianificazioni;
	}

	public PaPianificazioni removePaPianificazioni(PaPianificazioni paPianificazioni) {
		getPaPianificazionis().remove(paPianificazioni);
		paPianificazioni.setPaPeriodicita(null);

		return paPianificazioni;
	}

	public Integer getIntGiorni() {
		return intGiorni;
	}

	public void setIntGiorni(Integer intGiorni) {
		this.intGiorni = intGiorni;
	}

	
	
}