package com.arbiva.apsfl.tms.dto;

import java.util.List;

public class CpeOrderSaveDTO {
	
	private List<CpeHelperDTO> cpeModelsList;
	private String tenantCode;
	private String loginId;
	
	
	
	public List<CpeHelperDTO> getCpeModelsList() {
		return cpeModelsList;
	}
	public void setCpeModelsList(List<CpeHelperDTO> cpeModelsList) {
		this.cpeModelsList = cpeModelsList;
	}
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	
	

}
