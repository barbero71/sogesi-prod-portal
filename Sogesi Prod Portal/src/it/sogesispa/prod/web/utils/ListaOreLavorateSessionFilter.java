package it.sogesispa.prod.web.utils;

import java.util.Calendar;
import java.util.Date;
 

public class ListaOreLavorateSessionFilter {
	public static int SORT_NONE=0;
	public static int SORT_ASC=1;
	public static int SORT_DESC=2;	
	
	private String tipoore;
	private String stabId;
	private Date txtDateFrom;
	private Date txtDateTo;
	private int userAuthLevel;

	private int stabilimentoSort;
	private int oreLavorateOrdinarieSort;	
	private int oreStraordinarioSort;
	private int oreFerieSort;
	private int oreMaternitaSort;
	private int malattieSort;
	private int infortuniSort;
	private int legge104Sort;

	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	int pageNumber = 1;
	
	
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
	public int getOreLavorateOrdinarieSort() {
		return oreLavorateOrdinarieSort;
	}
	public void setOreLavorateOrdinarieSort(int oreLavorateOrdinarieSort) {
		this.oreLavorateOrdinarieSort = oreLavorateOrdinarieSort;
	}
	public int getOreStraordinarioSort() {
		return oreStraordinarioSort;
	}
	public void setOreStraordinarioSort(int oreStraordinarioSort) {
		this.oreStraordinarioSort = oreStraordinarioSort;
	}
	public int getOreFerieSort() {
		return oreFerieSort;
	}
	public void setOreFerieSort(int oreFerieSort) {
		this.oreFerieSort = oreFerieSort;
	}
	public int getOreMaternitaSort() {
		return oreMaternitaSort;
	}
	public void setOreMaternitaSort(int oreMaternitaSort) {
		this.oreMaternitaSort = oreMaternitaSort;
	}
	public int getMalattieSort() {
		return malattieSort;
	}
	public void setMalattieSort(int malattieSort) {
		this.malattieSort = malattieSort;
	}
	public int getInfortuniSort() {
		return infortuniSort;
	}
	public void setInfortuniSort(int infortuniSort) {
		this.infortuniSort = infortuniSort;
	}
	public int getLegge104Sort() {
		return legge104Sort;
	}
	public void setLegge104Sort(int legge104Sort) {
		this.legge104Sort = legge104Sort;
	}
	public int getStabilimentoSort() {
		return stabilimentoSort;
	}
	public void setStabilimentoSort(int stabilimentoSort) {
		this.stabilimentoSort = stabilimentoSort;
	}
	public String getTipoore() {
		if(tipoore==null||tipoore.trim().length()==0)setTipoore("all");
		return tipoore;
	}
	public void setTipoore(String tipoore) {
		this.tipoore = tipoore;
	}
	
}
