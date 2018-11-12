package com.arbiva.apsfl.reports.vo;

import java.io.Serializable;

public class MsoListVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tenantCode;
	private String tenantName;
	private String pocMobileNumber;
	private String emiDemandQuantity;
	private String emi36DemandQuantity;
	private String emi48DemandQuantity;
	private String mandalName;
	private String districtName;
	private String regOfficePocName;

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getPocMobileNumber() {
		return pocMobileNumber;
	}

	public void setPocMobileNumber(String pocMobileNumber) {
		this.pocMobileNumber = pocMobileNumber;
	}

	public String getEmiDemandQuantity() {
		return emiDemandQuantity;
	}

	public void setEmiDemandQuantity(String emiDemandQuantity) {
		this.emiDemandQuantity = emiDemandQuantity;
	}

	public String getEmi36DemandQuantity() {
		return emi36DemandQuantity;
	}

	public void setEmi36DemandQuantity(String emi36DemandQuantity) {
		this.emi36DemandQuantity = emi36DemandQuantity;
	}

	public String getEmi48DemandQuantity() {
		return emi48DemandQuantity;
	}

	public void setEmi48DemandQuantity(String emi48DemandQuantity) {
		this.emi48DemandQuantity = emi48DemandQuantity;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getRegOfficePocName() {
		return regOfficePocName;
	}

	public void setRegOfficePocName(String regOfficePocName) {
		this.regOfficePocName = regOfficePocName;
	}

}