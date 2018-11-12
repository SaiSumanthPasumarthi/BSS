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
public class AddtionalServicesVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String serviceName;
	
	private String serviceCode;
	
	private String serviceCharge;
	
	private Integer lockInPeriod;
	
	private String coreServiceCode;
	
	private String featureCodes;
	
	private List<ChargeVO> chargeList;

	public List<ChargeVO> getChargeList() {
		return chargeList;
	}

	public void setChargeList(List<ChargeVO> chargeList) {
		this.chargeList = chargeList;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public Integer getLockInPeriod() {
		return lockInPeriod;
	}

	public void setLockInPeriod(Integer lockInPeriod) {
		this.lockInPeriod = lockInPeriod;
	}

	public String getCoreServiceCode() {
		return coreServiceCode;
	}

	public void setCoreServiceCode(String coreServiceCode) {
		this.coreServiceCode = coreServiceCode;
	}

	public String getFeatureCodes() {
		return featureCodes;
	}

	public void setFeatureCodes(String featureCodes) {
		this.featureCodes = featureCodes;
	}
	
}
