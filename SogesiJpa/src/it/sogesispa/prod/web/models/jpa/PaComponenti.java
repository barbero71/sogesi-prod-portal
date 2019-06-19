package it.sogesispa.prod.web.models.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PA_COMPONENTI database table.
 * 
 */
@Entity
@Table(name="PA_COMPONENTI")
@NamedQuery(name="PaComponenti.findAll", query="SELECT p FROM PaComponenti p")
public class PaComponenti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String codice;

	private String descrizione;

	//bi-directional many-to-one association to PaCompImpTMacchina
	@OneToMany(mappedBy="paComponenti")
	private List<PaCompImpTMacchina> paCompImpTMacchinas;

	public PaComponenti() {
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
		paCompImpTMacchina.setPaComponenti(this);

		return paCompImpTMacchina;
	}

	public PaCompImpTMacchina removePaCompImpTMacchina(PaCompImpTMacchina paCompImpTMacchina) {
		getPaCompImpTMacchinas().remove(paCompImpTMacchina);
		paCompImpTMacchina.setPaComponenti(null);

		return paCompImpTMacchina;
	}

}