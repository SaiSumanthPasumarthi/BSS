package com.arbiva.apsfl.scs.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author srinivasa
 *
 */
@JsonAutoDetect(getterVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, isGetterVisibility = Visibility.NONE)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AdditionalServicesDTO {

	private String code;

	private String name;

	private String coreServCode;

	private String effectiveTo;

	private String apsflPerc;

	private String mspPerc;

	private String lmoPerc;

	private String effectiveFrom;

	private String loginId;

	private List<AdditionalServiceParametersDTO> servParams;
	
	private String[] srvcFeaturesList;
	
	private int ftaFlage;
	
	


	public String[] getSrvcFeaturesList() {
		return srvcFeaturesList;
	}

	public void setSrvcFeaturesList(String[] srvcFeaturesList) {
		this.srvcFeaturesList = srvcFeaturesList;
	}

	public int getFtaFlage() {
		return ftaFlage;
	}

	public void setFtaFlage(int ftaFlage) {
		this.ftaFlage = ftaFlage;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoreServCode() {
		return coreServCode;
	}

	public void setCoreServCode(String coreServCode) {
		this.coreServCode = coreServCode;
	}

	public List<AdditionalServiceParametersDTO> getServParams() {
		return servParams;
	}

	public void setServParams(List<AdditionalServiceParametersDTO> servParams) {
		this.servParams = servParams;
	}

	public String getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public String getApsflPerc() {
		return apsflPerc;
	}

	public void setApsflPerc(String apsflPerc) {
		this.apsflPerc = apsflPerc;
	}

	public String getMspPerc() {
		return mspPerc;
	}

	public void setMspPerc(String mspPerc) {
		this.mspPerc = mspPerc;
	}

	public String getLmoPerc() {
		return lmoPerc;
	}

	public void setLmoPerc(String lmoPerc) {
		this.lmoPerc = lmoPerc;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

}
