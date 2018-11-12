package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class TenantBusinessAreas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String stateId;

	private String districtUid;

	private String mandalSlno;

	private String villageSlno;

	private String substnUid;

	private String subscriberCount;

	private String villageName;

	private String mandalName;

	private String districtName;
	
	private String region;
	
	private String stdCode;
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getStdCode() {
		return stdCode;
	}

	public void setStdCode(String stdCode) {
		this.stdCode = stdCode;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getDistrictUid() {
		return districtUid;
	}

	public void setDistrictUid(String districtUid) {
		this.districtUid = districtUid;
	}

	public String getMandalSlno() {
		return mandalSlno;
	}

	public void setMandalSlno(String mandalSlno) {
		this.mandalSlno = mandalSlno;
	}

	public String getVillageSlno() {
		return villageSlno;
	}

	public void setVillageSlno(String villageSlno) {
		this.villageSlno = villageSlno;
	}

	public String getSubstnUid() {
		return substnUid;
	}

	public void setSubstnUid(String substnUid) {
		this.substnUid = substnUid;
	}

	public String getSubscriberCount() {
		return subscriberCount;
	}

	public void setSubscriberCount(String subscriberCount) {
		this.subscriberCount = subscriberCount;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

}