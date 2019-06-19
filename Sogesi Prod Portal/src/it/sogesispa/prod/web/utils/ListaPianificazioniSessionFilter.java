package it.sogesispa.prod.web.utils;

//import java.util.Date;
 

public class ListaPianificazioniSessionFilter {
	public static int SORT_NONE=0;
	public static int SORT_ASC=1;
	public static int SORT_DESC=2;	
	
	
	private long[] cmbMach;
	private String cmbMachString;
	private Long groupId;
	private Long stabId;
	private Integer periodicita;
	private int userAuthLevel;

	private int lineaSort;	
	private int macchinaSort;	
	private int impiantoSort;	
	private int prioritaSort;	
	private int pianificazioneSort;	
	private int interventoSort;	

	int pageNumber = 1;
	
	
	public long[] getCmbMach() {
		return cmbMach;
	}
	public void setCmbMach(long[] cmbMach) {
		this.cmbMach = cmbMach;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getStabId() {
		return stabId;
	}
	public void setStabId(Long stabId) {
		this.stabId = stabId;
	}
	public int getLineaSort() {
		return lineaSort;
	}
	public void setLineaSort(int lineaSort) {
		this.lineaSort = lineaSort;
	}
	public int getMacchinaSort() {
		return macchinaSort;
	}
	public void setMacchinaSort(int macchinaSort) {
		this.macchinaSort = macchinaSort;
	}
	public int getImpiantoSort() {
		return impiantoSort;
	}
	public void setImpiantoSort(int impiantoSort) {
		this.impiantoSort = impiantoSort;
	}
	
	public int getPrioritaSort() {
		return prioritaSort;
	}
	public void setPrioritaSort(int prioritaSort) {
		this.prioritaSort = prioritaSort;
	}
	
	public int getPianificazioneSort() {
		return pianificazioneSort;
	}
	public void setPianificazioneSort(int pianificazioneSort) {
		this.pianificazioneSort = pianificazioneSort;
	}
	public int getInterventoSort() {
		return interventoSort;
	}
	public void setInterventoSort(int interventoSort) {
		this.interventoSort = interventoSort;
	}

	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	/*public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}*/
	public String getCmbMachString() {
		return cmbMachString;
	}
	public void setCmbMachString(String cmbMachString) {
		this.cmbMachString = cmbMachString;
	}
	public int getUserAuthLevel() {
		return userAuthLevel;
	}
	public void setUserAuthLevel(int userAuthLevel) {
		this.userAuthLevel = userAuthLevel;
	}
	public Integer getPeriodicita() {
		return periodicita;
	}
	public void setPeriodicita(Integer periodicita) {
		this.periodicita = periodicita;
	}
	
	
	
}
