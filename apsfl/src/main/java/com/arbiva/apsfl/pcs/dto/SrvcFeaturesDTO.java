package com.arbiva.apsfl.pcs.dto;

public class SrvcFeaturesDTO {
	
	private String featureCode;
	private String featureName;
	private String[] chargeCode;
	
	

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
	public String[] getChargeCode() {
		return chargeCode;
	}
	public void setChargeCode(String[] chargeCode) {
		this.chargeCode = chargeCode;
	}

}
