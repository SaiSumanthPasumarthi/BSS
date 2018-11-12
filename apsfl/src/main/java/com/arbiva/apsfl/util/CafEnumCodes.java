package com.arbiva.apsfl.util;

public enum CafEnumCodes {

	CAF_ACTIVATION_STATUS(6),
	CAF_SUSPENDED_STATUS(7),
	CAF_DEACTIVATION_STATUS(8),
	CAF_BLACKLISTED_STATUS(99),
	CAF_BULK_UPLOAD_STATUS(88),
	CAF_BULK_UPLOAD_PENDING_STATUS(0),
	CAF_PENDING_STATUS(89),
	
	CUST_TYPE_CODE("INDIVIDUAL"),
	ENTCUST_TYPE_CODE("ENTERPRISE"),
	GOVT_TYPE_CODE("GOVT"),
	PRIVATE_TYPE_CODE("PRIVATE"),
	SI_Tenant_Type("SI");

	private  String code;
	private  int status;

	private CafEnumCodes(String code) {
		this.code = code;
	}

	private CafEnumCodes(int status) {
		this.status = status;
	}
	
	public String getCode() {
		return code ;
	}
	public int getStatus() {
		return status ;
	}
	
}
