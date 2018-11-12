/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class ChangePkgBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String prodCode;

	private String tenantCode;

	private String agruniqueid;
	
	private String actDate;

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getAgruniqueid() {
		return agruniqueid;
	}

	public void setAgruniqueid(String agruniqueid) {
		this.agruniqueid = agruniqueid;
	}

	public String getActDate() {
		return actDate;
	}

	public void setActDate(String actDate) {
		this.actDate = actDate;
	}
	
	
}
