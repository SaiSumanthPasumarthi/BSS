package com.arbiva.apsfl.coms.dto;

import java.util.Map;

public class CafsVO {
	
	public enum CafsVOColumn {

		COLUMN_0("cafno"),
		COLUMN_1("custname"),
		COLUMN_2("cpeslno"),
		COLUMN_3("cpemacaddr"),
		COLUMN_4("cpeplace"),
		COLUMN_5("stbslno"),
		COLUMN_6("stbMacAddr"),
		COLUMN_7("cafPhoneNo");
		
		private String colName;

		private CafsVOColumn(String colName) {
			this.colName = colName;
		}

		public String getColName() {
			return colName;
		}

		public static String getColumns(String colName) {
			for (CafsVOColumn usersDataSearchParams : CafsVOColumn.values()) {
				if (usersDataSearchParams.toString().equals(colName)) {
					return usersDataSearchParams.getColName();
				}
			}
			return "";
		}
	}
	
	private String cafNo;

	private String lmoCode;
	
	private String cpeslNo;

	private String cpeMacAddr;
	
	private String custId;
	
	private String fName;
	
	private String lName;
	
	private String cpeAddr;
	
    private String stbSrlNo;
	
	private String stbMacAddr;
	
	private Map<String,String> stbSrlNoMacAddr;
	
	private String cafPhoneNo;
	
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getCafPhoneNo() {
		return cafPhoneNo;
	}

	public void setCafPhoneNo(String cafPhoneNo) {
		this.cafPhoneNo = cafPhoneNo;
	}
	
	public Map<String, String> getStbSrlNoMacAddr() {
		return stbSrlNoMacAddr;
	}

	public void setStbSrlNoMacAddr(Map<String, String> stbSrlNoMacAddr) {
		this.stbSrlNoMacAddr = stbSrlNoMacAddr;
	}
	
	public String getStbSrlNo() {
		return stbSrlNo;
	}

	public void setStbSrlNo(String stbSrlNo) {
		this.stbSrlNo = stbSrlNo;
	}

	public String getStbMacAddr() {
		return stbMacAddr;
	}

	public void setStbMacAddr(String stbMacAddr) {
		this.stbMacAddr = stbMacAddr;
	}
	
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getCpeAddr() {
		return cpeAddr;
	}

	public void setCpeAddr(String cpeAddr) {
		this.cpeAddr = cpeAddr;
	}

	public String getCafNo() {
		return cafNo;
	}

	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}

	public String getLmoCode() {
		return lmoCode;
	}

	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}

	public String getCpeslNo() {
		return cpeslNo;
	}

	public void setCpeslNo(String cpeslNo) {
		this.cpeslNo = cpeslNo;
	}

	public String getCpeMacAddr() {
		return cpeMacAddr;
	}

	public void setCpeMacAddr(String cpeMacAddr) {
		this.cpeMacAddr = cpeMacAddr;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
	
}
