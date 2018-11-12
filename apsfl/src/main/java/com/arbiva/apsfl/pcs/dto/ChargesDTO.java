package com.arbiva.apsfl.pcs.dto;

public class ChargesDTO {
	
	private String chargeName;
	
	private String chargeCode;
	
	private String chargeAmt;
	
	private String glCode;
	
	private String taxPercentage;
	
	private String featureCode;
	
	private String featureName;
	

	
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


	public String getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(String taxPercentage) {
		this.taxPercentage = taxPercentage;
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

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	
	
}
