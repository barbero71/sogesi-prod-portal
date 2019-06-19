package it.sogesispa.prod.web.models.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PA_RUOLI_PROFILI database table.
 * 
 */
@Entity
@Table(name="PA_RUOLI_PROFILI")
@NamedQuery(name="PaRuoliProfili.findAll", query="SELECT p FROM PaRuoliProfili p")
public class PaRuoliProfili implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	//bi-directional many-to-one association to PaProfili
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PROFILO")
	private PaProfili paProfili;

	//bi-directional many-to-one association to PaRuoli
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_RUOLO")
	private PaRuoli paRuoli;

	public PaRuoliProfili() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PaProfili getPaProfili() {
		return this.paProfili;
	}

	public void setPaProfili(PaProfili paProfili) {
		this.paProfili = paProfili;
	}

	public PaRuoli getPaRuoli() {
		return this.paRuoli;
	}

	public void setPaRuoli(PaRuoli paRuoli) {
		this.paRuoli = paRuoli;
	}

}