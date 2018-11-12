package com.arbiva.apsfl.pcs.dto;

import java.util.List;

public class CreateProductDTO {
	
	private List<SrvcsDTO> serviceslist;
	private List<String> chargeTypeLov;
	private List<LovsDTO> custTypeList;
	private List<LovsDTO> prodTypeList;
	private List<GlCodeDTO> glCodes;
	private List<TaxCodeDTO> taxCodesList;
	private List<ChargeCodesDTO> chargeTypeList;
	private List<SrvcFeaturesDTO> srvcFeaturesList ;
	
	
	
	
	public List<SrvcFeaturesDTO> getSrvcFeaturesList() {
		return srvcFeaturesList;
	}
	public void setSrvcFeaturesList(List<SrvcFeaturesDTO> srvcFeaturesList) {
		this.srvcFeaturesList = srvcFeaturesList;
	}
	public List<SrvcsDTO> getServiceslist() {
		return serviceslist;
	}
	public void setServiceslist(List<SrvcsDTO> serviceslist) {
		this.serviceslist = serviceslist;
	}
	public List<String> getChargeTypeLov() {
		return chargeTypeLov;
	}
	public void setChargeTypeLov(List<String> chargeTypeLov) {
		this.chargeTypeLov = chargeTypeLov;
	}
	public List<LovsDTO> getCustTypeList() {
		return custTypeList;
	}
	public void setCustTypeList(List<LovsDTO> custTypeList) {
		this.custTypeList = custTypeList;
	}
	public List<LovsDTO> getProdTypeList() {
		return prodTypeList;
	}
	public void setProdTypeList(List<LovsDTO> prodTypeList) {
		this.prodTypeList = prodTypeList;
	}
	public List<GlCodeDTO> getGlCodes() {
		return glCodes;
	}
	public void setGlCodes(List<GlCodeDTO> glCodes) {
		this.glCodes = glCodes;
	}
	public List<TaxCodeDTO> getTaxCodesList() {
		return taxCodesList;
	}
	public void setTaxCodesList(List<TaxCodeDTO> taxCodesList) {
		this.taxCodesList = taxCodesList;
	}
	public List<ChargeCodesDTO> getChargeTypeList() {
		return chargeTypeList;
	}
	public void setChargeTypeList(List<ChargeCodesDTO> chargeTypeList) {
		this.chargeTypeList = chargeTypeList;
	}
	

}
