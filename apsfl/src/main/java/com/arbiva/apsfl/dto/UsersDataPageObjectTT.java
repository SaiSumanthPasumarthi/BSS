package com.arbiva.apsfl.dto;

import java.util.List;

import com.arbiva.apsfl.tt.dto.TroubleTicketDTO2;

public class UsersDataPageObjectTT {

	long iTotalRecords;

	long iTotalDisplayRecords;

	String sEcho;

	String sColumns;

	List<TroubleTicketDTO2> aaData;
	
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

	public List<TroubleTicketDTO2> getAaData() {
		return aaData;
	}

	public void setAaData(List<TroubleTicketDTO2> aaData) {
		this.aaData = aaData;
	}
	
}
