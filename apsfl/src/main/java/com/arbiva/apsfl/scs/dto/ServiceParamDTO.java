package com.arbiva.apsfl.scs.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author srinivasa
 *
 */
@JsonAutoDetect(getterVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, isGetterVisibility = Visibility.NONE)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServiceParamDTO {
	

	private String code;

	private String name;

	private String paramType;

	private String dataType;

	private String paramValueType;

	private String label;

	private String value;
	
	private String prmLOVName;
	
	private Map<String,String> prmLOVList;

	
	public Map<String, String> getPrmLOVList() {
		return prmLOVList;
	}

	public void setPrmLOVList(Map<String, String> prmLOVList) {
		this.prmLOVList = prmLOVList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getParamValueType() {
		return paramValueType;
	}

	public void setParamValueType(String paramValueType) {
		this.paramValueType = paramValueType;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPrmLOVName() {
		return prmLOVName;
	}

	public void setPrmLOVName(String prmLOVName) {
		this.prmLOVName = prmLOVName;
	}

}
