/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Lakshman
 *
 */
public class VPNSrvcNamesStage implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long uploadId;
	
	private Long uploadRecNo;
	
	private Integer districtUid;
	
	private Integer mandalSlno;
	
	private String substnUid;
	
	private String oltSerialNo;
	
	private String vpnsrvcName;
	
	private String createdBy;
	
	private Calendar createdOn;
	
	private String status;
	
	private String remarks;
	
	private String successRec;
	
	private String failureRec;
	
	public String getSuccessRec() {
		return successRec;
	}

	public void setSuccessRec(String successRec) {
		this.successRec = successRec;
	}

	public String getFailureRec() {
		return failureRec;
	}

	public void setFailureRec(String failureRec) {
		this.failureRec = failureRec;
	}

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

	public Integer getDistrictUid() {
		return districtUid;
	}

	public void setDistrictUid(Integer districtUid) {
		this.districtUid = districtUid;
	}

	public Integer getMandalSlno() {
		return mandalSlno;
	}

	public void setMandalSlno(Integer mandalSlno) {
		this.mandalSlno = mandalSlno;
	}

	public String getSubstnUid() {
		return substnUid;
	}

	public void setSubstnUid(String substnUid) {
		this.substnUid = substnUid;
	}

	public String getOltSerialNo() {
		return oltSerialNo;
	}

	public void setOltSerialNo(String oltSerialNo) {
		this.oltSerialNo = oltSerialNo;
	}

	public String getVpnsrvcName() {
		return vpnsrvcName;
	}

	public void setVpnsrvcName(String vpnsrvcName) {
		this.vpnsrvcName = vpnsrvcName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
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
