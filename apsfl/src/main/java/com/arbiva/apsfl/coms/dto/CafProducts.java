/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.util.Date;

/**
 * @author Lakshman
 *
 */
public class CafProducts extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long cafno;
	
	private String tenantCode;
	
	private String prodCode;
	
	private Long rsagruid;
	
	private Integer minlockDays;
	
	private String mspCode;
	
	private Date expDate;
	
	private int priority;
	
	private Long pmntCustId;
	
	private Long parentcafno;
	
	private String prodName;
	
	public Long getParentcafno() {
		return parentcafno;
	}

	public void setParentcafno(Long parentcafno) {
		this.parentcafno = parentcafno;
	}

	public String getMspCode() {
		return mspCode;
	}

	public void setMspCode(String mspCode) {
		this.mspCode = mspCode;
	}
	
	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Long getCafno() {
		return cafno;
	}

	public void setCafno(Long cafno) {
		this.cafno = cafno;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public Integer getMinlockDays() {
		return minlockDays;
	}
	
	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public Long getRsagruid() {
		return rsagruid;
	}

	public void setRsagruid(Long rsagruid) {
		this.rsagruid = rsagruid;
	}

	public void setMinlockDays(Integer minlockDays) {
		this.minlockDays = minlockDays;
	}
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Long getPmntCustId() {
		return pmntCustId;
	}

	public void setPmntCustId(Long pmntCustId) {
		this.pmntCustId = pmntCustId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
}
