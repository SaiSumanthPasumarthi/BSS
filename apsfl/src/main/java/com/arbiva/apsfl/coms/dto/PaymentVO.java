/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class PaymentVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long cafNo;
	
	private String loginId;
	
	private Long custId;
	
	private String payment;
	
	private Float totalCharge;
	
	private String aadharNumber;
	
	private String customerName;
	
	private String paymentMode;
	
	private Float paidAmount;
	
	private String feasibility;
	
	private String ipAddress;
	
	private String custType;
	
	private String tenantCode;
	
	private String ddDate;
	
	private String ddNo;
	
	private String bankName;
	
	private String branchName;
	
	private String oltSrlNo;
	
	private String oltPortNo;
	
	private String district;
	
	private String village;
	
	private String mandal;
	
	private String cpeSrlNo;
	
	private String stbSrlNo;
	
	private CustomerCafVO customerCafVO;
	
	private String tenantType;
	
	private String transactionNo;
	
	private String source;
	
	private String cpeReleasedCharge;
	
	private String mobileNo;
	
	private String acctCafNo;
	
	private String pmntCustId;
	
	private String pmntDate;
	
	private String mName;
	
	private String lName;
	
	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAcctCafNo() {
		return acctCafNo;
	}

	public void setAcctCafNo(String acctCafNo) {
		this.acctCafNo = acctCafNo;
	}

	public String getPmntCustId() {
		return pmntCustId;
	}

	public void setPmntCustId(String pmntCustId) {
		this.pmntCustId = pmntCustId;
	}

	public String getPmntDate() {
		return pmntDate;
	}

	public void setPmntDate(String pmntDate) {
		this.pmntDate = pmntDate;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getCpeReleasedCharge() {
		return cpeReleasedCharge;
	}

	public void setCpeReleasedCharge(String cpeReleasedCharge) {
		this.cpeReleasedCharge = cpeReleasedCharge;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	
	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}

	private String pmntId;
	
	public String getPmntId() {
		return pmntId;
	}

	public void setPmntId(String pmntId) {
		this.pmntId = pmntId;
	}
	
	public CustomerCafVO getCustomerCafVO() {
		return customerCafVO;
	}

	public void setCustomerCafVO(CustomerCafVO customerCafVO) {
		this.customerCafVO = customerCafVO;
	}
	
	public String getCpeSrlNo() {
		return cpeSrlNo;
	}

	public void setCpeSrlNo(String cpeSrlNo) {
		this.cpeSrlNo = cpeSrlNo;
	}

	public String getStbSrlNo() {
		return stbSrlNo;
	}

	public void setStbSrlNo(String stbSrlNo) {
		this.stbSrlNo = stbSrlNo;
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
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getOltSrlNo() {
		return oltSrlNo;
	}

	public void setOltSrlNo(String oltSrlNo) {
		this.oltSrlNo = oltSrlNo;
	}

	public String getOltPortNo() {
		return oltPortNo;
	}

	public void setOltPortNo(String oltPortNo) {
		this.oltPortNo = oltPortNo;
	}
	
	public String getDdDate() {
		return ddDate;
	}

	public void setDdDate(String ddDate) {
		this.ddDate = ddDate;
	}

	public String getDdNo() {
		return ddNo;
	}

	public void setDdNo(String ddNo) {
		this.ddNo = ddNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	private String lmoCode;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLmoCode() {
		return lmoCode;
	}

	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}

	public String getFeasibility() {
		return feasibility;
	}

	public void setFeasibility(String feasibility) {
		this.feasibility = feasibility;
	}

	public Long getCafNo() {
		return cafNo;
	}

	public void setCafNo(Long cafNo) {
		this.cafNo = cafNo;
	}

	public Float getTotalCharge() {
		return totalCharge;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public void setTotalCharge(Float totalCharge) {
		this.totalCharge = totalCharge;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Float getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Float paidAmount) {
		this.paidAmount = paidAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
