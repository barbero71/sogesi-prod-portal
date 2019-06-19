package it.sogesispa.prod.web.models.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the T_PLANTS database table.
 * 
 */
@Entity
@Table(name="T_PLANTS")
@NamedQuery(name="TPlant.findAll", query="SELECT t FROM TPlant t")
public class TPlant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PLANT_ID")
	private long plantId;

	private BigDecimal ecolab;

	@Column(name="PLANT_CDC")
	private String plantCdc;

	@Column(name="PLANT_DESC")
	private String plantDesc;

	private BigDecimal prod;

	@Column(name="TI_NUMBER")
	private BigDecimal tiNumber;

	//bi-directional many-to-one association to PaMacchine
	@OneToMany(mappedBy="TPlant")
	private List<PaMacchine> paMacchines;

	public TPlant() {
	}

	public long getPlantId() {
		return this.plantId;
	}

	public void setPlantId(long plantId) {
		this.plantId = plantId;
	}

	public BigDecimal getEcolab() {
		return this.ecolab;
	}

	public void setEcolab(BigDecimal ecolab) {
		this.ecolab = ecolab;
	}

	public String getPlantCdc() {
		return this.plantCdc;
	}

	public void setPlantCdc(String plantCdc) {
		this.plantCdc = plantCdc;
	}

	public String getPlantDesc() {
		return this.plantDesc;
	}

	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}

	public BigDecimal getProd() {
		return this.prod;
	}

	public void setProd(BigDecimal prod) {
		this.prod = prod;
	}

	public BigDecimal getTiNumber() {
		return this.tiNumber;
	}

	public void setTiNumber(BigDecimal tiNumber) {
		this.tiNumber = tiNumber;
	}

	public List<PaMacchine> getPaMacchines() {
		return this.paMacchines;
	}

	public void setPaMacchines(List<PaMacchine> paMacchines) {
		this.paMacchines = paMacchines;
	}

	public PaMacchine addPaMacchine(PaMacchine paMacchine) {
		getPaMacchines().add(paMacchine);
		paMacchine.setTPlant(this);

		return paMacchine;
	}

	public PaMacchine removePaMacchine(PaMacchine paMacchine) {
		getPaMacchines().remove(paMacchine);
		paMacchine.setTPlant(null);

		return paMacchine;
	}

}