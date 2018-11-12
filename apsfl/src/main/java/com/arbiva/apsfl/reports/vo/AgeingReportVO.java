package com.arbiva.apsfl.reports.vo;

import java.io.Serializable;


public class AgeingReportVO implements Serializable{

	private static final long serialVersionUID = 1L; 
	
	public enum UsersDataSearchParams{

		COLUMN_0("cfs.parentcafno"),COLUMN_1("cf.custcode"),COLUMN_2("cfs.stbmacaddr"),COLUMN_3("cfs.subscode"),
		COLUMN_4("cf.custid"),COLUMN_5("cs.srvccode"),COLUMN_6("cs.actdate");

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
	private String aadharNo;
	private String stbMacAddr;
	private String subsCode;
	private String custId;
	private String iptvPack;
	private String actDate;
	private String status;
	private String ageing;
	
	public String getCafNo() {
		return cafNo;
	}
	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getStbMacAddr() {
		return stbMacAddr;
	}
	public void setStbMacAddr(String stbMacAddr) {
		this.stbMacAddr = stbMacAddr;
	}
	public String getSubsCode() {
		return subsCode;
	}
	public void setSubsCode(String subsCode) {
		this.subsCode = subsCode;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getIptvPack() {
		return iptvPack;
	}
	public void setIptvPack(String iptvPack) {
		this.iptvPack = iptvPack;
	}
	public String getActDate() {
		return actDate;
	}
	public void setActDate(String actDate) {
		this.actDate = actDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAgeing() {
		return ageing;
	}
	public void setAgeing(String ageing) {
		this.ageing = ageing;
	}
}
