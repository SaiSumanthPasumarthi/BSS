/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lakshman
 *
 */
public class ProductsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tenantcode;
	
	private String mspCode;
	
	private String tenantname;
	
	private Float prodcharge;
	
	private String prodname;
	
	private String prodcode;
	
	private String prodType;
	
	private String createdby;
	
	private String effectivefrom;
	
	private Float taxamt;
	
	private Float totalCharge;
	
	private Integer lockInPeriod;
	
	private Long agruniqueid;
	
	private String rstmplcode;
	
	private Integer oneTimeProdDuration;
	
	private String coreSrvcCodesWithComma;
	
	private String customerType;
	
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	
	public String getCoreSrvcCodesWithComma() {
		return coreSrvcCodesWithComma;
	}

	public void setCoreSrvcCodesWithComma(String coreSrvcCodesWithComma) {
		this.coreSrvcCodesWithComma = coreSrvcCodesWithComma;
	}
	
	public String getMspCode() {
		return mspCode;
	}

	public void setMspCode(String mspCode) {
		this.mspCode = mspCode;
	}

	public Long getAgruniqueid() {
		return agruniqueid;
	}

	public void setAgruniqueid(Long agruniqueid) {
		this.agruniqueid = agruniqueid;
	}

	public String getRstmplcode() {
		return rstmplcode;
	}

	public void setRstmplcode(String rstmplcode) {
		this.rstmplcode = rstmplcode;
	}

	public Integer getOneTimeProdDuration() {
		return oneTimeProdDuration;
	}

	public void setOneTimeProdDuration(Integer oneTimeProdDuration) {
		this.oneTimeProdDuration = oneTimeProdDuration;
	}

	public Integer getLockInPeriod() {
		return lockInPeriod;
	}

	public void setLockInPeriod(Integer lockInPeriod) {
		this.lockInPeriod = lockInPeriod;
	}

	private List<AddtionalServicesVO> servicesList;
	
	private List<chargeTypesVO> chargeTypeList;
	
	public List<chargeTypesVO> getChargeTypeList() {
		return chargeTypeList;
	}

	public void setChargeTypeList(List<chargeTypesVO> chargeTypeList) {
		this.chargeTypeList = chargeTypeList;
	}

	public String getTenantcode() {
		return tenantcode;
	}

	public void setTenantcode(String tenantcode) {
		this.tenantcode = tenantcode;
	}

	public String getTenantname() {
		return tenantname;
	}

	public void setTenantname(String tenantname) {
		this.tenantname = tenantname;
	}

	public Float getProdcharge() {
		return prodcharge;
	}

	public void setProdcharge(Float prodcharge) {
		this.prodcharge = prodcharge;
	}

	public String getProdname() {
		return prodname;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public String getProdcode() {
		return prodcode;
	}

	public void setProdcode(String prodcode) {
		this.prodcode = prodcode;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getEffectivefrom() {
		return effectivefrom;
	}

	public void setEffectivefrom(String effectivefrom) {
		this.effectivefrom = effectivefrom;
	}

	public Float getTaxamt() {
		return taxamt;
	}

	public void setTaxamt(Float taxamt) {
		this.taxamt = taxamt;
	}

	public Float getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(Float totalCharge) {
		this.totalCharge = totalCharge;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<AddtionalServicesVO> getServicesList() {
		return servicesList;
	}

	public void setServicesList(List<AddtionalServicesVO> servicesList) {
		this.servicesList = servicesList;
	}
	
	
}
