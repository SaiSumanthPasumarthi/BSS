/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class Substations implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String substnUid;
	
	private String substnName;
	
	private String substnType;
	
	private Integer districtUid;
	
	private Integer mandalSlno;
	
	public String getSubstnUid() {
		return substnUid;
	}

	public void setSubstnUid(String substnUid) {
		this.substnUid = substnUid;
	}

	public String getSubstnName() {
		return substnName;
	}

	public void setSubstnName(String substnName) {
		this.substnName = substnName;
	}

	public String getSubstnType() {
		return substnType;
	}

	public void setSubstnType(String substnType) {
		this.substnType = substnType;
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
