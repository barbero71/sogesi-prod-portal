package it.sogesispa.prod.web.dto;

import java.io.Serializable;
import java.util.List;

public class DetergentiChartDTO implements Serializable {

	private static final long serialVersionUID = 6482915806074895092L;
	private String idStabilimento;

	private String stabilimento;
	private List<List<String[]>> datap;
	private String xkey;
	private String ykeys;
	private String lineColors;
	private String labels;

	public String getIdStabilimento() {
		return idStabilimento;
	}

	public void setIdStabilimento(String idStabilimento) {
		this.idStabilimento = idStabilimento;
	}

	public String getStabilimento() {
		return stabilimento;
	}

	public void setStabilimento(String stabilimento) {
		this.stabilimento = stabilimento;
	}

	public List<List<String[]>> getDatap() {
		return datap;
	}

	public void setDatap(List<List<String[]>> datap) {
		this.datap = datap;
	}

	public String getXkey() {
		return xkey;
	}

	public void setXkey(String xkey) {
		this.xkey = xkey;
	}

	public String getYkeys() {
		return ykeys;
	}

	public void setYkeys(String ykeys) {
		this.ykeys = ykeys;
	}

	public String getLineColors() {
		return lineColors;
	}

	public void setLineColors(String lineColors) {
		this.lineColors = lineColors;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}
}
