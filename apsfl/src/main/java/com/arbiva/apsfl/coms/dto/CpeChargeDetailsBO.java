/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Lakshman
 *
 */
public class CpeChargeDetailsBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long profileId;
	
	private String cpeModel;
	
	private Integer emiCount;
	
	private BigDecimal instcharges;
	
	private BigDecimal emiAmount;
	
	private BigDecimal upFrontCharges;
	
	private String cpemacAddr;

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public String getCpeModel() {
		return cpeModel;
	}

	public void setCpeModel(String cpeModel) {
		this.cpeModel = cpeModel;
	}

	public Integer getEmiCount() {
		return emiCount;
	}

	public void setEmiCount(Integer emiCount) {
		this.emiCount = emiCount;
	}

	public BigDecimal getInstcharges() {
		return instcharges;
	}

	public void setInstcharges(BigDecimal instcharges) {
		this.instcharges = instcharges;
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

	public String getCpemacAddr() {
		return cpemacAddr;
	}

	public void setCpemacAddr(String cpemacAddr) {
		this.cpemacAddr = cpemacAddr;
	}
	
}
