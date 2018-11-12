package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MsoDetailsWithLmosBO implements Serializable {

	private static final long serialVersionUID = 1811740318585251741L;
	@Id
	@Column(name = "MSO")
	private String msoCode;
	@Column(name = "MSONAME")
	private String msoNetwName;
	@Column(name = "MSO_ContactName")
	private String msoName;
	@Column(name = "MSO_MobileNo")
	private String msoMob;
	@Column(name = "mso_district")
	private String msoDistrict;
	@Column(name = "mso_mandal")
	private String msoMandal;
	@Column(name = "mso_villagename")
	private String msoVillage;
	@Column(name = "mso_createdon")
	private String mso_createdon;
	@Id
	@Column(name = "LMO")
	private String lmoCode;
	@Column(name = "LMONAME")
	private String lmoNetwName;
	@Column(name = "LMO_ContactName")
	private String lmoName;
	@Column(name = "LMO_MobileNo")
	private String lmoMob;
	@Column(name = "lmo_district")
	private String lmoDistrict;
	@Column(name = "lmo_mandal")
	private String lmoMandal;
	@Column(name = "lmo_villagename")
	private String lmoVillage;
	@Column(name = "lmo_createdon")
	private String lmo_createdon;

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

	public String getMso_createdon() {
		return mso_createdon;
	}

	public void setMso_createdon(String mso_createdon) {
		this.mso_createdon = mso_createdon;
	}

	public String getLmoCode() {
		return lmoCode;
	}

	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}

	public String getLmoNetwName() {
		return lmoNetwName;
	}

	public void setLmoNetwName(String lmoNetwName) {
		this.lmoNetwName = lmoNetwName;
	}

	public String getLmoName() {
		return lmoName;
	}

	public void setLmoName(String lmoName) {
		this.lmoName = lmoName;
	}

	public String getLmoMob() {
		return lmoMob;
	}

	public void setLmoMob(String lmoMob) {
		this.lmoMob = lmoMob;
	}

	public String getLmoDistrict() {
		return lmoDistrict;
	}

	public void setLmoDistrict(String lmoDistrict) {
		this.lmoDistrict = lmoDistrict;
	}

	public String getLmoMandal() {
		return lmoMandal;
	}

	public void setLmoMandal(String lmoMandal) {
		this.lmoMandal = lmoMandal;
	}

	public String getLmoVillage() {
		return lmoVillage;
	}

	public void setLmoVillage(String lmoVillage) {
		this.lmoVillage = lmoVillage;
	}

	public String getLmo_createdon() {
		return lmo_createdon;
	}

	public void setLmo_createdon(String lmo_createdon) {
		this.lmo_createdon = lmo_createdon;
	}

}
