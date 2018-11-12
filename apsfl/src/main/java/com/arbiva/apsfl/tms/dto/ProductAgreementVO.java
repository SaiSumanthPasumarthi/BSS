package com.arbiva.apsfl.tms.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductAgreementVO {

	private String tenantCode;
	private String prodCode;
	private String prodName;
	private String agrFDate;
	private String tmplCode;
	List<ProductAgreementPartnersVO> partnrsList;
	
	private String agreementId;
	private String tenantName;
	private String tenantType;
	private String templateName;
	private String lmoName;

	private String prodtype;
	private String custtypelov;
	private String lockindays;
	private BigDecimal totalCharge;

	private String rstmlCode;
	private String effetiveFrom;
	private String createdBy;
	private String prodCharge;
	private String durationDays;
	private String taxCompenent;
	private String prodType;
	private String flag;
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getLmoName() {
		return lmoName;
	}

	public void setLmoName(String lmoName) {
		this.lmoName = lmoName;
	}
	
	public BigDecimal getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
	}

	public String getTaxCompenent() {
		return taxCompenent;
	}

	public void setTaxCompenent(String taxCompenent) {
		this.taxCompenent = taxCompenent;
	}

	public String getRstmlCode() {
		return rstmlCode;
	}

	public void setRstmlCode(String rstmlCode) {
		this.rstmlCode = rstmlCode;
	}

	public String getEffetiveFrom() {
		return effetiveFrom;
	}

	public void setEffetiveFrom(String effetiveFrom) {
		this.effetiveFrom = effetiveFrom;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getProdCharge() {
		return prodCharge;
	}

	public void setProdCharge(String prodCharge) {
		this.prodCharge = prodCharge;
	}

	public String getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(String durationDays) {
		this.durationDays = durationDays;
	}

	
	public String getProdtype() {
		return prodtype;
	}

	public void setProdtype(String prodtype) {
		this.prodtype = prodtype;
	}

	public String getCusttypelov() {
		return custtypelov;
	}

	public void setCusttypelov(String custtypelov) {
		this.custtypelov = custtypelov;
	}

	public String getLockindays() {
		return lockindays;
	}

	public void setLockindays(String lockindays) {
		this.lockindays = lockindays;
	}

	
	
	

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getAgrFDate() {
		return agrFDate;
	}

	public void setAgrFDate(String agrFDate) {
		this.agrFDate = agrFDate;
	}

	public String getTmplCode() {
		return tmplCode;
	}

	public void setTmplCode(String tmplCode) {
		this.tmplCode = tmplCode;
	}

	public List<ProductAgreementPartnersVO> getPartnrsList() {
		return partnrsList;
	}

	public void setPartnrsList(List<ProductAgreementPartnersVO> partnrsList) {
		this.partnrsList = partnrsList;
	}
}
