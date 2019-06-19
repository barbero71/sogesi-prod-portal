package it.sogesispa.prod.web.models.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PA_IMPIANTI database table.
 * 
 */
@Entity
@Table(name="PA_IMPIANTI")
@NamedQuery(name="PaImpianti.findAll", query="SELECT p FROM PaImpianti p")
public class PaImpianti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String descrizione;

	//bi-directional many-to-one association to PaAttivita
	@OneToMany(mappedBy="paImpianti")
	private List<PaAttivita> paAttivitas;

	//bi-directional many-to-one association to PaCompImpTMacchina
	@OneToMany(mappedBy="paImpianti")
	private List<PaCompImpTMacchina> paCompImpTMacchinas;

	//bi-directional many-to-one association to PaImpiantoTMacchina
	@OneToMany(mappedBy="paImpianti")
	private List<PaImpiantoTMacchina> paImpiantoTMacchinas;

	//bi-directional many-to-one association to PaPianificazioni
	@OneToMany(mappedBy="paImpianti")
	private List<PaPianificazioni> paPianificazionis;

	public PaImpianti() {
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

	public List<PaAttivita> getPaAttivitas() {
		return this.paAttivitas;
	}

	public void setPaAttivitas(List<PaAttivita> paAttivitas) {
		this.paAttivitas = paAttivitas;
	}

	public PaAttivita addPaAttivita(PaAttivita paAttivita) {
		getPaAttivitas().add(paAttivita);
		paAttivita.setPaImpianti(this);

		return paAttivita;
	}

	public PaAttivita removePaAttivita(PaAttivita paAttivita) {
		getPaAttivitas().remove(paAttivita);
		paAttivita.setPaImpianti(null);

		return paAttivita;
	}

	public List<PaCompImpTMacchina> getPaCompImpTMacchinas() {
		return this.paCompImpTMacchinas;
	}

	public void setPaCompImpTMacchinas(List<PaCompImpTMacchina> paCompImpTMacchinas) {
		this.paCompImpTMacchinas = paCompImpTMacchinas;
	}

	public PaCompImpTMacchina addPaCompImpTMacchina(PaCompImpTMacchina paCompImpTMacchina) {
		getPaCompImpTMacchinas().add(paCompImpTMacchina);
		paCompImpTMacchina.setPaImpianti(this);

		return paCompImpTMacchina;
	}

	public PaCompImpTMacchina removePaCompImpTMacchina(PaCompImpTMacchina paCompImpTMacchina) {
		getPaCompImpTMacchinas().remove(paCompImpTMacchina);
		paCompImpTMacchina.setPaImpianti(null);

		return paCompImpTMacchina;
	}

	public List<PaImpiantoTMacchina> getPaImpiantoTMacchinas() {
		return this.paImpiantoTMacchinas;
	}

	public void setPaImpiantoTMacchinas(List<PaImpiantoTMacchina> paImpiantoTMacchinas) {
		this.paImpiantoTMacchinas = paImpiantoTMacchinas;
	}

	public PaImpiantoTMacchina addPaImpiantoTMacchina(PaImpiantoTMacchina paImpiantoTMacchina) {
		getPaImpiantoTMacchinas().add(paImpiantoTMacchina);
		paImpiantoTMacchina.setPaImpianti(this);

		return paImpiantoTMacchina;
	}

	public PaImpiantoTMacchina removePaImpiantoTMacchina(PaImpiantoTMacchina paImpiantoTMacchina) {
		getPaImpiantoTMacchinas().remove(paImpiantoTMacchina);
		paImpiantoTMacchina.setPaImpianti(null);

		return paImpiantoTMacchina;
	}

	public List<PaPianificazioni> getPaPianificazionis() {
		return this.paPianificazionis;
	}

	public void setPaPianificazionis(List<PaPianificazioni> paPianificazionis) {
		this.paPianificazionis = paPianificazionis;
	}

	public PaPianificazioni addPaPianificazioni(PaPianificazioni paPianificazioni) {
		getPaPianificazionis().add(paPianificazioni);
		paPianificazioni.setPaImpianti(this);

		return paPianificazioni;
	}

	public PaPianificazioni removePaPianificazioni(PaPianificazioni paPianificazioni) {
		getPaPianificazionis().remove(paPianificazioni);
		paPianificazioni.setPaImpianti(null);

		return paPianificazioni;
	}

}