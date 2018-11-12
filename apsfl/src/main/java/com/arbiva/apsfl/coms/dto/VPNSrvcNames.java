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
public class VPNSrvcNames implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String substnUid;
	
	private String oltSerialNo;
	
	private String vpnsrvcName;
	
	private Integer districtUid;
	
	private Integer mandalSlno;
	
	private String createdBy;
	
	private Calendar createdOn;
	
	private String status;
	
	private String successRecordCount;
	
	private String failedRecordCount;

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

	public String getSuccessRecordCount() {
		return successRecordCount;
	}

	public void setSuccessRecordCount(String successRecordCount) {
		this.successRecordCount = successRecordCount;
	}

	public String getFailedRecordCount() {
		return failedRecordCount;
	}

	public void setFailedRecordCount(String failedRecordCount) {
		this.failedRecordCount = failedRecordCount;
	}
	
}
