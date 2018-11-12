package com.arbiva.apsfl.util;

import java.util.List;

public class PaginationObject<T> {
	
	long iTotalRecords;

	long iTotalDisplayRecords;

	String sEcho;

	String sColumns;
	
	List<T> aaData;
	
	List<T> abData;


	public List<T> getAbData() {
		return abData;
	}

	public void setAbData(List<T> abData) {
		this.abData = abData;
	}

	public long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public List<T> getAaData() {
		return aaData;
	}

	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}

}
