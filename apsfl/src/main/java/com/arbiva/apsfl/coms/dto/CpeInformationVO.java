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
public class CpeInformationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cpePrice;
	
	private String cpeRentalPrice;
	
	private String cpeInstChrg;
	
	private String cpeExtCableChrg;
	
	private String cpeTaxAmt;
	
	private String cpeModel;
	
	private String totalCharge;
	
	private List<CafAndCpeChargesVO> cafAndCpeChargesList;
	
	private String district;
	
	private String VATTax;
	
	private String village;
	
	private String mandal;
	
	private List<STBChargesVO> stbChargesList;
	
	private String cpeMacAddress;
	
	private String cpeSrlNo;
	
	private String cpeCharge;
	
	public String getCpeCharge() {
		return cpeCharge;
	}

	public void setCpeCharge(String cpeCharge) {
		this.cpeCharge = cpeCharge;
	}
	
	public String getCpeSrlNo() {
		return cpeSrlNo;
	}

	public void setCpeSrlNo(String cpeSrlNo) {
		this.cpeSrlNo = cpeSrlNo;
	}
	
	public String getCpeMacAddress() {
		return cpeMacAddress;
	}

	public void setCpeMacAddress(String cpeMacAddress) {
		this.cpeMacAddress = cpeMacAddress;
	}
	
	public List<STBChargesVO> getStbChargesList() {
		return stbChargesList;
	}

	public void setStbChargesList(List<STBChargesVO> stbChargesList) {
		this.stbChargesList = stbChargesList;
	}

	public String getCpePrice() {
		return cpePrice;
	}

	public void setCpePrice(String cpePrice) {
		this.cpePrice = cpePrice;
	}

	public String getCpeRentalPrice() {
		return cpeRentalPrice;
	}

	public void setCpeRentalPrice(String cpeRentalPrice) {
		this.cpeRentalPrice = cpeRentalPrice;
	}

	public String getCpeInstChrg() {
		return cpeInstChrg;
	}

	public void setCpeInstChrg(String cpeInstChrg) {
		this.cpeInstChrg = cpeInstChrg;
	}

	public String getCpeExtCableChrg() {
		return cpeExtCableChrg;
	}

	public void setCpeExtCableChrg(String cpeExtCableChrg) {
		this.cpeExtCableChrg = cpeExtCableChrg;
	}

	public String getCpeTaxAmt() {
		return cpeTaxAmt;
	}

	public void setCpeTaxAmt(String cpeTaxAmt) {
		this.cpeTaxAmt = cpeTaxAmt;
	}

	public String getCpeModel() {
		return cpeModel;
	}

	public void setCpeModel(String cpeModel) {
		this.cpeModel = cpeModel;
	}

	public String getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(String totalCharge) {
		this.totalCharge = totalCharge;
	}

	public List<CafAndCpeChargesVO> getCafAndCpeChargesList() {
		return cafAndCpeChargesList;
	}

	public void setCafAndCpeChargesList(List<CafAndCpeChargesVO> cafAndCpeChargesList) {
		this.cafAndCpeChargesList = cafAndCpeChargesList;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getVATTax() {
		return VATTax;
	}

	public void setVATTax(String vATTax) {
		VATTax = vATTax;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
}
