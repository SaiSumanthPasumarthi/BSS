/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class MultiAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum MultiActionColumn {

		COLUMN_0("custname"),
		COLUMN_1("cafType"),
		COLUMN_2("apsfluniqueid"),
		COLUMN_3("cpeplace"),
		COLUMN_4("lmocode"),
		COLUMN_5("cafno"),
		COLUMN_6("aadharno"),
		COLUMN_7("popname"),
		COLUMN_8("cafdate"),
		COLUMN_9("products"),
		COLUMN_10("statusdesc");

		private String colName;

		private MultiActionColumn(String colName) {
			this.colName = colName;
		}

		public String getColName() {
			return colName;
		}

		public static String getColumns(String colName) {
			for (MultiActionColumn usersDataSearchParams : MultiActionColumn.values()) {
				if (usersDataSearchParams.toString().equals(colName)) {
					return usersDataSearchParams.getColName();
				}
			}
			return "";
		}
	}
	
	private String effectiveFrom;
	
	private String effectiveTo;
	
	private Long cafNo;
	
	private String aadharNo;
	
	private String status;
	
	private String tenantCode;
	
	private String loginId;
	
	private String tenantType;
	
	private String apsflTrackId;
	
	private String lmoCode;
	
	private String district;
	
	private String mandal;
	
	private String popName;
	
	private String organizationName;
	
	private String cafType;
	
	private String cafSubType;
	
	private String msoCode;
	
    private String usageYYYY;
	
	public String getUsageYYYY() {
		return usageYYYY;
	}

	public void setUsageYYYY(String usageYYYY) {
		this.usageYYYY = usageYYYY;
	}

	public String getUsageMM() {
		return usageMM;
	}

	public void setUsageMM(String usageMM) {
		this.usageMM = usageMM;
	}

	private String usageMM;

	
	public String getMsoCode() {
		return msoCode;
	}

	public void setMsoCode(String msoCode) {
		this.msoCode = msoCode;
	}

	public String getLmoCode() {
		return lmoCode;
	}

	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getCafType() {
		return cafType;
	}

	public void setCafType(String cafType) {
		this.cafType = cafType;
	}

	public String getPopName() {
		return popName;
	}

	public void setPopName(String popName) {
		this.popName = popName;
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
	
	public String getApsflTrackId() {
		return apsflTrackId;
	}

	public void setApsflTrackId(String apsflTrackId) {
		this.apsflTrackId = apsflTrackId;
	}
	
	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public Long getCafNo() {
		return cafNo;
	}

	public void setCafNo(Long cafNo) {
		this.cafNo = cafNo;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCafSubType() {
		return cafSubType;
	}

	public void setCafSubType(String cafSubType) {
		this.cafSubType = cafSubType;
	}
	
}
