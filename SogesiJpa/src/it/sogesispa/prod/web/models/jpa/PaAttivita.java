package it.sogesispa.prod.web.models.jpa;

import java.io.Serializable;
//import java.math.BigDecimal;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PA_ATTIVITA database table.
 * 
 */
@Entity
@Table(name="PA_ATTIVITA")
@NamedQuery(name="PaAttivita.findAll", query="SELECT p FROM PaAttivita p")
public class PaAttivita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="PA_ATTIVITA_SEQ")
	@SequenceGenerator(name="PA_ATTIVITA_SEQ", sequenceName="PA_ATTIVITA_SEQ")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ESECUZIONE")
	private Date dataEsecuzione;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PREVISTA")
	private Date dataPrevista;

	@Column(name="DESCRIZIONE_INTT")
	private String descrizioneIntt;

	@Column(name="NOME_INT")
	private String nomeInt;

	private String note;
	
	@Column(name="ORE_ESECUZIONE")
	private String ore;
	@Column(name="COSTI_ESECUZIONE")
	private String costi;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OPERATORE")
	private TUser operatore;

	//bi-directional many-to-one association to PaAttivita
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PADRE")
	private PaAttivita paAttivita;

	//bi-directional many-to-one association to PaAttivita
	@OneToMany(mappedBy="paAttivita")
	private List<PaAttivita> paAttivitas;

	//bi-directional many-to-one association to PaImpianti
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_IMPIANTO")
	private PaImpianti paImpianti;

	//bi-directional many-to-one association to PaMacchine
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_MACCHINA")
	private PaMacchine paMacchine;

	//bi-directional many-to-one association to PaPianificazioni
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PIANIFICAZIONE")
	private PaPianificazioni paPianificazioni;

	//bi-directional many-to-one association to PaPriorita
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PRIORITA")
	private PaPriorita paPriorita;

	public PaAttivita() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataEsecuzione() {
		return this.dataEsecuzione;
	}

	public void setDataEsecuzione(Date dataEsecuzione) {
		this.dataEsecuzione = dataEsecuzione;
	}

	public Date getDataPrevista() {
		return this.dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public String getDescrizioneIntt() {
		return this.descrizioneIntt;
	}

	public void setDescrizioneIntt(String descrizioneIntt) {
		this.descrizioneIntt = descrizioneIntt;
	}

	public String getNomeInt() {
		return this.nomeInt;
	}

	public void setNomeInt(String nomeInt) {
		this.nomeInt = nomeInt;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public PaAttivita getPaAttivita() {
		return this.paAttivita;
	}

	public void setPaAttivita(PaAttivita paAttivita) {
		this.paAttivita = paAttivita;
	}

	public List<PaAttivita> getPaAttivitas() {
		return this.paAttivitas;
	}

	public void setPaAttivitas(List<PaAttivita> paAttivitas) {
		this.paAttivitas = paAttivitas;
	}

	public PaAttivita addPaAttivita(PaAttivita paAttivita) {
		getPaAttivitas().add(paAttivita);
		paAttivita.setPaAttivita(this);

		return paAttivita;
	}

	public PaAttivita removePaAttivita(PaAttivita paAttivita) {
		getPaAttivitas().remove(paAttivita);
		paAttivita.setPaAttivita(null);

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

	public PaPianificazioni getPaPianificazioni() {
		return this.paPianificazioni;
	}

	public void setPaPianificazioni(PaPianificazioni paPianificazioni) {
		this.paPianificazioni = paPianificazioni;
	}

	public PaPriorita getPaPriorita() {
		return this.paPriorita;
	}

	public void setPaPriorita(PaPriorita paPriorita) {
		this.paPriorita = paPriorita;
	}

	public String getCosti() {
		return costi;
	}

	public void setCosti(String costi) {
		this.costi = costi;
	}

	public String getOre() {
		return ore;
	}

	public void setOre(String ore) {
		this.ore = ore;
	}

	public TUser getOperatore() {
		return operatore;
	}

	public void setOperatore(TUser operatore) {
		this.operatore = operatore;
	}

}