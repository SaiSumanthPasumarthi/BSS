package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MsoCafNotCpeStockBo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "tenantcode")
	private String msoCode;
	@Column(name = "tenantname")
	private String msoNetwName;
	@Column(name = "MSO_Name")
	private String msoName;
	@Column(name = "MSO_MobileNO")
	private String msoMob;
	@Column(name = "districtname")
	private String msoDistrict;
	@Column(name = "Mso_Mandalname")
	private String msoMandal;
	@Column(name = "Mso_Villagename")
	private String msoVillage;

	@Column(name = "CAF_Not_Stock_Count")
	private String cafNotDoneCount;

	public String getMsoCode() {
		return msoCode;
	}

	public void setMsoCode(String msoCode) {
		this.msoCode = msoCode;
	}

	public String getMsoNetwName() {
		return msoNetwName;
	}

	public void setMsoNetwName(String msoNetwName) {
		this.msoNetwName = msoNetwName;
	}

	public String getMsoName() {
		return msoName;
	}

	public void setMsoName(String msoName) {
		this.msoName = msoName;
	}

	public String getMsoMob() {
		return msoMob;
	}

	public void setMsoMob(String msoMob) {
		this.msoMob = msoMob;
	}

	public String getMsoDistrict() {
		return msoDistrict;
	}

	public void setMsoDistrict(String msoDistrict) {
		this.msoDistrict = msoDistrict;
	}

	public String getMsoMandal() {
		return msoMandal;
	}

	public void setMsoMandal(String msoMandal) {
		this.msoMandal = msoMandal;
	}

	public String getMsoVillage() {
		return msoVillage;
	}

	public void setMsoVillage(String msoVillage) {
		this.msoVillage = msoVillage;
	}

	public String getCafNotDoneCount() {
		return cafNotDoneCount;
	}

	public void setCafNotDoneCount(String cafNotDoneCount) {
		this.cafNotDoneCount = cafNotDoneCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
