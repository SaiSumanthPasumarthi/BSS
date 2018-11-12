package com.arbiva.apsfl.tt.dto;

import java.math.BigInteger;
import java.sql.Timestamp;



public class TTHistoryDTO {
	private BigInteger ticketNo;
	private Timestamp effectiveFrom;
	private String assignedTo;
	private String remarks;
	private Byte status;
	private Timestamp modifiedOn;
	private String modifiedBy;
	private Timestamp ttCreatedOn;
	private String effectiveFrm;
	private String currentStatus;
	 private String createdBy; 
	 private String mobileNo; 
	 public String getCreatedBy() {
		 return createdBy;
		 }
		 public void setCreatedBy(String createdBy) {
		 this.createdBy = createdBy;
		 }
	public BigInteger getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(BigInteger ticketNo) {
		this.ticketNo = ticketNo;
	}
	public Timestamp getEffectiveFrom() {
		return effectiveFrom;
	}
	public void setEffectiveFrom(Timestamp effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Timestamp getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Timestamp getTtCreatedOn() {
		return ttCreatedOn;
	}
	public void setTtCreatedOn(Timestamp ttCreatedOn) {
		this.ttCreatedOn = ttCreatedOn;
	}
	public String getEffectiveFrm() {
		return effectiveFrm;
	}
	public void setEffectiveFrm(String effectiveFrm) {
		this.effectiveFrm = effectiveFrm;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


}
