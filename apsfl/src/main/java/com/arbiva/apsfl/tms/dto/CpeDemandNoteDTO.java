package com.arbiva.apsfl.tms.dto;

import java.util.List;

public class CpeDemandNoteDTO {
	
	
	private String tenantCode;
	private List<LmoCpeRequestDTO> lmoCpeRequest;
	
	
	
	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public List<LmoCpeRequestDTO> getLmoCpeRequest() {
		return lmoCpeRequest;
	}

	public void setLmoCpeRequest(List<LmoCpeRequestDTO> lmoCpeRequest) {
		this.lmoCpeRequest = lmoCpeRequest;
	}
	
	

}
