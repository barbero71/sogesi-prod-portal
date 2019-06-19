package it.sogesispa.prod.web.utils;

import java.util.Calendar;
import java.util.Date;
 

public class ConsumoDetergentiChartSessionFilter {
	public static final int UNITA_MISURA_GIORNI = 1;
	public static final int UNITA_MISURA_SETTIMANE = 2;
	public static final  int UNITA_MISURA_MESI = 3;
	public static final  int UNITA_MISURA_ANNI = 4;
	private Integer unitaMisura;
	private String tipo;
	private String stabId;
	private Date txtDateFrom;
	private Date txtDateTo;
	private int userAuthLevel;
	public Date getTxtDateFrom() {
		if(txtDateFrom==null){
			setTxtDateFrom(new Date());
		}
		return txtDateFrom;		
	}
	public void setTxtDateFrom(Date txtDateFrom) {
		this.txtDateFrom = txtDateFrom;
	}
	public String getStabId() {
		if(stabId==null||stabId.trim().length()==0)setStabId("all");
		return stabId;
	}
	public void setStabId(String stabId) {
		this.stabId = stabId;
	}
	public Date getTxtDateTo() {
		if(txtDateTo==null){
			Calendar cal = Calendar.getInstance();
			//cal.add(Calendar.DAY_OF_YEAR, 1);
			setTxtDateTo(cal.getTime());
		}
		return txtDateTo;
	}
	public void setTxtDateTo(Date txtDateTo) {
		this.txtDateTo = txtDateTo;
	}
	public int getUserAuthLevel() {
		return userAuthLevel;
	}
	public void setUserAuthLevel(int userAuthLevel) {
		this.userAuthLevel = userAuthLevel;
	}
	public String getTipo() {
		if(tipo==null)setTipo("all");
		return tipo;
	}
	public void setTipo(String tipoore) {
		this.tipo = tipoore;
	}
	public Integer getUnitaMisura() {
		if(unitaMisura==null||unitaMisura<1){
			setUnitaMisura(1);
		}
		return unitaMisura;
	}
	public void setUnitaMisura(Integer unitaMisura) {
		this.unitaMisura = unitaMisura;
	}
}
