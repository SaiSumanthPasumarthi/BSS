/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.util.List;

import com.arbiva.apsfl.tms.dto.Lovs;

/**
 * @author Lakshman
 *
 */
public class FeatureParamsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String paramLabel;
	
	private String paramValueType;
	
	private String paramLovName;
	
	private String maxParamValues;
	
	private String featureCode;
	
	private List<Lovs>  featureList;
	
	private String prmCode;
	
	public String getPrmCode() {
		return prmCode;
	}

	public void setPrmCode(String prmCode) {
		this.prmCode = prmCode;
	}
	
	public List<Lovs> getFeatureList() {
		return featureList;
	}

	public void setFeatureList(List<Lovs> featureList) {
		this.featureList = featureList;
	}
	public String getFeatureCode() {
		return featureCode;
	}

	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}

	public String getParamLabel() {
		return paramLabel;
	}

	public void setParamLabel(String paramLabel) {
		this.paramLabel = paramLabel;
	}

	public String getParamValueType() {
		return paramValueType;
	}

	public void setParamValueType(String paramValueType) {
		this.paramValueType = paramValueType;
	}

	public String getParamLovName() {
		return paramLovName;
	}

	public void setParamLovName(String paramLovName) {
		this.paramLovName = paramLovName;
	}

	public String getMaxParamValues() {
		return maxParamValues;
	}

	public void setMaxParamValues(String maxParamValues) {
		this.maxParamValues = maxParamValues;
	}
	
}
