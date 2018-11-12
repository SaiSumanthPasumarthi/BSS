/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

/**
 * @author Lakshman
 *
 */
public class OLTPortDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String popOltSerialno;
	
	private int cardId;
	
	private Integer portNo;
	
	private String lmocode;
	
	private String slots;
	
	private String l1Slots;
	
	private String l3Slotsavlbl;
	
	private String l3SlotsUsed;
	
	private String createdBy;
	
	private String modifiedBy;
	
	private String modifiedOn;
	
	private String createdOn;
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	

	


	public String getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getPopOltSerialno() {
		return popOltSerialno;
	}

	public void setPopOltSerialno(String popOltSerialno) {
		this.popOltSerialno = popOltSerialno;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public Integer getPortNo() {
		return portNo;
	}

	public void setPortNo(Integer portNo) {
		this.portNo = portNo;
	}

	public String getLmocode() {
		return lmocode;
	}

	public void setLmocode(String lmocode) {
		this.lmocode = lmocode;
	}

	public String getSlots() {
		return slots;
	}

	public void setSlots(String slots) {
		this.slots = slots;
	}

	public String getL1Slots() {
		return l1Slots;
	}

	public void setL1Slots(String l1Slots) {
		this.l1Slots = l1Slots;
	}

	public String getL3Slotsavlbl() {
		return l3Slotsavlbl;
	}

	public void setL3Slotsavlbl(String l3Slotsavlbl) {
		this.l3Slotsavlbl = l3Slotsavlbl;
	}

	public String getL3SlotsUsed() {
		return l3SlotsUsed;
	}

	public void setL3SlotsUsed(String l3SlotsUsed) {
		this.l3SlotsUsed = l3SlotsUsed;
	}
	
}
