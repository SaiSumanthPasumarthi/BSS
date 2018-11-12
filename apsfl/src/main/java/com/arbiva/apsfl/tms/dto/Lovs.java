/**
 * 
 */
package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;

/**
 * @author Arbiva
 *
 */
public class Lovs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer lovId;
	
	private String lovName;
	
	private Integer lovSeq;
	
	private String lovValue;
	
	private Integer status;
	
	private Calendar createdDate;
	
	private String createdBy;
	
	private String cratedIPAddress;
	
	private Calendar modifiedDate;
	
	public Integer getLovId() {
		return lovId;
	}

	public void setLovId(Integer lovId) {
		this.lovId = lovId;
	}

	public String getLovName() {
		return lovName;
	}

	public void setLovName(String lovName) {
		this.lovName = lovName;
	}

	public Integer getLovSeq() {
		return lovSeq;
	}

	public void setLovSeq(Integer lovSeq) {
		this.lovSeq = lovSeq;
	}

	public String getLovValue() {
		return lovValue;
	}

	public void setLovValue(String lovValue) {
		this.lovValue = lovValue;
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

	public String getModifiedIPAddress() {
		return modifiedIPAddress;
	}

	public void setModifiedIPAddress(String modifiedIPAddress) {
		this.modifiedIPAddress = modifiedIPAddress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name = "modifiedby")
	private String modifiedBy;
	
	@Column(name = "modifiedipaddr")
	private String modifiedIPAddress;

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
