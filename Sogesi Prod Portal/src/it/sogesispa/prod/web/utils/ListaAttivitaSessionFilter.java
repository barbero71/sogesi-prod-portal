package it.sogesispa.prod.web.utils;

import java.util.Date;
 

public class ListaAttivitaSessionFilter {
	public static int SORT_NONE=0;
	public static int SORT_ASC=1;
	public static int SORT_DESC=2;	
	
	
	private long[] cmbMach;
	private String cmbMachString;
	private String eseguite;
	private Long groupId;
	private Long stabId;
	private Date txtDateFrom;
	private Date txtDateTo;
	private int userAuthLevel;

	private int lineaSort;	
	private int macchinaSort;
	private int nomeIstanzaMacchinaSort;
	private int impiantoSort;	
	private int dataPrevistaSort = 1;	
	private int prioritaSort;	
	private int tipoSort;	
	private int pianificazioneSort;	
	private int interventoSort;	
	private int eseguitaSort;	 
	private int pageSize;

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
	public Date getTxtDateFrom() {
		if(txtDateFrom==null){
			setTxtDateFrom(new Date());
		}
		return txtDateFrom;		
	}
	public void setTxtDateFrom(Date txtDateFrom) {
		this.txtDateFrom = txtDateFrom;
	}
	public Date getTxtDateTo() {
		return txtDateTo;
	}
	public void setTxtDateTo(Date txtDateTo) {
		this.txtDateTo = txtDateTo;
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
	public int getDataPrevistaSort() {
		return dataPrevistaSort;
	}
	public void setDataPrevistaSort(int dataPrevistaSort) {
		this.dataPrevistaSort = dataPrevistaSort;
	}
	public int getPrioritaSort() {
		return prioritaSort;
	}
	public void setPrioritaSort(int prioritaSort) {
		this.prioritaSort = prioritaSort;
	}
	public int getTipoSort() {
		return tipoSort;
	}
	public void setTipoSort(int tipoSort) {
		this.tipoSort = tipoSort;
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
	public int getEseguitaSort() {
		return eseguitaSort;
	}
	public void setEseguitaSort(int eseguitaSort) {
		this.eseguitaSort = eseguitaSort;
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
	public int getNomeIstanzaMacchinaSort() {
		return nomeIstanzaMacchinaSort;
	}
	public void setNomeIstanzaMacchinaSort(int nomeIstanzaMacchinaSort) {
		this.nomeIstanzaMacchinaSort = nomeIstanzaMacchinaSort;
	}
	public String getEseguite() {
		if(eseguite==null){
			eseguite="ALL";
		}
		return eseguite;
	}
	public void setEseguite(String eseguite) {
		this.eseguite = eseguite;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
}
