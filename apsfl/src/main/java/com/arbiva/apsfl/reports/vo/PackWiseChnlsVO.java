package com.arbiva.apsfl.reports.vo;

import java.io.Serializable;


public class PackWiseChnlsVO implements Serializable{

	private static final long serialVersionUID = 1L;

	public enum UsersDataSearchParams{

		COLUMN_0("b.srvccode"),COLUMN_1("b. srvcname"),COLUMN_2("c.featurecode"),COLUMN_3("c.featurename");

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
	
	private String packCode;
	private String packName;
	private String chnlCode;
	private String chnlName;
	
	public String getPackCode() {
		return packCode;
	}
	public void setPackCode(String packCode) {
		this.packCode = packCode;
	}
	public String getPackName() {
		return packName;
	}
	public void setPackName(String packName) {
		this.packName = packName;
	}
	public String getChnlCode() {
		return chnlCode;
	}
	public void setChnlCode(String chnlCode) {
		this.chnlCode = chnlCode;
	}
	public String getChnlName() {
		return chnlName;
	}
	public void setChnlName(String chnlName) {
		this.chnlName = chnlName;
	}
	
	
}
