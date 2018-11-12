package com.arbiva.apsfl.scs.dto;

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
public class AdditionalServiceParametersDTO {
	

	private String code;

	private String prmactlbl;
	
	private String prmvalue;
	
	private String productminval;
	
	private String productmaxval;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPrmactlbl() {
		return prmactlbl;
	}

	public void setPrmactlbl(String prmactlbl) {
		this.prmactlbl = prmactlbl;
	}

	public String getPrmvalue() {
		return prmvalue;
	}

	public void setPrmvalue(String prmvalue) {
		this.prmvalue = prmvalue;
	}

	public String getProductminval() {
		return productminval;
	}

	public void setProductminval(String productminval) {
		this.productminval = productminval;
	}

	public String getProductmaxval() {
		return productmaxval;
	}

	public void setProductmaxval(String productmaxval) {
		this.productmaxval = productmaxval;
	}
	
}
