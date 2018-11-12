/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lakshman
 *
 */
public class FilterPackagesVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ProductsVO> selectedPackagesList;
	
	private List<CafAndCpeChargesVO> cafAndCpeChargesList;
	
	private String totalAmount;

	private String totalTax;
	
	public List<ProductsVO> getSelectedPackagesList() {
		return selectedPackagesList;
	}

	public void setSelectedPackagesList(List<ProductsVO> selectedPackagesList) {
		this.selectedPackagesList = selectedPackagesList;
	}

	public List<CafAndCpeChargesVO> getCafAndCpeChargesList() {
		return cafAndCpeChargesList;
	}

	public void setCafAndCpeChargesList(List<CafAndCpeChargesVO> cafAndCpeChargesList) {
		this.cafAndCpeChargesList = cafAndCpeChargesList;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}
	
}
