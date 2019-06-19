package it.sogesispa.prod.web.models.jpa;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PA_MACCHINE database table.
 * 
 */
@Entity
@Table(name="PA_MACCHINE")
@NamedQuery(name="PaMacchine.findAll", query="SELECT p FROM PaMacchine p")
public class PaMacchine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String descrizione;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ACQUISTO")
	private Date dataAcquisto;

	//bi-directional many-to-one association to PaAttivita
	@OneToMany(mappedBy="paMacchine")
	private List<PaAttivita> paAttivitas;

	//bi-directional many-to-one association to PaTipiMacchina
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_TIPO_MACCHINA")
	private PaTipiMacchina paTipiMacchina;

	//bi-directional many-to-one association to TPlant
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PLAN")
	private TPlant TPlant;

	//bi-directional many-to-one association to PaPianificazioni
	@OneToMany(mappedBy="paMacchine")
	private List<PaPianificazioni> paPianificazionis;
	
	//bi-directional many-to-one association to PaLinea
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_LINEA")
	private PaLinea paLinea;

	public PaMacchine() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataAcquisto() {
		return this.dataAcquisto;
	}

	public void setDataAcquisto(Date dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public List<PaAttivita> getPaAttivitas() {
		return this.paAttivitas;
	}

	public void setPaAttivitas(List<PaAttivita> paAttivitas) {
		this.paAttivitas = paAttivitas;
	}

	public PaAttivita addPaAttivita(PaAttivita paAttivita) {
		getPaAttivitas().add(paAttivita);
		paAttivita.setPaMacchine(this);

		return paAttivita;
	}

	public PaAttivita removePaAttivita(PaAttivita paAttivita) {
		getPaAttivitas().remove(paAttivita);
		paAttivita.setPaMacchine(null);

		return paAttivita;
	}

	public PaTipiMacchina getPaTipiMacchina() {
		return this.paTipiMacchina;
	}

	public void setPaTipiMacchina(PaTipiMacchina paTipiMacchina) {
		this.paTipiMacchina = paTipiMacchina;
	}

	public TPlant getTPlant() {
		return this.TPlant;
	}

	public void setTPlant(TPlant TPlant) {
		this.TPlant = TPlant;
	}

	public List<PaPianificazioni> getPaPianificazionis() {
		return this.paPianificazionis;
	}

	public void setPaPianificazionis(List<PaPianificazioni> paPianificazionis) {
		this.paPianificazionis = paPianificazionis;
	}

	public PaPianificazioni addPaPianificazioni(PaPianificazioni paPianificazioni) {
		getPaPianificazionis().add(paPianificazioni);
		paPianificazioni.setPaMacchine(this);

		return paPianificazioni;
	}

	public PaPianificazioni removePaPianificazioni(PaPianificazioni paPianificazioni) {
		getPaPianificazionis().remove(paPianificazioni);
		paPianificazioni.setPaMacchine(null);

		return paPianificazioni;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public PaLinea getPaLinea() {
		return this.paLinea;
	}

	public void setPaLinea(PaLinea paLinea) {
		this.paLinea = paLinea;
	}

}