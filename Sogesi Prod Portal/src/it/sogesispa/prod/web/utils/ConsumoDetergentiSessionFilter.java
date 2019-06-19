package it.sogesispa.prod.web.utils;

import java.util.Calendar;
import java.util.Date;
 

public class ConsumoDetergentiSessionFilter {
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
}
