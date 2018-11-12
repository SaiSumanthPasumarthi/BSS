package com.arbiva.apsfl.tms.model;

import java.io.Serializable;

public class TenantBankDetailsPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tenantCode;
	
	private String accountNo;
	
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNo == null) ? 0 : accountNo.hashCode());
		result = prime * result + ((tenantCode == null) ? 0 : tenantCode.hashCode());
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
		TenantBankDetailsPK other = (TenantBankDetailsPK) obj;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (tenantCode == null) {
			if (other.tenantCode != null)
				return false;
		} else if (!tenantCode.equals(other.tenantCode))
			return false;
		return true;
	}

}
