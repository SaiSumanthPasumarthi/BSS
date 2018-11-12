package com.arbiva.apsfl.scs.dto;

import java.io.Serializable;
import java.util.TreeMap;

public class TTIssueCodeAttributesDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String attrName;
	private String attrValue;
	private String uiType;
	private String mandatoryFlag;
	private TreeMap<String,String> lovMap=new TreeMap<String,String>();
	
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	public String getUiType() {
		return uiType;
	}
	public void setUiType(String uiType) {
		this.uiType = uiType;
	}
	public TreeMap<String, String> getLovMap() {
		return lovMap;
	}
	public void setLovMap(TreeMap<String, String> lovMap) {
		this.lovMap = lovMap;
	}
	public String getMandatoryFlag() {
		return mandatoryFlag;
	}
	public void setMandatoryFlag(String mandatoryFlag) {
		this.mandatoryFlag = mandatoryFlag;
	}
}
