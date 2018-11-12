package com.arbiva.apsfl.tms.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tenantdocs")
@IdClass(TenantDocsPK.class)
public class TenantDocuments implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public TenantDocuments() {
		
	}
	
	@Id
	@Column(name = "tenantcode")
	private String tenantCode;
	
	@Id
	@Column(name = "doclovname")
	private String documentLovName;
	
	@Column(name = "doclov")
	private String docmentLov;
	
	@Id
	@Column(name = "effectivefrom")
	private Date effectiveFrom;
	
	@Column(name = "doclocref")
	private String documentLocationReference;
	
	@Column(name = "docuniqueid")
	private String docuniqueId;
	
	@Column(name = "effectiveto")
	private Date effectiveTo;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "createdon")
	private Calendar createdDate;
	
	@Column(name = "createdby")
	private String createdBy;
	
	@Column(name = "createdipaddr")
	private String cratedIPAddress;
	
	@Column(name = "modifiedon")
	private Calendar modifiedDate;
	
	@Column(name = "modifiedby")
	private String modifiedBy;
	
	@Column(name = "modifiedipaddr")
	private String modifiedIPAddress;
	
	@Column(name = "deactivatedon")
	private Calendar deactivatedOn;
	
	@Column(name = "deactivatedby")
	private String deactivatedBy;
	
	@Column(name = "deactivatedipaddr")
	private String deactivatedIpaddr;
	
	@ManyToOne
	@JoinColumn(name="tenantcode", referencedColumnName="tenantcode", insertable=false,updatable=false)
	private Tenant tenant;
	
	

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

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
