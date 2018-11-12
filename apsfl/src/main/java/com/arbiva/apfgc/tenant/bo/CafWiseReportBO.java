package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class CafWiseReportBO implements Serializable {
	
	private static final long serialVersionUID = -1157473540307390846L;

	@Column(name="maxdate")
	private String maxdat;
	
	@Column(name="districtname")
	private String districtname;
	
	@Id
	@Column(name="villagename")
	private String  village;
	
	@Id
	@Column(name="tname")
	private String  lmoName;
	
	@Column(name="hhday")
	private Long cafsCountOnTheDay;
	
	@Column(name="hhcum")
	private Long cumulativeCafs;
	
	@Column(name="pvtday")
	private Long epCafsCountPrivateDay;
	
	@Column(name="govtday")
	private Long epCafsCountGovtDay;
	
	@Column(name="pvtcum")
	private Long epCafsCountPrivateCu;
	
	@Column(name="govtcum")
	private Long epCafsCountGovtCu;
	
	@Transient
	private Long cumulativeCafsEntered;

	public String getMaxdat() {
		return maxdat;
	}

	public void setMaxdat(String maxdat) {
		this.maxdat = maxdat;
	}

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getLmoName() {
		return lmoName;
	}

	public void setLmoName(String lmoName) {
		this.lmoName = lmoName;
	}

	public Long getCafsCountOnTheDay() {
		return cafsCountOnTheDay;
	}

	public void setCafsCountOnTheDay(Long cafsCountOnTheDay) {
		this.cafsCountOnTheDay = cafsCountOnTheDay;
	}

	public Long getCumulativeCafs() {
		return cumulativeCafs;
	}

	public void setCumulativeCafs(Long cumulativeCafs) {
		this.cumulativeCafs = cumulativeCafs;
	}

	public Long getEpCafsCountPrivateDay() {
		return epCafsCountPrivateDay;
	}

	public void setEpCafsCountPrivateDay(Long epCafsCountPrivateDay) {
		this.epCafsCountPrivateDay = epCafsCountPrivateDay;
	}

	public Long getEpCafsCountGovtDay() {
		return epCafsCountGovtDay;
	}

	public void setEpCafsCountGovtDay(Long epCafsCountGovtDay) {
		this.epCafsCountGovtDay = epCafsCountGovtDay;
	}

	public Long getEpCafsCountPrivateCu() {
		return epCafsCountPrivateCu;
	}

	public void setEpCafsCountPrivateCu(Long epCafsCountPrivateCu) {
		this.epCafsCountPrivateCu = epCafsCountPrivateCu;
	}

	public Long getEpCafsCountGovtCu() {
		return epCafsCountGovtCu;
	}

	public void setEpCafsCountGovtCu(Long epCafsCountGovtCu) {
		this.epCafsCountGovtCu = epCafsCountGovtCu;
	}

	public Long getCumulativeCafsEntered() {
		return  this.epCafsCountPrivateCu + this.epCafsCountGovtCu + this.cumulativeCafs;
	}

	public void setCumulativeCafsEntered(Long cumulativeCafsEntered) {
		
		this.cumulativeCafsEntered = cumulativeCafsEntered;
	}

}
