package com.arbiva.apsfl.tms.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emailrptsent")
public class EmailSendDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long emailtranid;

	@Column(name="emailid")
	private String emailid;
	
	@Column(name="rptname")
	private String rptname;
	
	@Column(name="senttime")
	private Calendar senttime;
	
	@Column(name="status")
	private String status;
	
	

	public Long getEmailtranid() {
		return emailtranid;
	}

	public void setEmailtranid(Long emailtranid) {
		this.emailtranid = emailtranid;
	}

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

	public Calendar getSenttime() {
		return senttime;
	}

	public void setSenttime(Calendar senttime) {
		this.senttime = senttime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
