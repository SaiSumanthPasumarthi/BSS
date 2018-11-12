package com.arbiva.apsfl.reports.vo;

import java.io.Serializable;


public class StatusWiseSubsVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public enum UsersDataSearchParams{

		COLUMN_0("cfs.stbmacaddr"),COLUMN_1("cfs.nwsubscode"),COLUMN_2("cs.actdate"),COLUMN_3("cs.suspdate"),
		COLUMN_4("cs.deactdate"),COLUMN_5("bl.approvedon");

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

	private String stbMacId;
	private String subsCode;
	private String actDate;
	private String suspDate;
	private String deactDate;
	private String blackListDate;
	
	public String getStbMacId() {
		return stbMacId;
	}
	public void setStbMacId(String stbMacId) {
		this.stbMacId = stbMacId;
	}
	public String getSubsCode() {
		return subsCode;
	}
	public void setSubsCode(String subsCode) {
		this.subsCode = subsCode;
	}
	public String getActDate() {
		return actDate;
	}
	public void setActDate(String actDate) {
		this.actDate = actDate;
	}
	public String getSuspDate() {
		return suspDate;
	}
	public void setSuspDate(String suspDate) {
		this.suspDate = suspDate;
	}
	public String getDeactDate() {
		return deactDate;
	}
	public void setDeactDate(String deactDate) {
		this.deactDate = deactDate;
	}
	public String getBlackListDate() {
		return blackListDate;
	}
	public void setBlackListDate(String blackListDate) {
		this.blackListDate = blackListDate;
	}
	
}
