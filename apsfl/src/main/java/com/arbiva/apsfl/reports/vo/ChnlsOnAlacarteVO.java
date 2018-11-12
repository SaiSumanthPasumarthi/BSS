package com.arbiva.apsfl.reports.vo;

import java.io.Serializable;

public class ChnlsOnAlacarteVO implements Serializable{

	private static final long serialVersionUID = 1L;

	public enum UsersDataSearchParams{

		COLUMN_0("cfs.parentcafno"),COLUMN_1("cs.featurecodes"),COLUMN_2("sf.featurename"),COLUMN_3("cf.custcode"),
		COLUMN_4("cfs.nwsubscode"),COLUMN_5("cfs.stbslno"),COLUMN_6("cfs.stbmacaddr");

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
	private String chnlCode;
	private String chalName;
	private String aadharNo;
	private String subsCode;
	private String stbSlno;
	private String stbMacId;
	
	public String getCafNo() {
		return cafNo;
	}
	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}
	public String getChnlCode() {
		return chnlCode;
	}
	public void setChnlCode(String chnlCode) {
		this.chnlCode = chnlCode;
	}
	public String getChalName() {
		return chalName;
	}
	public void setChalName(String chalName) {
		this.chalName = chalName;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getSubsCode() {
		return subsCode;
	}
	public void setSubsCode(String subsCode) {
		this.subsCode = subsCode;
	}
	public String getStbSlno() {
		return stbSlno;
	}
	public void setStbSlno(String stbSlno) {
		this.stbSlno = stbSlno;
	}
	public String getStbMacId() {
		return stbMacId;
	}
	public void setStbMacId(String stbMacId) {
		this.stbMacId = stbMacId;
	}
	
}
