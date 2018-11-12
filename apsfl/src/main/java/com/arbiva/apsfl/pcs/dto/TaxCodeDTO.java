package com.arbiva.apsfl.pcs.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author srinivasa
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TaxCodeDTO {

	
	private String taxCode;

	private String taxName;

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

}
