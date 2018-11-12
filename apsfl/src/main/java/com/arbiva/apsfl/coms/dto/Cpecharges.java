/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Lakshman
 *
 */
public class Cpecharges implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long profileId;
	
	private Date effectiveFrom;
	
	private Date effectiveTo;
	
	private BigDecimal custCost;
	
	private BigDecimal custRent;
	
	private BigDecimal instcharges;
	
	private BigDecimal purchaseCost;
	
	private Integer emiCount;
	
	private BigDecimal emiAmount;
	
	private BigDecimal upFrontCharges;

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Date getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public BigDecimal getCustCost() {
		return custCost;
	}

	public void setCustCost(BigDecimal custCost) {
		this.custCost = custCost;
	}

	public BigDecimal getCustRent() {
		return custRent;
	}

	public void setCustRent(BigDecimal custRent) {
		this.custRent = custRent;
	}

	public BigDecimal getInstcharges() {
		return instcharges;
	}

	public void setInstcharges(BigDecimal instcharges) {
		this.instcharges = instcharges;
	}

	public BigDecimal getPurchaseCost() {
		return purchaseCost;
	}

	public void setPurchaseCost(BigDecimal purchaseCost) {
		this.purchaseCost = purchaseCost;
	}

	public Integer getEmiCount() {
		return emiCount;
	}

	public void setEmiCount(Integer emiCount) {
		this.emiCount = emiCount;
	}

	public BigDecimal getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(BigDecimal emiAmount) {
		this.emiAmount = emiAmount;
	}

	public BigDecimal getUpFrontCharges() {
		return upFrontCharges;
	}

	public void setUpFrontCharges(BigDecimal upFrontCharges) {
		this.upFrontCharges = upFrontCharges;
	}
	
}
