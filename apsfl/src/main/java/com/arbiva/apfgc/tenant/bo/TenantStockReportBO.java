package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TenantStockReportBO implements Serializable {

	private static final long serialVersionUID = -5797994506568149031L;
	@Id
	@Column(name = "cpeslno")
	private String cpeSerialNo;
	@Column(name = "cpemacaddr")
	private String cpeMacId;
	@Column(name = "cpe_profile")
	private String cpeProfile;
	@Column(name = "batchdate")
	private String batchDate;
	@Column(name = "cafno")
	private String cafNo;
	@Column(name = "tenantcode")
	private String tenantCode;
	@Column(name = "districtname")
	private String district;
	@Column(name = "mandalname")
	private String mandal;
	@Column(name = "villagename")
	private String village;

	public String getCpeSerialNo() {
		return cpeSerialNo;
	}

	public void setCpeSerialNo(String cpeSerialNo) {
		this.cpeSerialNo = cpeSerialNo;
	}

	public String getCpeMacId() {
		return cpeMacId;
	}

	public void setCpeMacId(String cpeMacId) {
		this.cpeMacId = cpeMacId;
	}

	public String getCpeProfile() {
		return cpeProfile;
	}

	public void setCpeProfile(String cpeProfile) {
		this.cpeProfile = cpeProfile;
	}

	public String getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(String batchDate) {
		this.batchDate = batchDate;
	}

	public String getCafNo() {
		return cafNo;
	}

	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}


	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}
}
