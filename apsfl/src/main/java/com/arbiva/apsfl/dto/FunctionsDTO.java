package com.arbiva.apsfl.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author gowthami
 *
 */
@JsonAutoDetect(getterVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, isGetterVisibility = Visibility.NONE)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class FunctionsDTO {
	
	

	@JsonProperty("funcID")
	private int funcID;
	
	@JsonProperty("funcName")
	private String funcName;
	
	@JsonProperty("apfgcScope")
	private Byte apfgcScope;
	
	@JsonProperty("spScope")
	private Byte spScope;
	
	@JsonProperty("lmoScope")
	private Byte lmoScope;
	
	@JsonProperty("status")
	private Byte status;
	
	@JsonProperty("menuPathID")
	private String menuPathID;
	
	@JsonProperty("createdOn")
	private Date createdOn;
	
	@JsonProperty("createdBy")
	private String createdBy;
	
	@JsonProperty("createdIPAddr")
	private String createdIPAddr;
	
	@JsonProperty("modifiedOn")
	private Date modifiedOn;
	
	@JsonProperty("modifiedBy")
	private String modifiedBy;
	
	@JsonProperty("modifiedIPAddr")
	private String modifiedIPAddr;

	
	@JsonProperty("rolesHiddenValue")
	private String rolesHiddenValue;
		
	@JsonProperty("servicesHiddenValues")
	private String servicesHiddenValues;
	
	@JsonProperty("rowID")
	private String rowID;
	
	private String tenantCode;
	
	private String domain;
	
	private String userName;
	
	
	
	
	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getFuncID() {
		return funcID;
	}

	public void setFuncID(int funcID) {
		this.funcID = funcID;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public Byte getApfgcScope() {
		return apfgcScope;
	}

	public void setApfgcScope(Byte apfgcScope) {
		this.apfgcScope = apfgcScope;
	}

	public Byte getSpScope() {
		return spScope;
	}

	public void setSpScope(Byte spScope) {
		this.spScope = spScope;
	}

	public Byte getLmoScope() {
		return lmoScope;
	}

	public void setLmoScope(Byte lmoScope) {
		this.lmoScope = lmoScope;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getMenuPathID() {
		return menuPathID;
	}

	public void setMenuPathID(String menuPathID) {
		this.menuPathID = menuPathID;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedIPAddr() {
		return createdIPAddr;
	}

	public void setCreatedIPAddr(String createdIPAddr) {
		this.createdIPAddr = createdIPAddr;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedIPAddr() {
		return modifiedIPAddr;
	}

	public void setModifiedIPAddr(String modifiedIPAddr) {
		this.modifiedIPAddr = modifiedIPAddr;
	}

	public String getRolesHiddenValue() {
		return rolesHiddenValue;
	}

	public void setRolesHiddenValue(String rolesHiddenValue) {
		this.rolesHiddenValue = rolesHiddenValue;
	}

	public String getServicesHiddenValues() {
		return servicesHiddenValues;
	}

	public void setServicesHiddenValues(String servicesHiddenValues) {
		this.servicesHiddenValues = servicesHiddenValues;
	}

	public String getRowID() {
		return rowID;
	}

	public void setRowID(String rowID) {
		this.rowID = rowID;
	}
	
}
