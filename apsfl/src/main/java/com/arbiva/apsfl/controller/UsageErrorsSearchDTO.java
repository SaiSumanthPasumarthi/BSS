package com.arbiva.apsfl.controller;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;

/**
 * 
 * @author gowthami
 *
 */

public class UsageErrorsSearchDTO implements Serializable{

	private String fromDate;
	private String toDate;
	private String status;
	
	public UsageErrorsSearchDTO() {
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
