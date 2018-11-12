package com.arbiva.apsfl.tms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="tenants")
@IdClass(CafMasterID.class)
public class CafMaster {
	
	@Id
	@Column(name="regoff_email1")
	private String Email;
	
//	@Id
//	@Column(name="rptname")
//	private String  tenantname;
	
	@Column(name="tenantname")
	private String  tenantname;

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getTenantname() {
		return tenantname;
	}

	public void setTenantname(String tenantname) {
		this.tenantname = tenantname;
	}
	
//	@Column(name="subj")
//	private String  subj;
//	
//	@Column(name="msg")
//	private String  msg;
//	
//	@Column(name="phone")
//	private String  phone;
	
	
	
}
