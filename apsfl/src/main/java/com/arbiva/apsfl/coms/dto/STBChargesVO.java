/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class STBChargesVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String stbModel;
	
	private String stbMacAddress;
	
	private String stbTax;
	
	private String stbPrice;
	
	private String stbInstallmentPrice;
	
	private String stbSrlNo;
	
	private String stbPackages;

	public String getStbPackages() {
		return stbPackages;
	}

	public void setStbPackages(String stbPackages) {
		this.stbPackages = stbPackages;
	}


	public String getStbModel() {
		return stbModel;
	}

	public void setStbModel(String stbModel) {
		this.stbModel = stbModel;
	}

	public String getStbMacAddress() {
		return stbMacAddress;
	}

	public void setStbMacAddress(String stbMacAddress) {
		this.stbMacAddress = stbMacAddress;
	}

	public String getStbTax() {
		return stbTax;
	}

	public void setStbTax(String stbTax) {
		this.stbTax = stbTax;
	}

	public String getStbPrice() {
		return stbPrice;
	}

	public void setStbPrice(String stbPrice) {
		this.stbPrice = stbPrice;
	}

	public String getStbInstallmentPrice() {
		return stbInstallmentPrice;
	}

	public void setStbInstallmentPrice(String stbInstallmentPrice) {
		this.stbInstallmentPrice = stbInstallmentPrice;
	}

	public String getStbSrlNo() {
		return stbSrlNo;
	}

	public void setStbSrlNo(String stbSrlNo) {
		this.stbSrlNo = stbSrlNo;
	}
	
}
