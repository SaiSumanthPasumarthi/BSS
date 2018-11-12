package com.arbiva.apsfl.tms.dto;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Sai Suresh
 *
 */
public class ProductInfoVo {

	@NotNull(message = "Product Code May Not Be Null")
	private String productCode;

	@NotNull(message = "Product Name May Not Be Null")
	private String productName;

	private String validFrom;

	private String effectiveFrom;

	private String tenantcode;

	private String effectiveTo;

	private String validTo;
	
	private String prodType;
	
	private String custType;
	
	//private String chargeType;

	private Float productCharge;

	private Float taxAmount;
	
	//private String[] taxIds;
	
	private Integer prodValidity;
	
	//private String glCodeIds;

	@Pattern(regexp = "^[0-9]*$", message = "Lock In Days Must Be Number")
	private String lockInDays;

	//@NotEmpty(message = "At Least One Service Need To Add For The Product")
	@Valid
	private List<ServicesVo> servicesList;
	
	


	/**
	 * @param productInfoVo
	 * @param servicesVoAddList
	 * @throws ParseException
	 */




	public Float getProductCharge() {
		return productCharge;
	}

	public void setProductCharge(Float productCharge) {
		this.productCharge = productCharge;
	}

	public Float getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Float taxAmount) {
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

	public List<ServicesVo> getServicesList() {
		return servicesList;
	}

	public void setServicesList(List<ServicesVo> servicesList) {
		this.servicesList = servicesList;
	}
	
}
