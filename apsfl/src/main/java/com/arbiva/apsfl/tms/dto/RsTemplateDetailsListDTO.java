package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;

public class RsTemplateDetailsListDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String tenantslno;
	private String tenanttype;
	private String revshareperc;
	
	public String getTenantslno() {
		return tenantslno;
	}
	public void setTenantslno(String tenantslno) {
		this.tenantslno = tenantslno;
	}
	public String getTenanttype() {
		return tenanttype;
	}
	public void setTenanttype(String tenanttype) {
		this.tenanttype = tenanttype;
	}
	public String getRevshareperc() {
		return revshareperc;
	}
	public void setRevshareperc(String revshareperc) {
		this.revshareperc = revshareperc;
	}
	
	
	

}
