/**
 * 
 */
package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class EnterpriseSubscriberBOPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String custid;
	
	private String lmocode;

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getLmocode() {
		return lmocode;
	}

	public void setLmocode(String lmocode) {
		this.lmocode = lmocode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custid == null) ? 0 : custid.hashCode());
		result = prime * result + ((lmocode == null) ? 0 : lmocode.hashCode());
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
		EnterpriseSubscriberBOPK other = (EnterpriseSubscriberBOPK) obj;
		if (custid == null) {
			if (other.custid != null)
				return false;
		} else if (!custid.equals(other.custid))
			return false;
		if (lmocode == null) {
			if (other.lmocode != null)
				return false;
		} else if (!lmocode.equals(other.lmocode))
			return false;
		return true;
	}
	
}
