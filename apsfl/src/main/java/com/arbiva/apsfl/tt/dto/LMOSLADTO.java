package com.arbiva.apsfl.tt.dto;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 
 * @author gowthami
 *
 */

public class LMOSLADTO implements Serializable{
	private BigInteger slaViolatedCnt;
	private String userName;
	private String mobile;
	private String email;
	private String assignedTo;
	private String tenantType;
	private String status;
	private String loginID;
	private String tenantCode;
	
	public BigInteger getSlaViolatedCnt() {
		return slaViolatedCnt;
	}
	public void setSlaViolatedCnt(BigInteger slaViolatedCnt) {
		this.slaViolatedCnt = slaViolatedCnt;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getTenantType() {
		return tenantType;
	}
	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
}
