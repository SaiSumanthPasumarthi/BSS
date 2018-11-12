package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LmoStockCountBO implements Serializable {

	private static final long serialVersionUID = -7588019912337078760L;
	@Id
	@Column(name = "tenantcode")
	private String lmoCode;
	@Column(name = "tenantname")
	private String networkName;
	@Column(name = "LMO_Name")
	private String contactName;
	@Column(name = "LMO_MobileNO")
	private String mobileNo;
	
	@Id
	@Column(name = "districtname")
	private String district;
	
	@Id
	@Column(name = "Lmo_Mandalname")
	private String mandal;
	
	@Id
	@Column(name = "Lmo_Villagename")
	private String village;
	
	@Id
	@Column(name = "CAF_Done_Stock_Count")
	private long cafCount;
	@Column(name = "CAF_Not_Stock_Count")
	private long stockCount;
	
	@Id
	@Column(name="CAF_Created_DATE")
	private String createddate;

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getLmoCode() {
		return lmoCode;
	}

	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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

	public long getCafCount() {
		return cafCount;
	}

	public void setCafCount(long cafCount) {
		this.cafCount = cafCount;
	}

	public long getStockCount() {
		return stockCount;
	}

	public void setStockCount(long stockCount) {
		this.stockCount = stockCount;
	}

}
