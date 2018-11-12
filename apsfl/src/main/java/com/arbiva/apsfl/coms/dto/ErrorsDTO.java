package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

public class ErrorsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cafNo;
	private String requestId;
	private String prodCode;
	private String srvcCode;
	private String createdOn;
	
	public String getCafNo() {
		return cafNo;
	}
	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public String getSrvcCode() {
		return srvcCode;
	}
	public void setSrvcCode(String srvcCode) {
		this.srvcCode = srvcCode;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
}
