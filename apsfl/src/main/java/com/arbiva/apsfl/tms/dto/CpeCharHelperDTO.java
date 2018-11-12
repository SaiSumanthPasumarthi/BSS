package com.arbiva.apsfl.tms.dto;

import java.util.List;

public class CpeCharHelperDTO {
	
	private List<RevenueSharingTemplateMaster> cpeTmplList;
	
	private String chargeName;
	
	private String templCode;
	
	private List<ProductAgreementPartnersVO> partnrsList;
	
	private String aggrId;
	private String aggrFDate;
	private String tenantName;
	private String tenantType;

	public String getAggrId() {
		return aggrId;
	}

	public void setAggrId(String aggrId) {
		this.aggrId = aggrId;
	}

	public String getAggrFDate() {
		return aggrFDate;
	}

	public void setAggrFDate(String aggrFDate) {
		this.aggrFDate = aggrFDate;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}

	public String getTemplCode() {
		return templCode;
	}

	public void setTemplCode(String templCode) {
		this.templCode = templCode;
	}

	public List<ProductAgreementPartnersVO> getPartnrsList() {
		return partnrsList;
	}

	public void setPartnrsList(List<ProductAgreementPartnersVO> partnrsList) {
		this.partnrsList = partnrsList;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public List<RevenueSharingTemplateMaster> getCpeTmplList() {
		return cpeTmplList;
	}

	public void setCpeTmplList(List<RevenueSharingTemplateMaster> cpeTmplList) {
		this.cpeTmplList = cpeTmplList;
	}

}
