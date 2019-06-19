package it.sogesispa.prod.web.models.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PA_TIPI_MACCHINA database table.
 * 
 */
@Entity
@Table(name="PA_TIPI_MACCHINA")
@NamedQuery(name="PaTipiMacchina.findAll", query="SELECT p FROM PaTipiMacchina p")
public class PaTipiMacchina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String codice;

	private String descrizione;

	//bi-directional many-to-one association to PaCompImpTMacchina
	@OneToMany(mappedBy="paTipiMacchina")
	private List<PaCompImpTMacchina> paCompImpTMacchinas;

	//bi-directional many-to-one association to PaImpiantoTMacchina
	@OneToMany(mappedBy="paTipiMacchina")
	private List<PaImpiantoTMacchina> paImpiantoTMacchinas;

	//bi-directional many-to-one association to PaMacchine
	@OneToMany(mappedBy="paTipiMacchina")
	private List<PaMacchine> paMacchines;



	public PaTipiMacchina() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<PaCompImpTMacchina> getPaCompImpTMacchinas() {
		return this.paCompImpTMacchinas;
	}

	public void setPaCompImpTMacchinas(List<PaCompImpTMacchina> paCompImpTMacchinas) {
		this.paCompImpTMacchinas = paCompImpTMacchinas;
	}

	public PaCompImpTMacchina addPaCompImpTMacchina(PaCompImpTMacchina paCompImpTMacchina) {
		getPaCompImpTMacchinas().add(paCompImpTMacchina);
		paCompImpTMacchina.setPaTipiMacchina(this);

		return paCompImpTMacchina;
	}

	public PaCompImpTMacchina removePaCompImpTMacchina(PaCompImpTMacchina paCompImpTMacchina) {
		getPaCompImpTMacchinas().remove(paCompImpTMacchina);
		paCompImpTMacchina.setPaTipiMacchina(null);

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
		paImpiantoTMacchina.setPaTipiMacchina(this);

		return paImpiantoTMacchina;
	}

	public PaImpiantoTMacchina removePaImpiantoTMacchina(PaImpiantoTMacchina paImpiantoTMacchina) {
		getPaImpiantoTMacchinas().remove(paImpiantoTMacchina);
		paImpiantoTMacchina.setPaTipiMacchina(null);

		return paImpiantoTMacchina;
	}

	public List<PaMacchine> getPaMacchines() {
		return this.paMacchines;
	}

	public void setPaMacchines(List<PaMacchine> paMacchines) {
		this.paMacchines = paMacchines;
	}

	public PaMacchine addPaMacchine(PaMacchine paMacchine) {
		getPaMacchines().add(paMacchine);
		paMacchine.setPaTipiMacchina(this);

		return paMacchine;
	}

	public PaMacchine removePaMacchine(PaMacchine paMacchine) {
		getPaMacchines().remove(paMacchine);
		paMacchine.setPaTipiMacchina(null);

		return paMacchine;
	}



}