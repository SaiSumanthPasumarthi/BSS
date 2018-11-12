package com.arbiva.apsfl.dto;

/**
 * 
 * @author gowthami
 *
 */


import java.io.Serializable;
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
public class RolesDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("roleID")
	private int roleID;
	
	@JsonProperty("rowID")
	private String rowID;
	
	@JsonProperty("gridRowID")
	private String gridRowID;
	
	@JsonProperty("roleName")
	private String roleName;
	
	@JsonProperty("status")
	private Byte status;
	
	@JsonProperty("tenantCode")
	private String tenantCode;
	
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

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getRowID() {
		return rowID;
	}

	public void setRowID(String rowID) {
		this.rowID = rowID;
	}

	public String getGridRowID() {
		return gridRowID;
	}

	public void setGridRowID(String gridRowID) {
		this.gridRowID = gridRowID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roleID;
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolesDTO other = (RolesDTO) obj;
		if (roleID != other.roleID)
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}
	
	}
