/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class ChargeVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String chargeName;
	
	private String chargeCode;
	
	private String chargeAmt;
	
	private String glCode;
	
	private String taxPercentage;
	
	private String featureCode;

	private String featureName;
	
	private String chargeTypeFlag;
	
	private String absValue;
	
	private String totalChargeAmtIncludeTax;
	
	public String getFeatureCode() {
		return featureCode;
	}

	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getChargeTypeFlag() {
		return chargeTypeFlag;
	}

	public void setChargeTypeFlag(String chargeTypeFlag) {
		this.chargeTypeFlag = chargeTypeFlag;
	}

	public String getAbsValue() {
		return absValue;
	}

	public void setAbsValue(String absValue) {
		this.absValue = absValue;
	}

	public String getTotalChargeAmtIncludeTax() {
		return totalChargeAmtIncludeTax;
	}

	public void setTotalChargeAmtIncludeTax(String totalChargeAmtIncludeTax) {
		this.totalChargeAmtIncludeTax = totalChargeAmtIncludeTax;
	}
	
	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getChargeAmt() {
		return chargeAmt;
	}

	public void setChargeAmt(String chargeAmt) {
		this.chargeAmt = chargeAmt;
	}

	public String getGlCode() {
		return glCode;
	}

	public void setGlCode(String glCode) {
		this.glCode = glCode;
	}

	public String getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(String taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
