package com.arbiva.apsfl.tms.model;

import java.io.Serializable;
import java.util.Date;

public class TenantDocsPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tenantCode;
	
	private String documentLovName;
	
	private Date effectiveFrom;

	public TenantDocsPK(){
		
	}
	
	public TenantDocsPK(String tenantCode, String documentLovName, Date effectiveFrom) {
		this.tenantCode = tenantCode;
		this.documentLovName = documentLovName;
		this.effectiveFrom = effectiveFrom;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getDocumentLovName() {
		return documentLovName;
	}

	public void setDocumentLovName(String documentLovName) {
		this.documentLovName = documentLovName;
	}

	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((documentLovName == null) ? 0 : documentLovName.hashCode());
		result = prime * result + ((effectiveFrom == null) ? 0 : effectiveFrom.hashCode());
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
		TenantDocsPK other = (TenantDocsPK) obj;
		if (documentLovName == null) {
			if (other.documentLovName != null)
				return false;
		} else if (!documentLovName.equals(other.documentLovName))
			return false;
		if (effectiveFrom == null) {
			if (other.effectiveFrom != null)
				return false;
		} else if (!effectiveFrom.equals(other.effectiveFrom))
			return false;
		if (tenantCode == null) {
			if (other.tenantCode != null)
				return false;
		} else if (!tenantCode.equals(other.tenantCode))
			return false;
		return true;
	}
	
}
