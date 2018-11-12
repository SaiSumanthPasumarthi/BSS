/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;


public class EntCafStage implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long uploadId;
	
	private Long uploadRecNo;
	
	private String district;
	
	private String mandal;
	
	private String location;
	
	private String contactName;
	
	private String contactMobileNo;
	
	private String contactEmail;
	
	private String pmntrespFlag;
	
	private String lmocode;
	
	private String lattitude;
	
	private String longitude;
	
	private String apsflUniqueId;
	
	private String status;
	
	private String remarks;
	

	public Long getUploadId() {
		return uploadId;
	}

	public void setUploadId(Long uploadId) {
		this.uploadId = uploadId;
	}
	
	public Long getUploadRecNo() {
		return uploadRecNo;
	}

	public void setUploadRecNo(Long uploadRecNo) {
		this.uploadRecNo = uploadRecNo;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactMobileNo() {
		return contactMobileNo;
	}

	public void setContactMobileNo(String contactMobileNo) {
		this.contactMobileNo = contactMobileNo;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getPmntrespFlag() {
		return pmntrespFlag;
	}

	public void setPmntrespFlag(String pmntrespFlag) {
		this.pmntrespFlag = pmntrespFlag;
	}

	public String getLmocode() {
		return lmocode;
	}

	public void setLmocode(String lmocode) {
		this.lmocode = lmocode;
	}

	public String getLattitude() {
		return lattitude;
	}

	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getApsflUniqueId() {
		return apsflUniqueId;
	}

	public void setApsflUniqueId(String apsflUniqueId) {
		this.apsflUniqueId = apsflUniqueId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
