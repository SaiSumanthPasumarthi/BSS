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
public class CoreServiceDTO {
	

	private String code;

	private String name;

	private String provCode;

	private String glCode;

	private String glDescription;

	private ProvParamDTO provParams;

	private List<ServiceParamDTO> servParams;

	private List<TaxParamDTO> taxParams;
	
	private boolean multiple;
	
	private boolean oneTime;
	
	private String loginId;
	
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public boolean isOneTime() {
		return oneTime;
	}

	public void setOneTime(boolean oneTime) {
		this.oneTime = oneTime;
	}

	public boolean isMultiple(){
		return multiple;
	}

	public void setMultiple(boolean multiple){
		this.multiple = multiple;
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

	public String getProvCode() {
		return provCode;
	}

	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}

	public String getGlCode() {
		return glCode;
	}

	public void setGlCode(String glCode) {
		this.glCode = glCode;
	}

	public String getGlDescription() {
		return glDescription;
	}

	public void setGlDescription(String glDescription) {
		this.glDescription = glDescription;
	}

	public ProvParamDTO getProvParams() {
		return provParams;
	}

	public void setProvParams(ProvParamDTO provParams) {
		this.provParams = provParams;
	}

	public List<ServiceParamDTO> getServParams() {
		return servParams;
	}

	public void setServParams(List<ServiceParamDTO> servParams) {
		this.servParams = servParams;
	}

	public List<TaxParamDTO> getTaxParams() {
		return taxParams;
	}

	public void setTaxParams(List<TaxParamDTO> taxParams) {
		this.taxParams = taxParams;
	}

}
