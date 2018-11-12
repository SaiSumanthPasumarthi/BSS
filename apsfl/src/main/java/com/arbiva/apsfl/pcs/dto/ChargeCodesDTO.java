package com.arbiva.apsfl.pcs.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Sai Suresh
 *
 */
public class ChargeCodesDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String chargeCode;
	
	private String chargeName;
	
	private Character chargeTypeFlag;
	
	private Character chargeLevelFlag;
	
	private Character taxLevelFlag;
	
	private Character refundFlag;
	

	private String glCode;
	

    private Short status;

    private Calendar createdon;

    private String createdby;

    private String createdipaddr;

    private Calendar modifiedon;

    private String modifiedby;

    private String modifiedipaddr;
	
    private String taxAmt;
    
	private String featureName;
	
	private String featureCode;
	
	

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	
	public String getFeatureCode() {
		return featureCode;
	}

	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}

	public Character getRefundFlag() {
		return refundFlag;
	}

	public void setRefundFlag(Character refundFlag) {
		this.refundFlag = refundFlag;
	}

	public String getGlCode() {
		return glCode;
	}

	public void setGlCode(String glCode) {
		this.glCode = glCode;
	}

	public String getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(String taxAmt) {
		this.taxAmt = taxAmt;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public Character getChargeTypeFlag() {
		return chargeTypeFlag;
	}

	public void setChargeTypeFlag(Character chargeTypeFlag) {
		this.chargeTypeFlag = chargeTypeFlag;
	}

	public Character getChargeLevelFlag() {
		return chargeLevelFlag;
	}

	public void setChargeLevelFlag(Character chargeLevelFlag) {
		this.chargeLevelFlag = chargeLevelFlag;
	}

	public Character getTaxLevelFlag() {
		return taxLevelFlag;
	}

	public void setTaxLevelFlag(Character taxLevelFlag) {
		this.taxLevelFlag = taxLevelFlag;
	}


	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Calendar getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Calendar createdon) {
		this.createdon = createdon;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getCreatedipaddr() {
		return createdipaddr;
	}

	public void setCreatedipaddr(String createdipaddr) {
		this.createdipaddr = createdipaddr;
	}

	public Calendar getModifiedon() {
		return modifiedon;
	}

	public void setModifiedon(Calendar modifiedon) {
		this.modifiedon = modifiedon;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public String getModifiedipaddr() {
		return modifiedipaddr;
	}

	public void setModifiedipaddr(String modifiedipaddr) {
		this.modifiedipaddr = modifiedipaddr;
	}
	
	
}
	
