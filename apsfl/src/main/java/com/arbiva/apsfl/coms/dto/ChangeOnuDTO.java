package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Srinivas V
 * @since Feb 08 2017
 */
public class ChangeOnuDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cafNumberOnuChange; 
	
	private String nwSubCode;
	
	private String cafsCustId;
	
	private String oldOnuSerialNum;
	
	private String newOnuSerialNum;
	
	private String oldCpeOnuMacAddress;
	
	private String newCpeOnuMacAddress;
	
	private String aadharNumberOnuChange;
	
	private String mobileNumberOnuChange;
	
	private String tenantCodeOnuChange;
	
	private String onuModel;
	
	private String onuProfileName;
	
	private String changePurpose;
	
	private String profileIdOnu;
	
	private String tenantType;
	
	private String changeComment;
	
	

	public String getChangeComment() {
		return changeComment;
	}

	public void setChangeComment(String changeComment) {
		this.changeComment = changeComment;
	}

	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}

	public String getProfileIdOnu() {
		return profileIdOnu;
	}

	public void setProfileIdOnu(String profileIdOnu) {
		this.profileIdOnu = profileIdOnu;
	}

	public String getOnuModel() {
		return onuModel;
	}

	public void setOnuModel(String onuModel) {
		this.onuModel = onuModel;
	}

	public String getOnuProfileName() {
		return onuProfileName;
	}

	public void setOnuProfileName(String onuProfileName) {
		this.onuProfileName = onuProfileName;
	}

	public String getTenantCodeOnuChange() {
		return tenantCodeOnuChange;
	}

	public void setTenantCodeOnuChange(String tenantCodeOnuChange) {
		this.tenantCodeOnuChange = tenantCodeOnuChange;
	}

	public String getAadharNumberOnuChange() {
		return aadharNumberOnuChange;
	}

	public void setAadharNumberOnuChange(String aadharNumberOnuChange) {
		this.aadharNumberOnuChange = aadharNumberOnuChange;
	}

	public String getCafNumberOnuChange() {
		return cafNumberOnuChange;
	}

	public void setCafNumberOnuChange(String cafNumberOnuChange) {
		this.cafNumberOnuChange = cafNumberOnuChange;
	}

	public String getMobileNumberOnuChange() {
		return mobileNumberOnuChange;
	}

	public void setMobileNumberOnuChange(String mobileNumberOnuChange) {
		this.mobileNumberOnuChange = mobileNumberOnuChange;
	}

	public String getNwSubCode() {
		return nwSubCode;
	}

	public void setNwSubCode(String nwSubCode) {
		this.nwSubCode = nwSubCode;
	}

	public String getCafsCustId() {
		return cafsCustId;
	}

	public void setCafsCustId(String cafsCustId) {
		this.cafsCustId = cafsCustId;
	}

	public String getOldOnuSerialNum() {
		return oldOnuSerialNum;
	}

	public void setOldOnuSerialNum(String oldOnuSerialNum) {
		this.oldOnuSerialNum = oldOnuSerialNum;
	}

	public String getOldCpeOnuMacAddress() {
		return oldCpeOnuMacAddress;
	}

	public void setOldCpeOnuMacAddress(String oldCpeOnuMacAddress) {
		this.oldCpeOnuMacAddress = oldCpeOnuMacAddress;
	}

	public String getNewCpeOnuMacAddress() {
		return newCpeOnuMacAddress;
	}

	public void setNewCpeOnuMacAddress(String newCpeOnuMacAddress) {
		this.newCpeOnuMacAddress = newCpeOnuMacAddress;
	}

	public String getNewOnuSerialNum() {
		return newOnuSerialNum;
	}

	public void setNewOnuSerialNum(String newOnuSerialNum) {
		this.newOnuSerialNum = newOnuSerialNum;
	}

	public String getChangePurpose() {
		return changePurpose;
	}

	public void setChangePurpose(String changePurpose) {
		this.changePurpose = changePurpose;
	}
}
