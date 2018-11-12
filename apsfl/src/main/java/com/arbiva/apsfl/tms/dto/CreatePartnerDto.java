package com.arbiva.apsfl.tms.dto;

public class CreatePartnerDto {
	private String franchiseCode;
	private String franchiseName;
	private String franchiseNetworkName;
	private String parentPartnerCode;
	private String emailId;
	private String address1;
	private String areaCode;
	private String cityCode;
	private String stateCode;
	private String countryCode;
	private String currencyCode;
	private String zipCode;
	private String phoneno;
	
	
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
	public String getFranchiseNetworkName() {
		return franchiseNetworkName;
	}
	public void setFranchiseNetworkName(String franchiseNetworkName) {
		this.franchiseNetworkName = franchiseNetworkName;
	}
	
	public String getFranchiseCode() {
		return franchiseCode;
	}
	public void setFranchiseCode(String franchiseCode) {
		this.franchiseCode = franchiseCode;
	}
	public String getParentPartnerCode() {
		return parentPartnerCode;
	}
	public void setParentPartnerCode(String parentPartnerCode) {
		this.parentPartnerCode = parentPartnerCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getFranchiseName() {
		return franchiseName;
	}
	public void setFranchiseName(String franchiseName) {
		this.franchiseName = franchiseName;
	}
	public String getPartnerCode() {
		return parentPartnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.parentPartnerCode = partnerCode;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAreacode() {
		return areaCode;
	}
	public void setAreacode(String areacode) {
		this.areaCode = areacode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
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
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
