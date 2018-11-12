/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class CpeModal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long profileId;
	
	private String cpeModel;
	
	private String cpetypeLov;
	
	private String cpeProfileName;
	
	private String cpeModelDetails;

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
	
	public String getCpetypeLov() {
		return cpetypeLov;
	}

	public void setCpetypeLov(String cpetypeLov) {
		this.cpetypeLov = cpetypeLov;
	}

	public String getCpeProfileName() {
		return cpeProfileName;
	}

	public void setCpeProfileName(String cpeProfileName) {
		this.cpeProfileName = cpeProfileName;
	}

	public String getCpeModelDetails() {
		return cpeModelDetails;
	}

	public void setCpeModelDetails(String cpeModelDetails) {
		this.cpeModelDetails = cpeModelDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
