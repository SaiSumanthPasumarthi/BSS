package com.arbiva.apsfl.reports.vo;

import java.io.Serializable;


public class TransactionLogVO implements Serializable{

	private static final long serialVersionUID = 1L;

	public enum UsersDataSearchParams{

		COLUMN_0("cf.cafno"),COLUMN_1("cf.aadharno"),COLUMN_2("cfs.stbmacaddr"),COLUMN_3("cpeslno"),
		COLUMN_4("cfs.stbslno"),COLUMN_5("srvccodeaddl"),COLUMN_7("executed_date");

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
	private String onuSlno;
	private String stbSlno;
	private String iptvPack;
	private String transaction;
	private String txnDate;
	private String status;
	
	
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
	public String getOnuSlno() {
		return onuSlno;
	}
	public void setOnuSlno(String onuSlno) {
		this.onuSlno = onuSlno;
	}
	public String getStbSlno() {
		return stbSlno;
	}
	public void setStbSlno(String stbSlno) {
		this.stbSlno = stbSlno;
	}
	public String getIptvPack() {
		return iptvPack;
	}
	public void setIptvPack(String iptvPack) {
		this.iptvPack = iptvPack;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	public String getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
