package com.arbiva.apsfl.tms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="emailrptmst ")
@IdClass(EmailMasterID.class)
public class EmailMaster {
	
	@Id
	@Column(name="emailid")
	private String emailid;
	
	@Id
	@Column(name="rptname")
	private String  rptname;
	
	@Column(name="personname")
	private String  personname;
	
	@Column(name="subj")
	private String  subj;
	
	@Column(name="msg")
	private String  msg;
	
	@Column(name="phone")
	private String  phone;
	
	
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getRptname() {
		return rptname;
	}
	public void setRptname(String rptname) {
		this.rptname = rptname;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSubj() {
		return subj;
	}
	public void setSubj(String subj) {
		this.subj = subj;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
