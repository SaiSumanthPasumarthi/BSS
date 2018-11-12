package com.arbiva.apsfl.dto;

import java.util.List;

import com.arbiva.apsfl.coms.dto.Customer;

public class UsersDataPageObject {

	long iTotalRecords;

	long iTotalDisplayRecords;

	String sEcho;

	String sColumns;

	List<Customer> aaData;
	
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

	public List<Customer> getAaData() {
		return aaData;
	}

	public void setAaData(List<Customer> aaData) {
		this.aaData = aaData;
	}
}
