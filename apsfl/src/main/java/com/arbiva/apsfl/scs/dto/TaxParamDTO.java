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
public class TaxParamDTO {
	

	private String taxCode;

	private String taxPerc;

	//private String percentage;

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getTaxPerc() {
		return taxPerc;
	}

	public void setTaxPerc(String taxPerc) {
		this.taxPerc = taxPerc;
	}

	/*public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}*/

}
