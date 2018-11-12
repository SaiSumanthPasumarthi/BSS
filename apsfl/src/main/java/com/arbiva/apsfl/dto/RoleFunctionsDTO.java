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
public class RoleFunctionsDTO {
	
	

	
	@JsonProperty("roleName")
	private String roleName;
	
	@JsonProperty("funcName")
	private String funcName;
	
	@JsonProperty("tenantCode")
	private String tenantCode;
	
	@JsonProperty("status")
	private Byte status;
	
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
	
	@JsonProperty("check_role")
	private String checkRole;
	
	

	public String getCheckRole() {
		return checkRole;
	}

	public void setCheckRole(String checkRole) {
		this.checkRole = checkRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
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
	
}
