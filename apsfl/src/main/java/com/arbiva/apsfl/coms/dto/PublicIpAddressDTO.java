package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author sreev
 * @since 13/06/2017
 *
 */
public class PublicIpAddressDTO implements Serializable{

	 
	private static final long serialVersionUID = 1L;
	
	 
	
	private String cafNum;
	
	private String tenantCode;
	
	private String tenantType;
	
	private String trackId;
	
	private String custId;
	
	private String pIpAddress;
	
	
	
	public String getCafNum() {
		return cafNum;
	}
	public String getTrackId() {
		return trackId;
	}
	public String getCustId() {
		return custId;
	}
	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getTenantCode() {
		return tenantCode;
	}
	public String getTenantType() {
		return tenantType;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}
	 
	public void setCafNum(String cafNum) {
		this.cafNum = cafNum;
	}
	public String getpIpAddress() {
		return pIpAddress;
	}
	public void setpIpAddress(String pIpAddress) {
		this.pIpAddress = pIpAddress;
	}
	
	

}
