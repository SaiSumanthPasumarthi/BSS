/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class chargeTypesVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String chargeType;
	
	private String chargeAmt;
	
	private String taxAmt;

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getChargeAmt() {
		return chargeAmt;
	}

	public void setChargeAmt(String chargeAmt) {
		this.chargeAmt = chargeAmt;
	}

	public String getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(String taxAmt) {
		this.taxAmt = taxAmt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
