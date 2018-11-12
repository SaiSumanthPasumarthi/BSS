package com.arbiva.apsfl.pcs.dto;

import java.util.ArrayList;
import java.util.List;

public class CharTaxGlDTO {
	
	private List<ChargeCodesDTO> chargeTypes = new ArrayList<>();
	
	List<TaxCodeDTO> taxCodesList ;
	
	List<GlCodeDTO> glCodes ;
	


	public List<TaxCodeDTO> getTaxCodesList() {
		return taxCodesList;
	}

	public void setTaxCodesList(List<TaxCodeDTO> taxCodesList) {
		this.taxCodesList = taxCodesList;
	}

	public List<GlCodeDTO> getGlCodes() {
		return glCodes;
	}

	public void setGlCodes(List<GlCodeDTO> glCodes) {
		this.glCodes = glCodes;
	}

	public List<ChargeCodesDTO> getChargeTypes() {
		return chargeTypes;
	}

	public void setChargeTypes(List<ChargeCodesDTO> chargeTypes) {
		this.chargeTypes = chargeTypes;
	}
}
