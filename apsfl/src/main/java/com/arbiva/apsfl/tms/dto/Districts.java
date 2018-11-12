/**
 * 
 */
package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;

/**
 * @author Priya
 *
 */
public class Districts implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer districtUid;
	
	private String districtName;
	
	private Integer stateId;
	

	public Integer getDistrictUid() {
		return districtUid;
	}

	public void setDistrictUid(Integer districtUid) {
		this.districtUid = districtUid;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	
}
