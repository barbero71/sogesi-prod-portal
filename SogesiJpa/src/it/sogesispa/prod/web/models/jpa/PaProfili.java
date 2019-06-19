package it.sogesispa.prod.web.models.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PA_PROFILI database table.
 * 
 */
@Entity
@Table(name="PA_PROFILI")
@NamedQuery(name="PaProfili.findAll", query="SELECT p FROM PaProfili p")
public class PaProfili implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String descrizione;

	private String nome;

	//bi-directional many-to-one association to PaRuoliProfili
	@OneToMany(mappedBy="paProfili")
	private List<PaRuoliProfili> paRuoliProfilis;

	public PaProfili() {
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

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<PaRuoliProfili> getPaRuoliProfilis() {
		return this.paRuoliProfilis;
	}

	public void setPaRuoliProfilis(List<PaRuoliProfili> paRuoliProfilis) {
		this.paRuoliProfilis = paRuoliProfilis;
	}

	public PaRuoliProfili addPaRuoliProfili(PaRuoliProfili paRuoliProfili) {
		getPaRuoliProfilis().add(paRuoliProfili);
		paRuoliProfili.setPaProfili(this);

		return paRuoliProfili;
	}

	public PaRuoliProfili removePaRuoliProfili(PaRuoliProfili paRuoliProfili) {
		getPaRuoliProfilis().remove(paRuoliProfili);
		paRuoliProfili.setPaProfili(null);

		return paRuoliProfili;
	}

}