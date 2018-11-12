package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;
import java.util.List;

public class RsTemplateDetailsDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public RsTemplateDetailsDTO()
	{
		
	}
	
	private String rstmplCode;
	private String regioncode;
	private List<RsTemplateDetailsListDTO> rsTemplateList;
	private String status;
	
	public String getRstmplCode() {
		return rstmplCode;
	}
	public void setRstmplCode(String rstmplCode) {
		this.rstmplCode = rstmplCode;
	}
	public String getRegioncode() {
		return regioncode;
	}
	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
	}
	public List<RsTemplateDetailsListDTO> getRsTemplateList() {
		return rsTemplateList;
	}
	public void setRsTemplateList(List<RsTemplateDetailsListDTO> rsTemplateList) {
		this.rsTemplateList = rsTemplateList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
