package com.arbiva.apsfl.scs.dto;

import java.util.List;

public class ScsHelperDTO {
	
	List<AdditionalServiceParamDTO> addSrvcDTOList;
	List<SrvcFeatures> srvcFeatList;
	private String ftaFlage;
	private String coreSrvc;
	private String srvcCode;
	private String effeFrom;
	List<String> srvcFeaturesCodes;
	private String chanelsEffectiveFrom;
	
	
	
	public String getChanelsEffectiveFrom() {
		return chanelsEffectiveFrom;
	}
	public void setChanelsEffectiveFrom(String chanelsEffectiveFrom) {
		this.chanelsEffectiveFrom = chanelsEffectiveFrom;
	}
	public List<String> getSrvcFeaturesCodes() {
		return srvcFeaturesCodes;
	}
	public void setSrvcFeaturesCodes(List<String> srvcFeaturesCodes) {
		this.srvcFeaturesCodes = srvcFeaturesCodes;
	}
	public String getSrvcCode() {
		return srvcCode;
	}
	public void setSrvcCode(String srvcCode) {
		this.srvcCode = srvcCode;
	}
	public String getEffeFrom() {
		return effeFrom;
	}
	public void setEffeFrom(String effeFrom) {
		this.effeFrom = effeFrom;
	}
	public String getFtaFlage() {
		return ftaFlage;
	}
	public void setFtaFlage(String ftaFlage) {
		this.ftaFlage = ftaFlage;
	}
	public String getCoreSrvc() {
		return coreSrvc;
	}
	public void setCoreSrvc(String coreSrvc) {
		this.coreSrvc = coreSrvc;
	}
	public List<AdditionalServiceParamDTO> getAddSrvcDTOList() {
		return addSrvcDTOList;
	}
	public void setAddSrvcDTOList(List<AdditionalServiceParamDTO> addSrvcDTOList) {
		this.addSrvcDTOList = addSrvcDTOList;
	}
	public List<SrvcFeatures> getSrvcFeatList() {
		return srvcFeatList;
	}
	public void setSrvcFeatList(List<SrvcFeatures> srvcFeatList) {
		this.srvcFeatList = srvcFeatList;
	}
	
	

}
