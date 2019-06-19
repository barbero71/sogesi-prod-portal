package it.sogesispa.prod.web.utils;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List; 

public class Page {
	@SuppressWarnings("rawtypes")
	List objectList;
	long pageNumber;
	long pageSize;
	long totalRows;
	long totalPages;
	
	
	public Page(@SuppressWarnings("rawtypes") List objectList, long pageNumber, long pageSize, long totalRows) {
		super();
		this.objectList = objectList;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalRows = totalRows;
		this.totalPages = (totalRows - 1)/ pageSize + 1;
	}
	@SuppressWarnings("rawtypes")
	public List getObjectList() {
		return objectList;
	}
	public long getPageNumber() {
		return pageNumber;
	}
	public long getPageSize() {
		return pageSize;
	}
	public long getTotalRows() {
		return totalRows;
	}
	public long getTotalPages() {
		return totalPages;
	}
	
	public long getSize() {
		return objectList.size();
	}
	


	public Long getUltima(){
		if (getTotalPages() > 1) {
			return getTotalPages();
		} else {
			return null;
		}
	}


	public Long getPrima(){
		if (getTotalPages() > 1) {
			return 1l;
		} else {
			return null;
		}
	}

	public Long getIndietro(){
		if (pageNumber == 1) {
			return null;
		} else if (pageNumber - 10 > 1 ) {
			return pageNumber - 10 ;
		} else {
			return 1l;
		} 
	}

	public Long getAvanti(){
		if (pageNumber == getTotalPages()) {
			return null;
		} else if (pageNumber + 10 < getTotalPages() ) {
			return pageNumber + 10 ;
		} else {
			return getTotalPages();
		} 
	}
	
	
	public List<Long> getNumeriPagine(){
		List<Long> result = new ArrayList<Long>();
		
		result.add(pageNumber);
		
		//aggiunge a destra
		int iDx = 1;
		for ( ;iDx <= 5; iDx++) {
			
			if (pageNumber + iDx > getTotalPages()) {
				break;
			} else{
				result.add( pageNumber + iDx );
			}
			
		}
		
		int iSx = 1;
		for ( ;iSx <= 5; iSx++) {
			
			if (pageNumber - iSx < 1 ) {
				break;
			} else{
				result.add( pageNumber - iSx );
			}
			
		} 
		
		if (result.size() < 10) {
			for ( ; result.size() < 10; iDx++) {
				
				if (pageNumber + iDx > getTotalPages()) {
					break;
				} else{
					result.add( pageNumber + iDx );
				}
				
			}			
		}
		

		if (result.size() < 10) {
			for ( ;result.size() < 10; iSx++) {
				
				if (pageNumber - iSx < 1 ) {
					break;
				} else{
					result.add( pageNumber - iSx );
				}
				
			} 
		}
		
		Collections.sort(result);
		
		return result;
		
	}
	
}
