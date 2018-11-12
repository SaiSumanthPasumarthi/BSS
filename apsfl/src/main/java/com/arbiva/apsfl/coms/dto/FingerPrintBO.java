/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class FingerPrintBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum UsersDataSearchParams{

		COLUMN_0("cafno"),COLUMN_1("custname"),COLUMN_2("aadharno"),COLUMN_3("cafdate"),
		COLUMN_4("cpeplace"),COLUMN_5("stbslno"),COLUMN_6("stbmacaddr"),COLUMN_7("nwsubscode");

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

	private String custName;

	private String lName;

	private String stbCafno;

	private String cafDate;

	private String cpePlace;

	private String stbSlno;
	
	private String stbMacaddr;
	
	private String nwsubsCode;
	
	private String district;
	
	private String mandal;
	
	private String village;
	
	private String selectCaf;
	
	private PageObject pageObject;

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

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getStbCafno() {
		return stbCafno;
	}

	public void setStbCafno(String stbCafno) {
		this.stbCafno = stbCafno;
	}

	public String getCafDate() {
		return cafDate;
	}

	public void setCafDate(String cafDate) {
		this.cafDate = cafDate;
	}

	public String getCpePlace() {
		return cpePlace;
	}

	public void setCpePlace(String cpePlace) {
		this.cpePlace = cpePlace;
	}

	public String getStbSlno() {
		return stbSlno;
	}

	public void setStbSlno(String stbSlno) {
		this.stbSlno = stbSlno;
	}

	public String getStbMacaddr() {
		return stbMacaddr;
	}

	public void setStbMacaddr(String stbMacaddr) {
		this.stbMacaddr = stbMacaddr;
	}

	public String getNwsubsCode() {
		return nwsubsCode;
	}

	public void setNwsubsCode(String nwsubsCode) {
		this.nwsubsCode = nwsubsCode;
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

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getSelectCaf() {
		return selectCaf;
	}

	public void setSelectCaf(String selectCaf) {
		this.selectCaf = selectCaf;
	}

	public PageObject getPageObject() {
		return pageObject;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}
	
}
