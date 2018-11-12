/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class Villages implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer villageUid;
	
	private String villageName;
	
	private Integer stateId;
	
	private Integer districtUid;
	
	private Integer villageSlno;
	
	private String stdCode;
	
	private String pinCode;
	
	//private Integer substnUid;
	
	private String entTaxZone;
	
	private String entTaxCircle;
	
	private String srvcTaxCircle;
	
	private String swachcessCircle;
	
	private String kisancessCircle;
	
	private Integer mandalSlno;
	
	public void setVillageUid(Integer villageUid) {
		this.villageUid = villageUid;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getDistrictUid() {
		return districtUid;
	}

	public void setDistrictUid(Integer districtUid) {
		this.districtUid = districtUid;
	}

	public Integer getVillageSlno() {
		return villageSlno;
	}

	public void setVillageSlno(Integer villageSlno) {
		this.villageSlno = villageSlno;
	}

	public String getStdCode() {
		return stdCode;
	}

	public void setStdCode(String stdCode) {
		this.stdCode = stdCode;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Integer getVillageUid() {
		return villageUid;
	}

	/*public Integer getSubstnUid() {
		return substnUid;
	}

	public void setSubstnUid(Integer substnUid) {
		this.substnUid = substnUid;
	}*/

	public String getEntTaxZone() {
		return entTaxZone;
	}

	public void setEntTaxZone(String entTaxZone) {
		this.entTaxZone = entTaxZone;
	}

	public String getEntTaxCircle() {
		return entTaxCircle;
	}

	public void setEntTaxCircle(String entTaxCircle) {
		this.entTaxCircle = entTaxCircle;
	}

	public String getSrvcTaxCircle() {
		return srvcTaxCircle;
	}

	public void setSrvcTaxCircle(String srvcTaxCircle) {
		this.srvcTaxCircle = srvcTaxCircle;
	}

	public String getSwachcessCircle() {
		return swachcessCircle;
	}

	public void setSwachcessCircle(String swachcessCircle) {
		this.swachcessCircle = swachcessCircle;
	}

	public String getKisancessCircle() {
		return kisancessCircle;
	}

	public void setKisancessCircle(String kisancessCircle) {
		this.kisancessCircle = kisancessCircle;
	}

	public Integer getMandalSlno() {
		return mandalSlno;
	}

	public void setMandalSlno(Integer mandalSlno) {
		this.mandalSlno = mandalSlno;
	}
	
}
