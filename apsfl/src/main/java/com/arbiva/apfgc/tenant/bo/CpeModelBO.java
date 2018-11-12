package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CpeModelBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="cpe_model")
	private String cpeModel;
	
	@Column(name="profile_id")
	private String cpeProfileId;
	
	@Column(name="cpetypelov")
	private String cpeTypeLov;

	public String getCpeModel() {
		return cpeModel;
	}

	public void setCpeModel(String cpeModel) {
		this.cpeModel = cpeModel;
	}

	public String getCpeProfileId() {
		return cpeProfileId;
	}

	public void setCpeProfileId(String cpeProfileId) {
		this.cpeProfileId = cpeProfileId;
	}

	public String getCpeTypeLov() {
		return cpeTypeLov;
	}

	public void setCpeTypeLov(String cpeTypeLov) {
		this.cpeTypeLov = cpeTypeLov;
	}
	
	

}
