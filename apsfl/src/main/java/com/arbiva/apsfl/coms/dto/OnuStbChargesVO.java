/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class OnuStbChargesVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String onuCost;
	
	private String onuInstChrg;
	
	private String onuInstCount;
	
	private String instCharge;
	
	private String extraCableChrg;
	
	private String stbCost;
	
	private String stbInstChrg;
	
	private String stbInstCount;

	public String getOnuCost() {
		return onuCost;
	}

	public void setOnuCost(String onuCost) {
		this.onuCost = onuCost;
	}

	public String getOnuInstChrg() {
		return onuInstChrg;
	}

	public void setOnuInstChrg(String onuInstChrg) {
		this.onuInstChrg = onuInstChrg;
	}

	public String getOnuInstCount() {
		return onuInstCount;
	}

	public void setOnuInstCount(String onuInstCount) {
		this.onuInstCount = onuInstCount;
	}

	public String getInstCharge() {
		return instCharge;
	}

	public void setInstCharge(String instCharge) {
		this.instCharge = instCharge;
	}

	public String getExtraCableChrg() {
		return extraCableChrg;
	}

	public void setExtraCableChrg(String extraCableChrg) {
		this.extraCableChrg = extraCableChrg;
	}

	public String getStbCost() {
		return stbCost;
	}

	public void setStbCost(String stbCost) {
		this.stbCost = stbCost;
	}

	public String getStbInstChrg() {
		return stbInstChrg;
	}

	public void setStbInstChrg(String stbInstChrg) {
		this.stbInstChrg = stbInstChrg;
	}

	public String getStbInstCount() {
		return stbInstCount;
	}

	public void setStbInstCount(String stbInstCount) {
		this.stbInstCount = stbInstCount;
	}
	
}
