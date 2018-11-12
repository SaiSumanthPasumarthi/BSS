package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RiggedCafBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="lmocode")
	private String lmoCode;
	
	@Id
	@Column(name="networkname")
	private String lmoNwName;
	
	@Id
	@Column(name="lmoname")
	private String lmoName;
	
	@Id
	@Column(name="regoff_pocmob1")
	private String lmoMobile;
	
	@Id
	@Column(name="districtname")
	private String district;
	
	@Id
	@Column(name="mandalname")
	private String mandal;
	
	@Id
	@Column(name="villagename")
	private String village;
	
	@Column(name="Total_caf_count")
	private String totalCafCount;
	
	@Column(name="riggedcafs")
	private String notConnectCafCount;
	
	
	public String getLmoCode() {
		return lmoCode;
	}
	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}
	public String getLmoNwName() {
		return lmoNwName;
	}
	public void setLmoNwName(String lmoNwName) {
		this.lmoNwName = lmoNwName;
	}
	public String getLmoName() {
		return lmoName;
	}
	public void setLmoName(String lmoName) {
		this.lmoName = lmoName;
	}
	public String getLmoMobile() {
		return lmoMobile;
	}
	public void setLmoMobile(String lmoMobile) {
		this.lmoMobile = lmoMobile;
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
	public String getTotalCafCount() {
		return totalCafCount;
	}
	public void setTotalCafCount(String totalCafCount) {
		this.totalCafCount = totalCafCount;
	}
	public String getNotConnectCafCount() {
		return notConnectCafCount;
	}
	public void setNotConnectCafCount(String notConnectCafCount) {
		this.notConnectCafCount = notConnectCafCount;
	}
	

}
