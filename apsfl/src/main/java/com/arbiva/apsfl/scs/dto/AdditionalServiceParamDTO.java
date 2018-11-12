package com.arbiva.apsfl.scs.dto;

import java.util.Map;

public class AdditionalServiceParamDTO {

	private String paramCode;
	private String paramType;
	private String paramName;
	private String paramDefaultLabel ;
	private String paramDataTypeLov ;
	private String paramValueType;
	private String paramValue;
	private String paramLovName;
	private String paramUmoLov;
	private Map<String,String> paramLovValues;
	private String paramActLabel;
	
	
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamDefaultLabel() {
		return paramDefaultLabel;
	}
	public void setParamDefaultLabel(String paramDefaultLabel) {
		this.paramDefaultLabel = paramDefaultLabel;
	}
	public String getParamDataTypeLov() {
		return paramDataTypeLov;
	}
	public void setParamDataTypeLov(String paramDataTypeLov) {
		this.paramDataTypeLov = paramDataTypeLov;
	}
	public String getParamValueType() {
		return paramValueType;
	}
	public void setParamValueType(String paramValueType) {
		this.paramValueType = paramValueType;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getParamLovName() {
		return paramLovName;
	}
	public void setParamLovName(String paramLovName) {
		this.paramLovName = paramLovName;
	}
	public String getParamUmoLov() {
		return paramUmoLov;
	}
	public void setParamUmoLov(String paramUmoLov) {
		this.paramUmoLov = paramUmoLov;
	}

	public Map<String, String> getParamLovValues() {
		return paramLovValues;
	}

	public void setParamLovValues(Map<String, String> paramLovValues) {
		this.paramLovValues = paramLovValues;
	}

	public String getParamActLabel() {
		return paramActLabel;
	}

	public void setParamActLabel(String paramActLabel) {
		this.paramActLabel = paramActLabel;
	}
	
	
	
}
