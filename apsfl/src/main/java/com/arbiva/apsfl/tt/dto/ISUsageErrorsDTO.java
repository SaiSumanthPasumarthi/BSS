package com.arbiva.apsfl.tt.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * 
 * @author gowthami
 *
 */

public class ISUsageErrorsDTO implements Serializable {
	private BigInteger recid;
	private BigInteger fileSeqNo;
	private String sessionid;
	private String cpeslno;
	private String username;
	private String internetsrvccode;
	private Timestamp starttime;
	private Timestamp endtime;
	private BigDecimal dnldsize;
	private String dnldsizeuom;
	private BigInteger dnldspeed;
	private String dnldspeeduom;
	private BigDecimal upldsize;
	private String upldsizeuom;
	private BigInteger upldspeed;
	private String upldspeeduom;
	private String errorcode;
	private Timestamp createdon;
	private Timestamp processedon;
	private String status;
	public BigInteger getRecid() {
		return recid;
	}
	public void setRecid(BigInteger recid) {
		this.recid = recid;
	}
	public BigInteger getFileSeqNo() {
		return fileSeqNo;
	}
	public void setFileSeqNo(BigInteger fileSeqNo) {
		this.fileSeqNo = fileSeqNo;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getCpeslno() {
		return cpeslno;
	}
	public void setCpeslno(String cpeslno) {
		this.cpeslno = cpeslno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getInternetsrvccode() {
		return internetsrvccode;
	}
	public void setInternetsrvccode(String internetsrvccode) {
		this.internetsrvccode = internetsrvccode;
	}
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	public Timestamp getEndtime() {
		return endtime;
	}
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
	public BigDecimal getDnldsize() {
		return dnldsize;
	}
	public void setDnldsize(BigDecimal dnldsize) {
		this.dnldsize = dnldsize;
	}
	public String getDnldsizeuom() {
		return dnldsizeuom;
	}
	public void setDnldsizeuom(String dnldsizeuom) {
		this.dnldsizeuom = dnldsizeuom;
	}
	public BigInteger getDnldspeed() {
		return dnldspeed;
	}
	public void setDnldspeed(BigInteger dnldspeed) {
		this.dnldspeed = dnldspeed;
	}
	public String getDnldspeeduom() {
		return dnldspeeduom;
	}
	public void setDnldspeeduom(String dnldspeeduom) {
		this.dnldspeeduom = dnldspeeduom;
	}
	public BigDecimal getUpldsize() {
		return upldsize;
	}
	public void setUpldsize(BigDecimal upldsize) {
		this.upldsize = upldsize;
	}
	public String getUpldsizeuom() {
		return upldsizeuom;
	}
	public void setUpldsizeuom(String upldsizeuom) {
		this.upldsizeuom = upldsizeuom;
	}
	public BigInteger getUpldspeed() {
		return upldspeed;
	}
	public void setUpldspeed(BigInteger upldspeed) {
		this.upldspeed = upldspeed;
	}
	public String getUpldspeeduom() {
		return upldspeeduom;
	}
	public void setUpldspeeduom(String upldspeeduom) {
		this.upldspeeduom = upldspeeduom;
	}
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	public Timestamp getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}
	public Timestamp getProcessedon() {
		return processedon;
	}
	public void setProcessedon(Timestamp processedon) {
		this.processedon = processedon;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
