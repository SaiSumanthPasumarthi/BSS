package com.arbiva.apsfl.tms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tenantswallet")
public class TenantWallet implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "tenantcode")
	private String tenantCode;
	
	@Column(name = "deposit_amt")
	private BigDecimal depositAmount;
	
	@Column(name = "walletamt")
	private BigDecimal walletAmount;
	
	@Column(name = "crlimitamt")
	private BigDecimal crLimitAmt;
	
	@Column(name = "usedamt")
	private BigDecimal usedAmt;
	
	@Column(name = "deposit_mode")
	private String depositMode;
	
	
	@Column(name = "deposit_refno")
	private String depositRefno;
	
	@Column(name = "deposit_status")
	private Integer depositStatus;
	
	@Column(name = "wallet_lastdbamt")
	private BigDecimal walletLastdbamt;
	
	@Column(name = "wallet_lastdbdate")
	private Calendar walletLastdbdate;
	
	@Column(name = "wallet_lastdbmode")
	private String walletLastdbmode;
	
	@Column(name = "wallet_lastdbid")
	private String walletLastdbid;
	
	@Column(name = "wallet_lastcramt")
	private BigDecimal walletLastcramt;
	
	@Column(name = "wallet_lastcrdate")
	private Calendar walletLastcrdate;
	
	@Column(name = "wallet_lastcrmode")
	private String walletLastcrmode;
	
	@Column(name = "wallet_lastcrid")
	private String walletLastcrid;
	
	@Column(name = "depost_lastdbamt")
	private BigDecimal depostLastdbamt;
	
	@Column(name = "depost_lastdbdate")
	private Calendar depostLastdbdate;
	
	@Column(name = "depost_lastdbmode")
	private String depostLastdbmode;
	
	@Column(name = "depost_lastdbid")
	private String depostLastdbid;
	
	@Column(name = "depost_lastcramt")
	private BigDecimal depostLastcramt;
	
	@Column(name = "depost_lastcrdate")
	private Calendar depostLastcrdate;
	
	@Column(name = "depost_lastcrmode")
	private String depostLastcrmode;
	
	@Column(name = "depost_lastcrid")
	private String depostLastcrid;
	
	@Column(name = "crlimit_lastdbamt")
	private BigDecimal crlimitLastdbamt;
	
	@Column(name = "crlimit_lastdbdate")
	private Calendar crlimitLastdbdate;
	
	@Column(name = "crlimit_lastdbmode")
	private String crlimitLastdbmode;
	
	@Column(name = "crlimit_lastdbid")
	private String crlimitLastdbid;
	
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
	
	@ManyToOne
	@JoinColumn(name="tenantcode", referencedColumnName="tenantcode", insertable=false,updatable=false)
	private Tenant tenant;
	
	
	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public BigDecimal getCrLimitAmt() {
		return crLimitAmt;
	}

	public void setCrLimitAmt(BigDecimal crLimitAmt) {
		this.crLimitAmt = crLimitAmt;
	}

	public BigDecimal getUsedAmt() {
		return usedAmt;
	}

	public void setUsedAmt(BigDecimal usedAmt) {
		this.usedAmt = usedAmt;
	}

	public BigDecimal getCrlimitLastdbamt() {
		return crlimitLastdbamt;
	}

	public void setCrlimitLastdbamt(BigDecimal crlimitLastdbamt) {
		this.crlimitLastdbamt = crlimitLastdbamt;
	}

	public Calendar getCrlimitLastdbdate() {
		return crlimitLastdbdate;
	}

	public void setCrlimitLastdbdate(Calendar crlimitLastdbdate) {
		this.crlimitLastdbdate = crlimitLastdbdate;
	}

	public String getCrlimitLastdbmode() {
		return crlimitLastdbmode;
	}

	public void setCrlimitLastdbmode(String crlimitLastdbmode) {
		this.crlimitLastdbmode = crlimitLastdbmode;
	}

	public String getCrlimitLastdbid() {
		return crlimitLastdbid;
	}

	public void setCrlimitLastdbid(String crlimitLastdbid) {
		this.crlimitLastdbid = crlimitLastdbid;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public BigDecimal getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}

	public BigDecimal getWalletAmount() {
		return walletAmount;
	}

	public void setWalletAmount(BigDecimal walletAmount) {
		this.walletAmount = walletAmount;
	}

	public String getDepositMode() {
		return depositMode;
	}

	public void setDepositMode(String depositMode) {
		this.depositMode = depositMode;
	}

	public String getDepositRefno() {
		return depositRefno;
	}

	public void setDepositRefno(String depositRefno) {
		this.depositRefno = depositRefno;
	}

	public Integer getDepositStatus() {
		return depositStatus;
	}

	public void setDepositStatus(Integer depositStatus) {
		this.depositStatus = depositStatus;
	}

	public BigDecimal getWalletLastdbamt() {
		return walletLastdbamt;
	}

	public void setWalletLastdbamt(BigDecimal walletLastdbamt) {
		this.walletLastdbamt = walletLastdbamt;
	}

	public Calendar getWalletLastdbdate() {
		return walletLastdbdate;
	}

	public void setWalletLastdbdate(Calendar walletLastdbdate) {
		this.walletLastdbdate = walletLastdbdate;
	}

	public String getWalletLastdbmode() {
		return walletLastdbmode;
	}

	public void setWalletLastdbmode(String walletLastdbmode) {
		this.walletLastdbmode = walletLastdbmode;
	}

	public String getWalletLastdbid() {
		return walletLastdbid;
	}

	public void setWalletLastdbid(String walletLastdbid) {
		this.walletLastdbid = walletLastdbid;
	}

	public BigDecimal getWalletLastcramt() {
		return walletLastcramt;
	}

	public void setWalletLastcramt(BigDecimal walletLastcramt) {
		this.walletLastcramt = walletLastcramt;
	}

	public Calendar getWalletLastcrdate() {
		return walletLastcrdate;
	}

	public void setWalletLastcrdate(Calendar walletLastcrdate) {
		this.walletLastcrdate = walletLastcrdate;
	}

	public String getWalletLastcrmode() {
		return walletLastcrmode;
	}

	public void setWalletLastcrmode(String walletLastcrmode) {
		this.walletLastcrmode = walletLastcrmode;
	}

	public String getWalletLastcrid() {
		return walletLastcrid;
	}

	public void setWalletLastcrid(String walletLastcrid) {
		this.walletLastcrid = walletLastcrid;
	}

	public BigDecimal getDepostLastdbamt() {
		return depostLastdbamt;
	}

	public void setDepostLastdbamt(BigDecimal depostLastdbamt) {
		this.depostLastdbamt = depostLastdbamt;
	}

	public Calendar getDepostLastdbdate() {
		return depostLastdbdate;
	}

	public void setDepostLastdbdate(Calendar depostLastdbdate) {
		this.depostLastdbdate = depostLastdbdate;
	}

	public String getDepostLastdbmode() {
		return depostLastdbmode;
	}

	public void setDepostLastdbmode(String depostLastdbmode) {
		this.depostLastdbmode = depostLastdbmode;
	}

	public String getDepostLastdbid() {
		return depostLastdbid;
	}

	public void setDepostLastdbid(String depostLastdbid) {
		this.depostLastdbid = depostLastdbid;
	}

	public BigDecimal getDepostLastcramt() {
		return depostLastcramt;
	}

	public void setDepostLastcramt(BigDecimal depostLastcramt) {
		this.depostLastcramt = depostLastcramt;
	}

	public Calendar getDepostLastcrdate() {
		return depostLastcrdate;
	}

	public void setDepostLastcrdate(Calendar depostLastcrdate) {
		this.depostLastcrdate = depostLastcrdate;
	}

	public String getDepostLastcrmode() {
		return depostLastcrmode;
	}

	public void setDepostLastcrmode(String depostLastcrmode) {
		this.depostLastcrmode = depostLastcrmode;
	}

	public String getDepostLastcrid() {
		return depostLastcrid;
	}

	public void setDepostLastcrid(String depostLastcrid) {
		this.depostLastcrid = depostLastcrid;
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
