package com.arbiva.apsfl.tms.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "regions")
public class Region implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private RegionPK id;

	public RegionPK getId() {
        return this.id;
    }
	public void setId(RegionPK id) {
    this.id = id;
	}
	@Column(name = "regionname")
	private String regionName;
	
	@Column(name = "msporlmo")
	private String msporlmo;
	
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
	
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getMsporlmo() {
		return msporlmo;
	}
	public void setMsporlmo(String msporlmo) {
		this.msporlmo = msporlmo;
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
