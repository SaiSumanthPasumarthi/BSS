package com.arbiva.apsfl.coms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author srinivasa
 *
 */
public class CustomerDTO {

	private static final long serialVersionUID = 1L;

	public CustomerDTO() {
	
	}
	
	private String custid;
	
	private String aadharno;
		
	private String dob;
	
	private String title;
	
	private String firstname;
	
	private String lastname;
	
	private String contactno;
	
	private String emailid;
	
	private String address;
	
	private String landline;
	
	@JsonProperty("village")
	private String village;
	
	@JsonProperty("mandal")
	private String mandal;
	
	@JsonProperty("districtCode")
	private String districtCode;
	
	@JsonProperty("stateCode")
	private String stateCode;
	
	@JsonProperty("countryCode")
	private String countryCode;
	
	@JsonProperty("countryISO2")
	private String countryISO2;
	
	private String remarks;
	
	private String mname;
	
	private String billFrequency;
	
	private String custType;
	
	private String lmoCode;

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getAadharno() {
		return aadharno;
	}

	public void setAadharno(String aadharno) {
		this.aadharno = aadharno;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryISO2() {
		return countryISO2;
	}

	public void setCountryISO2(String countryISO2) {
		this.countryISO2 = countryISO2;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getBillFrequency() {
		return billFrequency;
	}

	public void setBillFrequency(String billFrequency) {
		this.billFrequency = billFrequency;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getLmoCode() {
		return lmoCode;
	}

	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}
}
