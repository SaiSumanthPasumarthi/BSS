package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class OLTMasterDataBO implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="pop_substnuid")
	private String popSubstnUID;
	
	@Id
	@Column(name="pop_name")
	private String popName;
	
	@Id
	@Column(name="pop_olt_serialno")
	private String popOltSerialNo;
	
	@Id
	@Column(name="pop_olt_ipaddress")
	private String popOltIPAddress;
	
	@Column(name="districtname")
	private String districtName;
	
	@Column(name="mandalname")
	private String mandalName;
	
	
	
	public String getPopSubstnUID() {
		return popSubstnUID;
	}
	public void setPopSubstnUID(String popSubstnUID) {
		this.popSubstnUID = popSubstnUID;
	}
	public String getPopName() {
		return popName;
	}
	public void setPopName(String popName) {
		this.popName = popName;
	}
	public String getPopOltSerialNo() {
		return popOltSerialNo;
	}
	public void setPopOltSerialNo(String popOltSerialNo) {
		this.popOltSerialNo = popOltSerialNo;
	}
	public String getPopOltIPAddress() {
		return popOltIPAddress;
	}
	public void setPopOltIPAddress(String popOltIPAddress) {
		this.popOltIPAddress = popOltIPAddress;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	
	

}
