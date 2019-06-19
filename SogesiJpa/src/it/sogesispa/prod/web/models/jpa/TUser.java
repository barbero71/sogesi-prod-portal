package it.sogesispa.prod.web.models.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the T_USERS database table.
 * 
 */
@Entity
@Table(name="T_USERS")
@NamedQuery(name="TUser.findAll", query="SELECT t FROM TUser t")
public class TUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private long userId;

	private String email;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_NAME")
	private String lastName;

	private String password;

	@Column(name="PLANT_ACCESS")
	private BigDecimal plantAccess;

	@Column(name="USER_NAME")
	private String userName;

	//bi-directional many-to-one association to PaRuoli
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_RUOLO")
	private PaRuoli paRuoli;

	public TUser() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getPlantAccess() {
		return this.plantAccess;
	}

	public void setPlantAccess(BigDecimal plantAccess) {
		this.plantAccess = plantAccess;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public PaRuoli getPaRuoli() {
		return this.paRuoli;
	}

	public void setPaRuoli(PaRuoli paRuoli) {
		this.paRuoli = paRuoli;
	}

}