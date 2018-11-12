package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;

public class ServiceDetailsDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String componentCode;
	
	private String srvcName;
	
	private String prodName;
	
	private String charTypeName;
	
	private Float chargeAmt;
	
	private String glName;
	
	private String glCode;
	

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}


	public String getComponentCode() {
		return componentCode;
	}

	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}

	public String getCharTypeName() {
		return charTypeName;
	}

	public void setCharTypeName(String charTypeName) {
		this.charTypeName = charTypeName;
	}

	public String getSrvcName() {
		return srvcName;
	}

	public void setSrvcName(String srvcName) {
		this.srvcName = srvcName;
	}

	public Float getChargeAmt() {
		return chargeAmt;
	}

	public void setChargeAmt(Float chargeAmt) {
		this.chargeAmt = chargeAmt;
	}

	public String getGlName() {
		return glName;
	}

	public void setGlName(String glName) {
		this.glName = glName;
	}

	public String getGlCode() {
		return glCode;
	}

	public void setGlCode(String glCode) {
		this.glCode = glCode;
	}
	

}
