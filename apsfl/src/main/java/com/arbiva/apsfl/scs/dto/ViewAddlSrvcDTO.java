package com.arbiva.apsfl.scs.dto;

import java.io.Serializable;
import java.util.List;

public class ViewAddlSrvcDTO implements Serializable{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5211434252423094060L;
	private String code;
	private String name;
	private String coreServCode;
	private String apsflPerc;
	private String mspPerc;
	private String lmoPerc;
	private String effectiveFrom;
	private String effectiveTo;
	private List<AdditionalServiceParamDTO> additionalServiceParamDTOList;
	private List<TaxParamDTO> taxParamDTOList;
	private String status;
	private List<SrvcFeatures> srvcFeatList; 
	
	
	
	
	
	public List<SrvcFeatures> getSrvcFeatList() {
		return srvcFeatList;
	}
	public void setSrvcFeatList(List<SrvcFeatures> srvcFeatList) {
		this.srvcFeatList = srvcFeatList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getEffectiveTo() {
		return effectiveTo;
	}
	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
	public List<AdditionalServiceParamDTO> getAdditionalServiceParamDTOList() {
		return additionalServiceParamDTOList;
	}
	public void setAdditionalServiceParamDTOList(List<AdditionalServiceParamDTO> additionalServiceParamDTOList) {
		this.additionalServiceParamDTOList = additionalServiceParamDTOList;
	}
	public List<TaxParamDTO> getTaxParamDTOList() {
		return taxParamDTOList;
	}
	public void setTaxParamDTOList(List<TaxParamDTO> taxParamDTOList) {
		this.taxParamDTOList = taxParamDTOList;
	}
	

	
	
	
	
	
	

}
