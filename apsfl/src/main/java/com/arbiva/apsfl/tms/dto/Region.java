package com.arbiva.apsfl.tms.dto;

import java.util.Calendar;

public class Region  {

	
	
	private Character regionType;
	
	private String regionCode;
	
	private String regionName;
	
	private String msporlmo;
	
	private Integer status;
	
	private Calendar createdDate;
	
	private String createdBy;
	
	private String cratedIPAddress;
	
	private Calendar modifiedDate;
	
	private String modifiedBy;
	
	private String modifiedIPAddress;
	
	
	
	public Character getRegionType() {
		return regionType;
	}
	public void setRegionType(Character regionType) {
		this.regionType = regionType;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
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
