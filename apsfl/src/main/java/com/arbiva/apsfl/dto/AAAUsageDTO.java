package com.arbiva.apsfl.dto;

import java.util.List;

import com.arbiva.apsfl.tms.model.AAAUsageBO;

public class AAAUsageDTO {
	
	public enum AAAUsageColumnName {
		
		
		COLUMN_0("starttime"),
		COLUMN_1("endtime"),
		COLUMN_2("sessiontime"),
		COLUMN_3("accntterminatecause"),
		COLUMN_4("cafno"),
		COLUMN_5("custname"),
		COLUMN_6("substnname"),
		COLUMN_7("totalsize"),
		COLUMN_8("dwnldsize"),
		COLUMN_9("upldsize");

		private String colName;

		private AAAUsageColumnName(String colName) {
			this.colName = colName;
		}

		public String getColName() {
			return colName;
		}

		public static String getColumns(String colName) {
			for (AAAUsageColumnName columnName : AAAUsageColumnName.values()) {
				if (columnName.toString().equals(colName)) {
					return columnName.getColName();
				}
			}
			return "";
		}
	}
	
	private String districtId;
	private String MandalId;
	private String VillageId;
	private String fromDate;
	private String toDate;
	private String accountCafNo;
	private String aadharNo;
	private String mobileNO;
	private String year;
	private String month;
	
	private List<AAAUsageBO> list ;
	
	private int count;
	
	
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public List<AAAUsageBO> getList() {
		return list;
	}
	public void setList(List<AAAUsageBO> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getMandalId() {
		return MandalId;
	}
	public void setMandalId(String mandalId) {
		MandalId = mandalId;
	}
	public String getVillageId() {
		return VillageId;
	}
	public void setVillageId(String villageId) {
		VillageId = villageId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getMobileNO() {
		return mobileNO;
	}
	public void setMobileNO(String mobileNO) {
		this.mobileNO = mobileNO;
	}
	public String getAccountCafNo() {
		return accountCafNo;
	}
	public void setAccountCafNo(String accountCafNo) {
		this.accountCafNo = accountCafNo;
	}
	
}
