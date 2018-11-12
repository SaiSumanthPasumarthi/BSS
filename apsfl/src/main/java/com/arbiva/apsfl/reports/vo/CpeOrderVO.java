package com.arbiva.apsfl.reports.vo;

import java.io.Serializable;


public class CpeOrderVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public enum UsersDataSearchParams{

		COLUMN_0("t1.tenantcode"),COLUMN_1("t.tenantname"),COLUMN_2("d.districtname"),COLUMN_3("m.mandalname"),
		COLUMN_4("cpe.cpetypelov"),COLUMN_5("cpe.cpe_model"),COLUMN_6("t1.dmnddate"),COLUMN_7("cp.purchasecost"),COLUMN_8("t1.dmndqty"),COLUMN_9("t1.dmndprice");

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

	private String msoCode;
	private String msoName;
	private String district;
	private String mandal;
	private String cpeType;
	private String cpeModel;
	private String orderDate;
	private String cpeCost;
	private String qnty;
	private String totalCost;
	
	public String getMsoCode() {
		return msoCode;
	}
	public void setMsoCode(String msoCode) {
		this.msoCode = msoCode;
	}
	public String getMsoName() {
		return msoName;
	}
	public void setMsoName(String msoName) {
		this.msoName = msoName;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	public String getCpeType() {
		return cpeType;
	}
	public void setCpeType(String cpeType) {
		this.cpeType = cpeType;
	}
	public String getCpeModel() {
		return cpeModel;
	}
	public void setCpeModel(String cpeModel) {
		this.cpeModel = cpeModel;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getCpeCost() {
		return cpeCost;
	}
	public void setCpeCost(String cpeCost) {
		this.cpeCost = cpeCost;
	}
	public String getQnty() {
		return qnty;
	}
	public void setQnty(String qnty) {
		this.qnty = qnty;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	
	
}
