package it.sogesispa.prod.web.models.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PA_RUOLI database table.
 * 
 */
@Entity
@Table(name="PA_RUOLI")
@NamedQuery(name="PaRuoli.findAll", query="SELECT p FROM PaRuoli p")
public class PaRuoli implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String descrizione;

	//bi-directional many-to-one association to PaRuoliProfili
	@OneToMany(mappedBy="paRuoli")
	private List<PaRuoliProfili> paRuoliProfilis;

	//bi-directional many-to-one association to PaUtenti
	@OneToMany(mappedBy="paRuoli")
	private List<PaUtenti> paUtentis;

	//bi-directional many-to-one association to TUser
	@OneToMany(mappedBy="paRuoli")
	private List<TUser> TUsers;

	public PaRuoli() {
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

	public List<PaRuoliProfili> getPaRuoliProfilis() {
		return this.paRuoliProfilis;
	}

	public void setPaRuoliProfilis(List<PaRuoliProfili> paRuoliProfilis) {
		this.paRuoliProfilis = paRuoliProfilis;
	}

	public PaRuoliProfili addPaRuoliProfili(PaRuoliProfili paRuoliProfili) {
		getPaRuoliProfilis().add(paRuoliProfili);
		paRuoliProfili.setPaRuoli(this);

		return paRuoliProfili;
	}

	public PaRuoliProfili removePaRuoliProfili(PaRuoliProfili paRuoliProfili) {
		getPaRuoliProfilis().remove(paRuoliProfili);
		paRuoliProfili.setPaRuoli(null);

		return paRuoliProfili;
	}

	public List<PaUtenti> getPaUtentis() {
		return this.paUtentis;
	}

	public void setPaUtentis(List<PaUtenti> paUtentis) {
		this.paUtentis = paUtentis;
	}

	public PaUtenti addPaUtenti(PaUtenti paUtenti) {
		getPaUtentis().add(paUtenti);
		paUtenti.setPaRuoli(this);

		return paUtenti;
	}

	public PaUtenti removePaUtenti(PaUtenti paUtenti) {
		getPaUtentis().remove(paUtenti);
		paUtenti.setPaRuoli(null);

		return paUtenti;
	}

	public List<TUser> getTUsers() {
		return this.TUsers;
	}

	public void setTUsers(List<TUser> TUsers) {
		this.TUsers = TUsers;
	}

	public TUser addTUser(TUser TUser) {
		getTUsers().add(TUser);
		TUser.setPaRuoli(this);

		return TUser;
	}

	public TUser removeTUser(TUser TUser) {
		getTUsers().remove(TUser);
		TUser.setPaRuoli(null);

		return TUser;
	}

}