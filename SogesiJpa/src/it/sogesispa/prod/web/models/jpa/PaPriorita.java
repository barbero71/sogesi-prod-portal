package it.sogesispa.prod.web.models.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PA_PRIORITA database table.
 * 
 */
@Entity
@Table(name="PA_PRIORITA")
@NamedQuery(name="PaPriorita.findAll", query="SELECT p FROM PaPriorita p")
public class PaPriorita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String livello;

	//bi-directional many-to-one association to PaAttivita
	@OneToMany(mappedBy="paPriorita")
	private List<PaAttivita> paAttivitas;

	//bi-directional many-to-one association to PaPianificazioni
	@OneToMany(mappedBy="paPriorita")
	private List<PaPianificazioni> paPianificazionis;

	public PaPriorita() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLivello() {
		return this.livello;
	}

	public void setLivello(String livello) {
		this.livello = livello;
	}

	public List<PaAttivita> getPaAttivitas() {
		return this.paAttivitas;
	}

	public void setPaAttivitas(List<PaAttivita> paAttivitas) {
		this.paAttivitas = paAttivitas;
	}

	public PaAttivita addPaAttivita(PaAttivita paAttivita) {
		getPaAttivitas().add(paAttivita);
		paAttivita.setPaPriorita(this);

		return paAttivita;
	}

	public PaAttivita removePaAttivita(PaAttivita paAttivita) {
		getPaAttivitas().remove(paAttivita);
		paAttivita.setPaPriorita(null);

		return paAttivita;
	}

	public List<PaPianificazioni> getPaPianificazionis() {
		return this.paPianificazionis;
	}

	public void setPaPianificazionis(List<PaPianificazioni> paPianificazionis) {
		this.paPianificazionis = paPianificazionis;
	}

	public PaPianificazioni addPaPianificazioni(PaPianificazioni paPianificazioni) {
		getPaPianificazionis().add(paPianificazioni);
		paPianificazioni.setPaPriorita(this);

		return paPianificazioni;
	}

	public PaPianificazioni removePaPianificazioni(PaPianificazioni paPianificazioni) {
		getPaPianificazionis().remove(paPianificazioni);
		paPianificazioni.setPaPriorita(null);

		return paPianificazioni;
	}

}