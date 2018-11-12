package com.arbiva.apsfl.reports.vo;

import java.io.Serializable;


public class SrvsWiseSubsVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public enum UsersDataSearchParams{

		COLUMN_0("a.srvccode");

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
	
	private String iptvpackname;
	private String total;
	
	public String getIptvpackname() {
		return iptvpackname;
	}
	public void setIptvpackname(String iptvpackname) {
		this.iptvpackname = iptvpackname;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	
}
