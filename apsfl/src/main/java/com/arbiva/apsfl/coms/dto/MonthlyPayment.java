/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class MonthlyPayment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mobileNo;
	
	private String cafNo;

	private String from;
	
	public String getInvmn() {
		return invmn;
	}

	public void setInvmn(String invmn) {
		this.invmn = invmn;
	}

	public String getInvyr() {
		return invyr;
	}

	public void setInvyr(String invyr) {
		this.invyr = invyr;
	}

	private String invmn;
	private String invyr;
	
	
	private int offset;
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	private String to;

	private String loginId;
	
	private String tenantCode;
	
	private String tenantName;
	
	public String getCafNo() {
		return cafNo;
	}

	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	
}
