package it.sogesispa.prod.web.models.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PA_PIANIFICAZIONI database table.
 * 
 */
@Entity
@Table(name="PA_PIANIFICAZIONI")
@NamedQuery(name="PaPianificazioni.findAll", query="SELECT p FROM PaPianificazioni p")
public class PaPianificazioni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="PA_PIAN_ATTIVITA_SEQ")
	@SequenceGenerator(name="PA_PIAN_ATTIVITA_SEQ", sequenceName="PA_PIAN_ATTIVITA_SEQ")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PRIMO_INTERVENTO")
	private Date dataPrimoIntervento;

	private BigDecimal deleted;

	@Column(name="DESCRIZIONE_INT")
	private String descrizioneInt;

	@Column(name="NOME_INT")
	private String nomeInt;

	//bi-directional many-to-one association to PaAttivita
	@OneToMany(mappedBy="paPianificazioni")
	private List<PaAttivita> paAttivitas;

	//bi-directional many-to-one association to PaImpianti
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_IMPIANTO")
	private PaImpianti paImpianti;

	//bi-directional many-to-one association to PaMacchine
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_MACCHINA")
	private PaMacchine paMacchine;

	//bi-directional many-to-one association to PaPeriodicita
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PERIODICITA")
	private PaPeriodicita paPeriodicita;

	//bi-directional many-to-one association to PaPriorita
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PRIORITA")
	private PaPriorita paPriorita;

	public PaPianificazioni() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataPrimoIntervento() {
		return this.dataPrimoIntervento;
	}

	public void setDataPrimoIntervento(Date dataPrimoIntervento) {
		this.dataPrimoIntervento = dataPrimoIntervento;
	}

	public BigDecimal getDeleted() {
		return this.deleted;
	}

	public void setDeleted(BigDecimal deleted) {
		this.deleted = deleted;
	}

	public String getDescrizioneInt() {
		return this.descrizioneInt;
	}

	public void setDescrizioneInt(String descrizioneInt) {
		this.descrizioneInt = descrizioneInt;
	}

	public String getNomeInt() {
		return this.nomeInt;
	}

	public void setNomeInt(String nomeInt) {
		this.nomeInt = nomeInt;
	}

	public List<PaAttivita> getPaAttivitas() {
		return this.paAttivitas;
	}

	public void setPaAttivitas(List<PaAttivita> paAttivitas) {
		this.paAttivitas = paAttivitas;
	}

	public PaAttivita addPaAttivita(PaAttivita paAttivita) {
		getPaAttivitas().add(paAttivita);
		paAttivita.setPaPianificazioni(this);

		return paAttivita;
	}

	public PaAttivita removePaAttivita(PaAttivita paAttivita) {
		getPaAttivitas().remove(paAttivita);
		paAttivita.setPaPianificazioni(null);

		return paAttivita;
	}

	public PaImpianti getPaImpianti() {
		return this.paImpianti;
	}

	public void setPaImpianti(PaImpianti paImpianti) {
		this.paImpianti = paImpianti;
	}

	public PaMacchine getPaMacchine() {
		return this.paMacchine;
	}

	public void setPaMacchine(PaMacchine paMacchine) {
		this.paMacchine = paMacchine;
	}

	public PaPeriodicita getPaPeriodicita() {
		return this.paPeriodicita;
	}

	public void setPaPeriodicita(PaPeriodicita paPeriodicita) {
		this.paPeriodicita = paPeriodicita;
	}

	public PaPriorita getPaPriorita() {
		return this.paPriorita;
	}

	public void setPaPriorita(PaPriorita paPriorita) {
		this.paPriorita = paPriorita;
	}

}