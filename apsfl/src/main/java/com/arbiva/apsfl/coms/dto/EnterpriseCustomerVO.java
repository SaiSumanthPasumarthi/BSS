/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */

public class EnterpriseCustomerVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum UsersDataSearchParams{

		COLUMN_0("custname"),COLUMN_2("regncode"),COLUMN_4("enttypelov"),COLUMN_5("officetypelov"),
		COLUMN_6("pocname"),COLUMN_7("stdcode");

		private String colName;

		private UsersDataSearchParams(String colName) {
			this.colName = colName;
		}

		public String getColName() {
			return colName;
		}

		public static String getColumns(String colName) {
			for (UsersDataSearchParams usersDataSearchParams : UsersDataSearchParams.values()) {
				if (usersDataSearchParams.toString().equals(colName)) {
					return usersDataSearchParams.getColName();
				}
			}
			return "";
		}
	}

	private String name;

	private String custType;
	
	private String custTypeLov;

	private String uidNo;

	private String titleLovName;

	private String orgName;

	private String dateOfIncorporation;

	private String emailId;

	private String billCycle;

	private String segment;

	private String channel;

	private String address1;

	private String address2;

	private String locality;

	private String city;

	private String state;

	private String area;

	private String mandal;

	private String pinCode;

	private String stdCode;

	private String landlineNo;

	private String fax;

	private String blAddress1;

	private String blAddress2;

	private String blLocality;

	private String blCity;

	private String blState;

	private String blArea;

	private String blMandal;

	private String blPinCode;

	private String blStdCode;

	private String blLandlineNo;

	private String blFax;

	private String pocName;

	private String pocmobile;

	private String officeTypeLov;

	private String paymentResponsible;

	private String custCode;

	private String tenantCode;
	
	private String tenantType;

	private String loginId;

	private String custId;
	
	private String ipAddress;
	
	private String pocDesignation;
	
	private String bulkUploadFlag;
	
	private String regnCode;
	
	private String entTypeLov;
	
	private String offcTypeLov;
	
	private String rowCount;
	
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getRowCount() {
		return rowCount;
	}

	public void setRowCount(String rowCount) {
		this.rowCount = rowCount;
	}

	
	public String getRegnCode() {
		return regnCode;
	}

	public void setRegnCode(String regnCode) {
		this.regnCode = regnCode;
	}

	public String getEntTypeLov() {
		return entTypeLov;
	}

	public void setEntTypeLov(String entTypeLov) {
		this.entTypeLov = entTypeLov;
	}

	public String getOffcTypeLov() {
		return offcTypeLov;
	}

	public void setOffcTypeLov(String offcTypeLov) {
		this.offcTypeLov = offcTypeLov;
	}
	
	public String getBulkUploadFlag() {
		return bulkUploadFlag;
	}

	public void setBulkUploadFlag(String bulkUploadFlag) {
		this.bulkUploadFlag = bulkUploadFlag;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getBlMandal() {
		return blMandal;
	}

	public void setBlMandal(String blMandal) {
		this.blMandal = blMandal;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getPaymentResponsible() {
		return paymentResponsible;
	}

	public void setPaymentResponsible(String paymentResponsible) {
		this.paymentResponsible = paymentResponsible;
	}

	public String getOfficeTypeLov() {
		return officeTypeLov;
	}

	public void setOfficeTypeLov(String officeTypeLov) {
		this.officeTypeLov = officeTypeLov;
	}

	public String getBlAddress1() {
		return blAddress1;
	}

	public void setBlAddress1(String blAddress1) {
		this.blAddress1 = blAddress1;
	}

	public String getBlAddress2() {
		return blAddress2;
	}

	public void setBlAddress2(String blAddress2) {
		this.blAddress2 = blAddress2;
	}

	public String getBlLocality() {
		return blLocality;
	}

	public void setBlLocality(String blLocality) {
		this.blLocality = blLocality;
	}

	public String getBlCity() {
		return blCity;
	}

	public void setBlCity(String blCity) {
		this.blCity = blCity;
	}

	public String getBlState() {
		return blState;
	}

	public void setBlState(String blState) {
		this.blState = blState;
	}

	public String getBlArea() {
		return blArea;
	}

	public void setBlArea(String blArea) {
		this.blArea = blArea;
	}

	public String getBlPinCode() {
		return blPinCode;
	}

	public void setBlPinCode(String blPinCode) {
		this.blPinCode = blPinCode;
	}

	public String getBlStdCode() {
		return blStdCode;
	}

	public void setBlStdCode(String blStdCode) {
		this.blStdCode = blStdCode;
	}

	public String getBlLandlineNo() {
		return blLandlineNo;
	}

	public void setBlLandlineNo(String blLandlineNo) {
		this.blLandlineNo = blLandlineNo;
	}

	public String getBlFax() {
		return blFax;
	}

	public void setBlFax(String blFax) {
		this.blFax = blFax;
	}

	public String getPocName() {
		return pocName;
	}

	public void setPocName(String pocName) {
		this.pocName = pocName;
	}

	public String getPocmobile() {
		return pocmobile;
	}

	public void setPocmobile(String pocmobile) {
		this.pocmobile = pocmobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getUidNo() {
		return uidNo;
	}

	public void setUidNo(String uidNo) {
		this.uidNo = uidNo;
	}

	public String getTitleLovName() {
		return titleLovName;
	}

	public void setTitleLovName(String titleLovName) {
		this.titleLovName = titleLovName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDateOfIncorporation() {
		return dateOfIncorporation;
	}

	public void setDateOfIncorporation(String dateOfIncorporation) {
		this.dateOfIncorporation = dateOfIncorporation;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getBillCycle() {
		return billCycle;
	}

	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getStdCode() {
		return stdCode;
	}

	public void setStdCode(String stdCode) {
		this.stdCode = stdCode;
	}

	public String getLandlineNo() {
		return landlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPocDesignation() {
		return pocDesignation;
	}

	public void setPocDesignation(String pocDesignation) {
		this.pocDesignation = pocDesignation;
	}

	public String getCustTypeLov() {
		return custTypeLov;
	}

	public void setCustTypeLov(String custTypeLov) {
		this.custTypeLov = custTypeLov;
	}

	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}
	
}
