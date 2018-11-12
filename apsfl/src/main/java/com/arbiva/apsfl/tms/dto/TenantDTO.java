package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;

import com.arbiva.apsfl.tms.model.Tenant;

public class TenantDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public TenantDTO() {
	}

	public TenantDTO(Tenant tenant) {
		this.tenantCode = tenant.getTenantCode();
		this.enrollmentNo = tenant.getPortalEnrllmentno();
		this.tenantName = tenant.getName();
		this.status = tenant.getStatus();
		this.tenantTypeLov = tenant.getTenantTypeLov();
		this.mobile = tenant.getPocMobileNo1();
		this.createdBy = tenant.getCreatedBy();
		this.tenantId = tenant.getTenantId();
	}
	
	public enum SortColumnName {
		COLUMN_0("tenantcode"),
		COLUMN_1("portal_enrollmentno"),
		COLUMN_2("tenantname"),
		COLUMN_3("tenantTypeLov"),
		COLUMN_4("regoff_pocmob1"),
		COLUMN_5("status");

		private String colName;

		private SortColumnName(String colName) {
			this.colName = colName;
		}
		public String getColName() {
			return colName;
		}

		public static String getColumns(String colName) {
			for (SortColumnName usersDataSearchParams : SortColumnName.values()) {
				if (usersDataSearchParams.toString().equals(colName)) {
					return usersDataSearchParams.getColName();
				}
			}
			return "";
		}
	}

	private String tenantCode;

	private int status;

	private String tenantName;

	private String tenantTypeLov;
	private String mobile;
	private Integer tenantId;
	private String createdBy;
	private String enrollmentNo;
	private String tenantType;

	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}
	

	public String getEnrollmentNo() {
		return enrollmentNo;
	}

	public void setEnrollmentNo(String enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
	}

	public String getTenantTypeLov() {
		return tenantTypeLov;
	}

	public void setTenantTypeLov(String tenantTypeLov) {
		this.tenantTypeLov = tenantTypeLov;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	private String name;

	private String region;

	// private MultipartFile licenceId;
	//
	// private MultipartFile idProof;
	//
	// private MultipartFile addressProof;

	private String effectiveFrom;

	private String effectiveTO;

	private String effectiveFrom1;

	private String effectiveTO1;

	private String effectiveFrom2;

	private String effectiveTO2;

	private String docUniqueId;

	private String docUniqueId1;

	private String docUniqueId2;

	private String licenserefno;

	private String licenseexpDate;

	private String accountNo;

	private String ifscCode;

	private String acctTypelov;

	private String bankNamelov;

	private String doclov;

	private String doclov1;

	private String doclov2;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getEffectiveTO() {
		return effectiveTO;
	}

	public void setEffectiveTO(String effectiveTO) {
		this.effectiveTO = effectiveTO;
	}

	public String getEffectiveFrom1() {
		return effectiveFrom1;
	}

	public void setEffectiveFrom1(String effectiveFrom1) {
		this.effectiveFrom1 = effectiveFrom1;
	}

	public String getEffectiveTO1() {
		return effectiveTO1;
	}

	public void setEffectiveTO1(String effectiveTO1) {
		this.effectiveTO1 = effectiveTO1;
	}

	public String getEffectiveFrom2() {
		return effectiveFrom2;
	}

	public void setEffectiveFrom2(String effectiveFrom2) {
		this.effectiveFrom2 = effectiveFrom2;
	}

	public String getEffectiveTO2() {
		return effectiveTO2;
	}

	public void setEffectiveTO2(String effectiveTO2) {
		this.effectiveTO2 = effectiveTO2;
	}

	public String getDocUniqueId() {
		return docUniqueId;
	}

	public void setDocUniqueId(String docUniqueId) {
		this.docUniqueId = docUniqueId;
	}

	public String getDocUniqueId1() {
		return docUniqueId1;
	}

	public void setDocUniqueId1(String docUniqueId1) {
		this.docUniqueId1 = docUniqueId1;
	}

	public String getDocUniqueId2() {
		return docUniqueId2;
	}

	public void setDocUniqueId2(String docUniqueId2) {
		this.docUniqueId2 = docUniqueId2;
	}

	public String getLicenserefno() {
		return licenserefno;
	}

	public void setLicenserefno(String licenserefno) {
		this.licenserefno = licenserefno;
	}

	public String getLicenseexpDate() {
		return licenseexpDate;
	}

	public void setLicenseexpDate(String licenseexpDate) {
		this.licenseexpDate = licenseexpDate;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getAcctTypelov() {
		return acctTypelov;
	}

	public void setAcctTypelov(String acctTypelov) {
		this.acctTypelov = acctTypelov;
	}

	public String getBankNamelov() {
		return bankNamelov;
	}

	public void setBankNamelov(String bankNamelov) {
		this.bankNamelov = bankNamelov;
	}

	public String getDoclov() {
		return doclov;
	}

	public void setDoclov(String doclov) {
		this.doclov = doclov;
	}

	public String getDoclov1() {
		return doclov1;
	}

	public void setDoclov1(String doclov1) {
		this.doclov1 = doclov1;
	}

	public String getDoclov2() {
		return doclov2;
	}

	public void setDoclov2(String doclov2) {
		this.doclov2 = doclov2;
	}

}
