package com.arbiva.apsfl.tms.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tenantlicenses")
public class TenantLicenses implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "licenserefno", unique = true, nullable = false)
	private String licenserefno;
	
	@Id
	@Column(name="tenantcode")
	private String tenantcode;
	
	@Column(name = "licenseexpdate", unique = true, nullable = false)
	private String licenseexpDate;
	
	@Column(name = "licenseauthority")
	private String licenseAuthority;
	
	@Column(name = "status", unique = true, nullable = false)
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
	private Calendar deactivatedDate;
	
	@Column(name = "deactivatedby")
	private String deactivatedBy;
	
	@Column(name = "deactivatedipaddr")
	private String deactivatedIpAddress;
	
	@ManyToOne
	@JoinColumn(name = "tenantcode", referencedColumnName = "tenantcode", nullable = false, insertable = false, updatable = false)
	private Tenant tenantObject;
	
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "regiontype", referencedColumnName = "regiontype", nullable = false), @JoinColumn(name = "regioncode", referencedColumnName = "regioncode", nullable = false) })
	private Region region;
	
	public String getLicenserefno() {
		return licenserefno;
	}
	public void setLicenserefno(String licenserefno) {
		this.licenserefno = licenserefno;
	}
	
	public String getTenantcode() {
		return tenantcode;
	}
	public void setTenantcode(String tenantcode) {
		this.tenantcode = tenantcode;
	}
	public Tenant getTenantObject() {
		return tenantObject;
	}
	public void setTenantObject(Tenant tenantObject) {
		this.tenantObject = tenantObject;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public String getLicenseexpDate() {
		return licenseexpDate;
	}
	public void setLicenseexpDate(String licenseexpDate) {
		this.licenseexpDate = licenseexpDate;
	}
	public String getLicenseAuthority() {
		return licenseAuthority;
	}
	public void setLicenseAuthority(String licenseAuthority) {
		this.licenseAuthority = licenseAuthority;
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
	public Calendar getDeactivatedDate() {
		return deactivatedDate;
	}
	public void setDeactivatedDate(Calendar deactivatedDate) {
		this.deactivatedDate = deactivatedDate;
	}
	public String getDeactivatedBy() {
		return deactivatedBy;
	}
	public void setDeactivatedBy(String deactivatedBy) {
		this.deactivatedBy = deactivatedBy;
	}
	public String getDeactivatedIpAddress() {
		return deactivatedIpAddress;
	}
	public void setDeactivatedIpAddress(String deactivatedIpAddress) {
		this.deactivatedIpAddress = deactivatedIpAddress;
	}
	
}
