package com.arbiva.apsfl.reports.vo;

import java.io.Serializable;

public class CafDatesVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long cafNo;

	private String serviceCode;

	private String actDate;

	private String deactDate;

	private String suspDates;

	private String resumedates;
	
	private String aadharNo;
	
	private String stbSrlNo;
	
	private String stbMacAddress;
	
	private String nwSubCode;
	
	public String getNwSubCode() {
		return nwSubCode;
	}

	public void setNwSubCode(String nwSubCode) {
		this.nwSubCode = nwSubCode;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getStbSrlNo() {
		return stbSrlNo;
	}

	public void setStbSrlNo(String stbSrlNo) {
		this.stbSrlNo = stbSrlNo;
	}

	public String getStbMacAddress() {
		return stbMacAddress;
	}

	public void setStbMacAddress(String stbMacAddress) {
		this.stbMacAddress = stbMacAddress;
	}

	public Long getCafNo() {
		return cafNo;
	}

	public void setCafNo(Long cafNo) {
		this.cafNo = cafNo;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getActDate() {
		return actDate;
	}

	public void setActDate(String actDate) {
		this.actDate = actDate;
	}

	public String getDeactDate() {
		return deactDate;
	}

	public void setDeactDate(String deactDate) {
		this.deactDate = deactDate;
	}

	public String getSuspDates() {
		return suspDates;
	}

	public void setSuspDates(String suspDates) {
		this.suspDates = suspDates;
	}

	public String getResumedates() {
		return resumedates;
	}

	public void setResumedates(String resumedates) {
		this.resumedates = resumedates;
	}
    
	
}
