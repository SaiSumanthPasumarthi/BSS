/**
 * 
 */
package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;

/**
 * @author kiran
 *
 */

public class BlackListInfoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String stbcafno;
	
	private String effectivefrom;

	private String blacklistflag;

	private String reason;

	private String status;

	private String createdon;

	private String createdby;

	private String createdipaddr;

	private String approvedon;

	private String approvedby;

	private String approvedipaddr;

	private String stbSlno;
	
	private String phone;
	
	private String stbmac;
	
	private String aadharno;
	
	private String cafno;
	
	private String nwsubscode;
	
	private String custid;
	
	private String affectedcafs;
	
	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getAffectedcafs() {
		return affectedcafs;
	}

	public void setAffectedcafs(String affectedcafs) {
		this.affectedcafs = affectedcafs;
	}
	
	public String getStbcafno() {
		return stbcafno;
	}

	public void setStbcafno(String stbcafno) {
		this.stbcafno = stbcafno;
	}
	
	public String getNwsubscode() {
		return nwsubscode;
	}

	public void setNwsubscode(String nwsubscode) {
		this.nwsubscode = nwsubscode;
	}
	
	public String getCafno() {
		return cafno;
	}

	public void setCafno(String cafno) {
		this.cafno = cafno;
	}

	public String getAadharno() {
		return aadharno;
	}

	public void setAadharno(String aadharno) {
		this.aadharno = aadharno;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStbmac() {
		return stbmac;
	}

	public void setStbmac(String stbmac) {
		this.stbmac = stbmac;
	}
	
	public String getStbSlno() {
		return stbSlno;
	}

	public void setStbSlno(String stbSlno) {
		this.stbSlno = stbSlno;
	}

	public String getEffectivefrom() {
		return effectivefrom;
	}

	public void setEffectivefrom(String effectivefrom) {
		this.effectivefrom = effectivefrom;
	}

	public String getBlacklistflag() {
		return blacklistflag;
	}

	public void setBlacklistflag(String blacklistflag) {
		this.blacklistflag = blacklistflag;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getCreatedipaddr() {
		return createdipaddr;
	}

	public void setCreatedipaddr(String createdipaddr) {
		this.createdipaddr = createdipaddr;
	}

	public String getApprovedon() {
		return approvedon;
	}

	public void setApprovedon(String approvedon) {
		this.approvedon = approvedon;
	}

	public String getApprovedby() {
		return approvedby;
	}

	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}

	public String getApprovedipaddr() {
		return approvedipaddr;
	}

	public void setApprovedipaddr(String approvedipaddr) {
		this.approvedipaddr = approvedipaddr;
	}


}
