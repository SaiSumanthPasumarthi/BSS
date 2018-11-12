package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
public class TenantDocuments implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String tenantCode;
	private String documentLovName;
	private String docmentLov;
	
	private Date effectiveFrom;
	
	private String documentLocationReference;
	
	private String docuniqueId;
	
	private Date effectiveTo;
	
	private Integer status;
	
	private Calendar createdDate;
	
	private String createdBy;
	
	private String cratedIPAddress;
	
	private Calendar modifiedDate;
	
	private String modifiedBy;
	
	private String modifiedIPAddress;
	
	private Calendar deactivatedOn;
	
	private String deactivatedBy;
	
	private String deactivatedIpaddr;

	public Calendar getDeactivatedOn() {
		return deactivatedOn;
	}

	public void setDeactivatedOn(Calendar deactivatedOn) {
		this.deactivatedOn = deactivatedOn;
	}

	public String getDeactivatedBy() {
		return deactivatedBy;
	}

	public void setDeactivatedBy(String deactivatedBy) {
		this.deactivatedBy = deactivatedBy;
	}

	public String getDeactivatedIpaddr() {
		return deactivatedIpaddr;
	}

	public void setDeactivatedIpaddr(String deactivatedIpaddr) {
		this.deactivatedIpaddr = deactivatedIpaddr;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getDocumentLovName() {
		return documentLovName;
	}

	public void setDocumentLovName(String documentLovName) {
		this.documentLovName = documentLovName;
	}

	public String getDocmentLov() {
		return docmentLov;
	}

	public void setDocmentLov(String docmentLov) {
		this.docmentLov = docmentLov;
	}
	
	public String getDocuniqueId() {
		return docuniqueId;
	}

	public void setDocuniqueId(String docuniqueId) {
		this.docuniqueId = docuniqueId;
	}
	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Date getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public String getDocumentLocationReference() {
		return documentLocationReference;
	}
	public void setDocumentLocationReference(String documentLocationReference) {
		this.documentLocationReference = documentLocationReference;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Calendar getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCratedIPAddress() {
		return cratedIPAddress;
	}
	public void setCratedIPAddress(String cratedIPAddress) {
		this.cratedIPAddress = cratedIPAddress;
	}
	public Calendar getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Calendar modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedIPAddress() {
		return modifiedIPAddress;
	}
	public void setModifiedIPAddress(String modifiedIPAddress) {
		this.modifiedIPAddress = modifiedIPAddress;
	}

}
