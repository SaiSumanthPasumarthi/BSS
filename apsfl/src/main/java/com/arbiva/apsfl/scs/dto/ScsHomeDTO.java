package com.arbiva.apsfl.scs.dto;

import java.util.List;
import java.util.Map;

public class ScsHomeDTO {

	Map<String, String> provTargetTypeLov;
	Map<String, String> glCodeLov;
	Map<String, String> taxCodeLov;
	Map<String, String> dataTypeLov;
	Map<String, String> lovVals;
	List<TaxParamDTO> taxParams;
	CoreServiceDTO coreServiceDTO;
	ViewAddlSrvcDTO addlServiceDTO;
	List<CoreService> coreSrvcsList;
	String coreSrvcCode;
	List<AdditionalServiceParamDTO> addlSrvcParamDTOList;
	List<TaxParamDTO> srvcTaxes;
	List<SrvcFeatures> addlServcList;
	List<SrvcFeatures> srvcFeaturesList;
	
	

	public List<SrvcFeatures> getSrvcFeaturesList() {
		return srvcFeaturesList;
	}

	public void setSrvcFeaturesList(List<SrvcFeatures> srvcFeaturesList) {
		this.srvcFeaturesList = srvcFeaturesList;
	}

	public List<SrvcFeatures> getAddlServcList() {
		return addlServcList;
	}

	public void setAddlServcList(List<SrvcFeatures> addlServcList) {
		this.addlServcList = addlServcList;
	}

	public List<TaxParamDTO> getSrvcTaxes() {
		return srvcTaxes;
	}

	public void setSrvcTaxes(List<TaxParamDTO> srvcTaxes) {
		this.srvcTaxes = srvcTaxes;
	}

	public ViewAddlSrvcDTO getAddlServiceDTO() {
		return addlServiceDTO;
	}

	public void setAddlServiceDTO(ViewAddlSrvcDTO addlServiceDTO) {
		this.addlServiceDTO = addlServiceDTO;
	}

	public List<CoreService> getCoreSrvcsList() {
		return coreSrvcsList;
	}

	public void setCoreSrvcsList(List<CoreService> coreSrvcsList) {
		this.coreSrvcsList = coreSrvcsList;
	}

	public String getCoreSrvcCode() {
		return coreSrvcCode;
	}

	public void setCoreSrvcCode(String coreSrvcCode) {
		this.coreSrvcCode = coreSrvcCode;
	}

	public List<AdditionalServiceParamDTO> getAddlSrvcParamDTOList() {
		return addlSrvcParamDTOList;
	}

	public void setAddlSrvcParamDTOList(List<AdditionalServiceParamDTO> addlSrvcParamDTOList) {
		this.addlSrvcParamDTOList = addlSrvcParamDTOList;
	}

	public CoreServiceDTO getCoreServiceDTO() {
		return coreServiceDTO;
	}

	public void setCoreServiceDTO(CoreServiceDTO coreServiceDTO) {
		this.coreServiceDTO = coreServiceDTO;
	}

	public List<TaxParamDTO> getTaxParams() {
		return taxParams;
	}

	public void setTaxParams(List<TaxParamDTO> taxParams) {
		this.taxParams = taxParams;
	}

	public Map<String, String> getProvTargetTypeLov() {
		return provTargetTypeLov;
	}

	public void setProvTargetTypeLov(Map<String, String> provTargetTypeLov) {
		this.provTargetTypeLov = provTargetTypeLov;
	}

	public Map<String, String> getGlCodeLov() {
		return glCodeLov;
	}

	public void setGlCodeLov(Map<String, String> glCodeLov) {
		this.glCodeLov = glCodeLov;
	}

	public Map<String, String> getTaxCodeLov() {
		return taxCodeLov;
	}

	public void setTaxCodeLov(Map<String, String> taxCodeLov) {
		this.taxCodeLov = taxCodeLov;
	}

	public Map<String, String> getDataTypeLov() {
		return dataTypeLov;
	}

	public void setDataTypeLov(Map<String, String> dataTypeLov) {
		this.dataTypeLov = dataTypeLov;
	}

	public Map<String, String> getLovVals() {
		return lovVals;
	}

	public void setLovVals(Map<String, String> lovVals) {
		this.lovVals = lovVals;
	}

}
