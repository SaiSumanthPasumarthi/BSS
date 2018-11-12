package com.arbiva.apsfl.tms.model;

import java.io.Serializable;

public class EmailMasterID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String emailid;
	private String  rptname;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailid == null) ? 0 : emailid.hashCode());
		result = prime * result + ((rptname == null) ? 0 : rptname.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailMasterID other = (EmailMasterID) obj;
		if (emailid == null) {
			if (other.emailid != null)
				return false;
		} else if (!emailid.equals(other.emailid))
			return false;
		if (rptname == null) {
			if (other.rptname != null)
				return false;
		} else if (!rptname.equals(other.rptname))
			return false;
		return true;
	}
	
	
}
