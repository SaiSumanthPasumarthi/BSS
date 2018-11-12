package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;

public class CpeStockVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cpeSrlno;
	
	private String profileId;
	
	private String mspCode;
	
	private String lmoCode;
	
	private String macAddress;
	
	private String tenantCode;
	
	private String cpeProfileName;
	
	private String districtName;
	
	private String mandalName;
	
	private String villageName;
	
	private String cpeTypeLov;
	
	private String cpeModel;
	
	private String cpeBatchDate;
	
	private String cpeCafNo;
	
	private String status;
	
	private String cpeLogTransactionStatus;
	
	private String cpeLogTransactionDate;
	
	private String user;
	
	private String cafActivationDate;
	
	public String getCafActivationDate() {
		return cafActivationDate;
	}

	public void setCafActivationDate(String cafActivationDate) {
		this.cafActivationDate = cafActivationDate;
	}

	public String getCafDeactivationDate() {
		return cafDeactivationDate;
	}

	public void setCafDeactivationDate(String cafDeactivationDate) {
		this.cafDeactivationDate = cafDeactivationDate;
	}

	private String cafDeactivationDate;
	
	
	public String getCpeLogTransactionStatus() {
		return cpeLogTransactionStatus;
	}

	public void setCpeLogTransactionStatus(String cpeLogTransactionStatus) {
		this.cpeLogTransactionStatus = cpeLogTransactionStatus;
	}

	public String getCpeLogTransactionDate() {
		return cpeLogTransactionDate;
	}

	public void setCpeLogTransactionDate(String cpeLogTransactionDate) {
		this.cpeLogTransactionDate = cpeLogTransactionDate;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String cpeUser) {
		this.user = cpeUser;
	}

	public String getCpeCafNo() {
		return cpeCafNo;
	}

	public void setCpeCafNo(String cpeCafNo) {
		this.cpeCafNo = cpeCafNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getCpeTypeLov() {
		return cpeTypeLov;
	}

	public void setCpeTypeLov(String cpeTypeLov) {
		this.cpeTypeLov = cpeTypeLov;
	}

	public String getCpeModel() {
		return cpeModel;
	}

	public void setCpeModel(String cpeModel) {
		this.cpeModel = cpeModel;
	}
	
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	
	public String getCpeProfileName() {
		return cpeProfileName;
	}

	public void setCpeProfileName(String cpeProfileName) {
		this.cpeProfileName = cpeProfileName;
	}
	
	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getCpeSrlno() {
		return cpeSrlno;
	}

	public void setCpeSrlno(String cpeSrlno) {
		this.cpeSrlno = cpeSrlno;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
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

	public String getCpeBatchDate() {
		return cpeBatchDate;
	}

	public void setCpeBatchDate(String cpeBatchDate) {
		this.cpeBatchDate = cpeBatchDate;
	}

	
	
}
