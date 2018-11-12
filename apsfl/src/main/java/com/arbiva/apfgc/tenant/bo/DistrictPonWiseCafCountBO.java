package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DistrictPonWiseCafCountBO implements Serializable{


	private static final long serialVersionUID = -897117689589035732L;
	
	@Id
	@Column(name = "lmocode")
	private String lmoCode;
	
	@Id
	@Column(name = "districtname")
	private String district;
	
	@Id
	@Column(name = "villagename")
	private String villagename;
	
	@Id
	@Column(name = "mandalname")
	private String mandal;
	
	@Id
	@Column(name = "pop_name")
	private String popName;
	
	@Id
	@Column(name = "portno")
	private String portNo;
	
	@Id
	@Column(name = "COUNT(c.cafno)")
	private String cafCount;

	public String getLmoCode() {
		return lmoCode;
	}

	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
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
	

	public String getVillagename() {
		return villagename;
	}

	public void setVillagename(String villagename) {
		this.villagename = villagename;
	}

	public String getPopName() {
		return popName;
	}

	public void setPopName(String popName) {
		this.popName = popName;
	}

	public String getPortNo() {
		return portNo;
	}

	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}

	public String getCafCount() {
		return cafCount;
	}

	public void setCafCount(String cafCount) {
		this.cafCount = cafCount;
	}
	
	

}
