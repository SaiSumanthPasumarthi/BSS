package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

public class CustomerCafBO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum UsersDataSearchParams{

		COLUMN_0("cafNo"),COLUMN_7("depBal"),COLUMN_8("regBal"),COLUMN_5("cf.actdate"),
		COLUMN_6("lmoCode"),COLUMN_4("cpeslno"),COLUMN_1("custName"),COLUMN_2("aadharno"), 
		COLUMN_3("apsfluniqueid"),COLUMN_9("status"),COLUMN_10("contactMob");

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
	
	private String cafNo;
	
	private String depBal;
	
	private String regBal;
	
	private String lmoCode;
	
	private String custName;
	
	private String mName;
	
	private String lName;
	
	private String custId;
	
	private String cpeSrlNo;
	
	private String status;
	
	private String actdate;
	
	private String aadharNo;
	
	private String apsflUniqueId;
	
	private String tenantType;
	
	private String tenantCode;
	
	private String contactMob;

	
	public String getContactMob() {
		return contactMob;
	}

	public void setContactMob(String contactMob) {
		this.contactMob = contactMob;
	}

	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	
	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getApsflUniqueId() {
		return apsflUniqueId;
	}

	public void setApsflUniqueId(String apsflUniqueId) {
		this.apsflUniqueId = apsflUniqueId;
	}

	public String getCafNo() {
		return cafNo;
	}

	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}

	public String getDepBal() {
		return depBal;
	}

	public void setDepBal(String depBal) {
		this.depBal = depBal;
	}

	public String getRegBal() {
		return regBal;
	}

	public void setRegBal(String regBal) {
		this.regBal = regBal;
	}

	public String getLmoCode() {
		return lmoCode;
	}

	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	public String getCpeSrlNo() {
		return cpeSrlNo;
	}

	public void setCpeSrlNo(String cpeSrlNo) {
		this.cpeSrlNo = cpeSrlNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActdate() {
		return actdate;
	}

	public void setActdate(String actdate) {
		this.actdate = actdate;
	}
}
