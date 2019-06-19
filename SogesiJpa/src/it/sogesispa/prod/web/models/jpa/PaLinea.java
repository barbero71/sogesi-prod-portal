package it.sogesispa.prod.web.models.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PA_LINEA database table.
 * 
 */
@Entity
@Table(name="PA_LINEA")
@NamedQuery(name="PaLinea.findAll", query="SELECT p FROM PaLinea p")
public class PaLinea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String descrizione;

	//bi-directional many-to-one association to PaTipiMacchina
	@OneToMany(mappedBy="paLinea")
	private List<PaMacchine> paMacchinas;

	public PaLinea() {
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

	public List<PaMacchine> getPaMacchinas() {
		return this.paMacchinas;
	}

	public void setPaMacchinas(List<PaMacchine> paMacchinas) {
		this.paMacchinas = paMacchinas;
	}

	public PaMacchine addPaMacchina(PaMacchine paMacchina) {
		getPaMacchinas().add(paMacchina);
		paMacchina.setPaLinea(this);

		return paMacchina;
	}

	public PaMacchine removePaMacchina(PaMacchine paMacchina) {
		getPaMacchinas().remove(paMacchina);
		paMacchina.setPaLinea(null);

		return paMacchina;
	}

}