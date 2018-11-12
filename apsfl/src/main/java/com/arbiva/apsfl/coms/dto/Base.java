package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author srinivasa
 * 
 */
@MappedSuperclass
public abstract class Base implements Serializable {

	private static final long serialVersionUID = 1L;

	protected int status;

	protected Calendar createdOn;

	protected String createdBy;

	protected String createdIPAddr;

	protected Calendar modifiedOn;

	protected String modifiedBy;

	protected String modifiedIPAddr;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setModifiedOn(Calendar modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Calendar getModifiedOn() {
		return modifiedOn;
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
	
	@Transient
	@JsonIgnore
	public boolean isNew() {
		return (this.createdOn == null);
	}

}
