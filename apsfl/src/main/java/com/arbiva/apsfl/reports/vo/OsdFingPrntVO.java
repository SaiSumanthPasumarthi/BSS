package com.arbiva.apsfl.reports.vo;

import java.io.Serializable;

public class OsdFingPrntVO implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	private String requestType;
	
	private String requestDate;
	
	private String detailedMessage;
	
	private String cafNo;

	private String serviceCode;

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

	
	public String getCafNo() {
		return cafNo;
	}

	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
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

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getDetailedMessage() {
		return detailedMessage;
	}

	public void setDetailedMessage(String detailedMessage) {
		this.detailedMessage = detailedMessage;
	}


}
