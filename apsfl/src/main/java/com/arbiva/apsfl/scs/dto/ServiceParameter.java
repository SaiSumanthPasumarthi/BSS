package com.arbiva.apsfl.scs.dto;

import java.util.Date;

/**
 * 
 * @author srinivasa
 *
 */
public class ServiceParameter extends Base {

	private static final long serialVersionUID = 1L;

	private String coreServCode;

	private Date effectivefrom;

	private String paramCode;

	private String paramType;

	private String paramName;

	private String paramDefaultLabel;

	private String paramDataTypeLov;

	private String paramValueType;

	private String paramLovName;

	private String paramUmoLov;

	private String paramValue;

	public String getCoreServCode() {
		return coreServCode;
	}

	public void setCoreServCode(String coreServCode) {
		this.coreServCode = coreServCode;
	}

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

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public Date getEffectivefrom() {
		return effectivefrom;
	}

	public void setEffectivefrom(Date effectivefrom) {
		this.effectivefrom = effectivefrom;
	}

}
