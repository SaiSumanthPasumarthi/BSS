package com.arbiva.apsfl.tms.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tenantbanks")
public class TenantBankDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "tenantcode")
	private String tenantCode;
	
	@Column(name = "accountno")
	private String accountNo;
	
	@Column(name = "ifsccode")
	private String ifscCode;
	
	@Column(name = "accttypelov")
	private String acctTypelov;
	
	@Column(name = "banknamelov")
	private String bankNamelov;
	
	@Column(name = "branchname")
	private String branchName;
	
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
	
	@Column(name = "modifiedby")
	private String modifiedBy;
	
	@Column(name = "modifiedipaddr")
	private String modifiedIPAddress;

	@Column(name = "deactivatedon")
	private Calendar deactivatedDate;
	
	@Column(name = "deactivatedby")
	private String deactivatedBy;
	
	@Column(name = "deactivatedipaddr")
	private String deactivatedIpAddress;
	
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getAcctTypelov() {
		return acctTypelov;
	}
	public void setAcctTypelov(String acctTypelov) {
		this.acctTypelov = acctTypelov;
	}
	public String getBankNamelov() {
		return bankNamelov;
	}
	public void setBankNamelov(String bankNamelov) {
		this.bankNamelov = bankNamelov;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Calendar getDeactivatedDate() {
		return deactivatedDate;
	}
	public void setDeactivatedDate(Calendar deactivatedDate) {
		this.deactivatedDate = deactivatedDate;
	}
	public String getDeactivatedBy() {
		return deactivatedBy;
	}
	public void setDeactivatedBy(String deactivatedBy) {
		this.deactivatedBy = deactivatedBy;
	}
	public String getDeactivatedIpAddress() {
		return deactivatedIpAddress;
	}
	public void setDeactivatedIpAddress(String deactivatedIpAddress) {
		this.deactivatedIpAddress = deactivatedIpAddress;
	}
	public String getModifiedIPAddress() {
		return modifiedIPAddress;
	}
	public void setModifiedIPAddress(String modifiedIPAddress) {
		this.modifiedIPAddress = modifiedIPAddress;
	}

	
}
