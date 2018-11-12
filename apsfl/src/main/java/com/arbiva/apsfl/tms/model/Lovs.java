/**
 * 
 */
package com.arbiva.apsfl.tms.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Arbiva
 *
 */
@Entity
@Table(name="lovs")
public class Lovs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "lovid")
	private Integer lovId;
	
	@Column(name = "lovname")
	private String lovName;
	
	@Column(name = "lovseq")
	private Integer lovSeq;
	
	@Column(name = "lovvalue")
	private String lovValue;
	
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
