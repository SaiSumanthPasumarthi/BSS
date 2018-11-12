package com.arbiva.apsfl.scs.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
/**
 * 
 * @author srinivasa
 * 
 */
@JsonAutoDetect
@MappedSuperclass
public abstract class Base implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "STATUS", columnDefinition="tinyint(1) default 1")
	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDON")
	private Date createdOn;

	@Column(name = "CREATEDBY")
	private String createdBy;

	@Column(name = "CREATEDIPADDR")
	private String createdIPAddr;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIEDON")
	private Date modifiedOn;

	@Column(name = "MODIFIEDBY")
	private String modifiedBy;

	@Column(name = "MODIFIEDIPADDR")
	private String modifiedIPAddr;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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
