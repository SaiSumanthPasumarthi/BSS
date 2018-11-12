package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RevenueSharingTemplateDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String rstmplCode;
	
	private int tenantslno;
	
	private String tenanttype;
	private String regioncode;
	
	private Date effectivefrom;
	
	private BigDecimal revshareperc;
	
	private int status;
	

	
	public String getRstmplCode() {
		return rstmplCode;
	}

	public void setRstmplCode(String rstmplCode) {
		this.rstmplCode = rstmplCode;
	}

	public int getTenantslno() {
		return tenantslno;
	}

	public void setTenantslno(int tenantslno) {
		this.tenantslno = tenantslno;
	}

	public String getTenanttype() {
		return tenanttype;
	}

	public void setTenanttype(String tenanttype) {
		this.tenanttype = tenanttype;
	}

	public String getRegioncode() {
		return regioncode;
	}

	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
	}

	public Date getEffectivefrom() {
		return effectivefrom;
	}

	public void setEffectivefrom(Date effectivefrom) {
		this.effectivefrom = effectivefrom;
	}

	public BigDecimal getRevshareperc() {
		return revshareperc;
	}

	public void setRevshareperc(BigDecimal revshareperc) {
		this.revshareperc = revshareperc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}