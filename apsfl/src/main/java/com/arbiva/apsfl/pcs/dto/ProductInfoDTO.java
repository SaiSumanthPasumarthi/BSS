package com.arbiva.apsfl.pcs.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Sai Suresh
 *
 */
public class ProductInfoDTO {

	private String productCode;

	private String productName;
	
	private String loginId;

	private String validFrom;

	private String effectiveFrom;

	private String tenantcode;

	private String effectiveTo;

	private String validTo;
	
	private String prodType;
	
	private String custType;

	private BigDecimal productCharge;

	private BigDecimal taxAmount;
	
	private Integer prodValidity;

	private String lockInDays;

	private List<ServicesDTO> servicesList;
	
	private String monthlyCharge;
	
	private String monthlyTax;
	
	private String activationCharge;
	
	private String depositsCharge;
	
	private String resumeCharge;
	
	private String disconnectionCharge;
	
	private String installationCharge;
	
	private String chargesEffectiveFrom;
	

		

	public String getChargesEffectiveFrom() {
		return chargesEffectiveFrom;
	}

	public void setChargesEffectiveFrom(String chargesEffectiveFrom) {
		this.chargesEffectiveFrom = chargesEffectiveFrom;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getMonthlyTax() {
		return monthlyTax;
	}

	public void setMonthlyTax(String monthlyTax) {
		this.monthlyTax = monthlyTax;
	}

	public String getMonthlyCharge() {
		return monthlyCharge;
	}

	public void setMonthlyCharge(String monthlyCharge) {
		this.monthlyCharge = monthlyCharge;
	}

	public String getActivationCharge() {
		return activationCharge;
	}

	public void setActivationCharge(String activationCharge) {
		this.activationCharge = activationCharge;
	}

	

	public String getDepositsCharge() {
		return depositsCharge;
	}

	public void setDepositsCharge(String depositsCharge) {
		this.depositsCharge = depositsCharge;
	}

	public String getResumeCharge() {
		return resumeCharge;
	}

	public void setResumeCharge(String resumeCharge) {
		this.resumeCharge = resumeCharge;
	}

	public String getDisconnectionCharge() {
		return disconnectionCharge;
	}

	public void setDisconnectionCharge(String disconnectionCharge) {
		this.disconnectionCharge = disconnectionCharge;
	}

	public String getInstallationCharge() {
		return installationCharge;
	}

	public void setInstallationCharge(String installationCharge) {
		this.installationCharge = installationCharge;
	}

	

	public BigDecimal getProductCharge() {
		return productCharge;
	}

	public void setProductCharge(BigDecimal productCharge) {
		this.productCharge = productCharge;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getTenantcode() {
		return tenantcode;
	}

	public void setTenantcode(String tenantcode) {
		this.tenantcode = tenantcode;
	}

	public String getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public Integer getProdValidity() {
		return prodValidity;
	}

	public void setProdValidity(Integer prodValidity) {
		this.prodValidity = prodValidity;
	}

	public String getLockInDays() {
		return lockInDays;
	}

	public void setLockInDays(String lockInDays) {
		this.lockInDays = lockInDays;
	}

	public List<ServicesDTO> getServicesList() {
		return servicesList;
	}

	public void setServicesList(List<ServicesDTO> servicesList) {
		this.servicesList = servicesList;
	}
	
}
