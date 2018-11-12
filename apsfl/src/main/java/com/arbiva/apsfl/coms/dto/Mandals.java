/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class Mandals implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer mandalUid;
	
	private String mandalName;
	
	private Integer districtUid;
	
	private Integer mandalSlno;
	
	private Integer stateId;
	
	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}


	public Integer getMandalUid() {
		return mandalUid;
	}

	public void setMandalUid(Integer mandalUid) {
		this.mandalUid = mandalUid;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public Integer getDistrictUid() {
		return districtUid;
	}

	public void setDistrictUid(Integer districtUid) {
		this.districtUid = districtUid;
	}

	public Integer getMandalSlno() {
		return mandalSlno;
	}

	public void setMandalSlno(Integer mandalSlno) {
		this.mandalSlno = mandalSlno;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
