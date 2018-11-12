package com.arbiva.apsfl.tms.model;

import java.util.Calendar;
import java.util.Date;

public class CpeStock {

	private String cpeslno;

	private String cpeMacAddr;

	private Long profileId;

	private String batchId;

	private Date batchDate;

	private Long dlvId;

	private String mspCode;

	private String lmoCode;

	private Long cafNo;

	private int status;

	private Calendar modifiedon;

	private String modifiedby;

	public String getCpeMacAddr() {
		return cpeMacAddr;
	}

	public void setCpeMacAddr(String cpeMacAddr) {
		this.cpeMacAddr = cpeMacAddr;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Calendar getModifiedon() {
		return modifiedon;
	}

	public void setModifiedon(Calendar modifiedon) {
		this.modifiedon = modifiedon;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public String getCpeslno() {
		return cpeslno;
	}

	public void setCpeslno(String cpeslno) {
		this.cpeslno = cpeslno;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Date getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}

	public Long getDlvId() {
		return dlvId;
	}

	public void setDlvId(Long dlvId) {
		this.dlvId = dlvId;
	}

	public String getMspCode() {
		return mspCode;
	}

	public void setMspCode(String mspCode) {
		this.mspCode = mspCode;
	}

	public String getLmoCode() {
		return lmoCode;
	}

	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}

	public Long getCafNo() {
		return cafNo;
	}

	public void setCafNo(Long cafNo) {
		this.cafNo = cafNo;
	}

}
