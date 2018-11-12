package com.arbiva.apsfl.reports.vo;

import java.io.Serializable;


public class SubsDtlsVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public enum UsersDataSearchParams{

		COLUMN_0("cfs.stbmacaddr"),COLUMN_1("cfs.parentcafno"),COLUMN_2("cs.prodcode "),COLUMN_3("cfs.nwsubscode"),
		COLUMN_4("cs.actdate"),COLUMN_5("cs.deactdate"),COLUMN_6("cs.suspdate"),COLUMN_7("cs.suspdate"),COLUMN_8("cs.suspdate")
		,COLUMN_9("cs.suspdate"),COLUMN_10("cs.suspdate"),COLUMN_11("cs.suspdate"),COLUMN_12("cs.suspdate"),COLUMN_13("cs.suspdate")
		,COLUMN_14("cs.suspdate"),COLUMN_15("cs.suspdate"),COLUMN_16("cs.suspdate"),COLUMN_17("cs.suspdate"),COLUMN_18("cs.suspdate")
		,COLUMN_19("cs.suspdate"),COLUMN_20("cs.suspdate"),COLUMN_21("cs.suspdate"),COLUMN_22("cs.suspdate"),COLUMN_23("cs.suspdate");

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
	private String cafNo;
	private String packName;
	private String subsCode;
	private String actDate;
	private String deactDate;
	private String suspDate;
	private String resumeDate;
	private String blacklistDate;
	private String iptvPackName;
	private String custId;
	private String aadharNo;
	private String onuMacId;
	private String instAddr1;
	private String instAddr2;
	private String instArea;
	private String instCity;
	private String instLocality;
	private String instMandal;
	private String instDist;
	private String instPin;
	private String latitude;
	private String longitude;
	private String status;
	
	public String getStbMacId() {
		return stbMacId;
	}
	public void setStbMacId(String stbMacId) {
		this.stbMacId = stbMacId;
	}
	public String getCafNo() {
		return cafNo;
	}
	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}
	public String getPackName() {
		return packName;
	}
	public void setPackName(String packName) {
		this.packName = packName;
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
	public String getDeactDate() {
		return deactDate;
	}
	public void setDeactDate(String deactDate) {
		this.deactDate = deactDate;
	}
	public String getSuspDate() {
		return suspDate;
	}
	public void setSuspDate(String suspDate) {
		this.suspDate = suspDate;
	}
	public String getResumeDate() {
		return resumeDate;
	}
	public void setResumeDate(String resumeDate) {
		this.resumeDate = resumeDate;
	}
	public String getBlacklistDate() {
		return blacklistDate;
	}
	public void setBlacklistDate(String blacklistDate) {
		this.blacklistDate = blacklistDate;
	}
	public String getIptvPackName() {
		return iptvPackName;
	}
	public void setIptvPackName(String iptvPackName) {
		this.iptvPackName = iptvPackName;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getOnuMacId() {
		return onuMacId;
	}
	public void setOnuMacId(String onuMacId) {
		this.onuMacId = onuMacId;
	}
	public String getInstAddr1() {
		return instAddr1;
	}
	public void setInstAddr1(String instAddr1) {
		this.instAddr1 = instAddr1;
	}
	public String getInstAddr2() {
		return instAddr2;
	}
	public void setInstAddr2(String instAddr2) {
		this.instAddr2 = instAddr2;
	}
	public String getInstArea() {
		return instArea;
	}
	public void setInstArea(String instArea) {
		this.instArea = instArea;
	}
	public String getInstCity() {
		return instCity;
	}
	public void setInstCity(String instCity) {
		this.instCity = instCity;
	}
	public String getInstLocality() {
		return instLocality;
	}
	public void setInstLocality(String instLocality) {
		this.instLocality = instLocality;
	}
	public String getInstMandal() {
		return instMandal;
	}
	public void setInstMandal(String instMandal) {
		this.instMandal = instMandal;
	}
	public String getInstDist() {
		return instDist;
	}
	public void setInstDist(String instDist) {
		this.instDist = instDist;
	}
	public String getInstPin() {
		return instPin;
	}
	public void setInstPin(String instPin) {
		this.instPin = instPin;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
