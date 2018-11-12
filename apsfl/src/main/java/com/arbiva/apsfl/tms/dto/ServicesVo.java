/**
 * 
 */
package com.arbiva.apsfl.tms.dto;

import javax.validation.constraints.NotNull;


/**
 * @author Sai Suresh
 *
 */
public class ServicesVo {

	private String srvccode;

	
	private String serviceType;

	private String serviceName;

	private String effectiveFrom;

	private String chargeType;

	private String chargeName;

	@NotNull
	private Float chargeAmount;

	private String addOrDelete;

	private String coreServiceCode;



	public String getCoreServiceCode() {
		return coreServiceCode;
	}

	public void setCoreServiceCode(String coreServiceCode) {
		this.coreServiceCode = coreServiceCode;
	}

	public String getAddOrDelete() {
		return addOrDelete;
	}

	public void setAddOrDelete(String addOrDelete) {
		this.addOrDelete = addOrDelete;
	}

	public String getSrvccode() {
		return srvccode;
	}

	public void setSrvccode(String srvccode) {
		this.srvccode = srvccode;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public Float getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(Float chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

}
