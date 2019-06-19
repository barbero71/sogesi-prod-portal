package it.sogesispa.prod.web.models.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PA_IMPIANTO_T_MACCHINA database table.
 * 
 */
@Entity
@Table(name="PA_IMPIANTO_T_MACCHINA")
@NamedQuery(name="PaImpiantoTMacchina.findAll", query="SELECT p FROM PaImpiantoTMacchina p")
public class PaImpiantoTMacchina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	//bi-directional many-to-one association to PaImpianti
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_IMPIANTO")
	private PaImpianti paImpianti;

	//bi-directional many-to-one association to PaTipiMacchina
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_TIPO_MACCHINA")
	private PaTipiMacchina paTipiMacchina;

	public PaImpiantoTMacchina() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PaImpianti getPaImpianti() {
		return this.paImpianti;
	}

	public void setPaImpianti(PaImpianti paImpianti) {
		this.paImpianti = paImpianti;
	}

	public PaTipiMacchina getPaTipiMacchina() {
		return this.paTipiMacchina;
	}

	public void setPaTipiMacchina(PaTipiMacchina paTipiMacchina) {
		this.paTipiMacchina = paTipiMacchina;
	}

}