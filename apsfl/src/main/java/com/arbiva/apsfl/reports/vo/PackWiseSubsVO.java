package com.arbiva.apsfl.reports.vo;

import java.io.Serializable;


public class PackWiseSubsVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public enum UsersDataSearchParams{

		COLUMN_0("cf.aadharno"),COLUMN_1("cfs.stbmacaddr"),COLUMN_2("cfs.stbslno"),COLUMN_3("cs.srvccode");

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
	
	private String aadharNo;
	private String stbMacId;
	private String stbSlno;
	private String packName;
	
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getStbMacId() {
		return stbMacId;
	}
	public void setStbMacId(String stbMacId) {
		this.stbMacId = stbMacId;
	}
	public String getStbSlno() {
		return stbSlno;
	}
	public void setStbSlno(String stbSlno) {
		this.stbSlno = stbSlno;
	}
	public String getPackName() {
		return packName;
	}
	public void setPackName(String packName) {
		this.packName = packName;
	}
	
	

}
