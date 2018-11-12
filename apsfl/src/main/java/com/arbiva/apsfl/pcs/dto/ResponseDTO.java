/**
 * 
 */
package com.arbiva.apsfl.pcs.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Arbiva
 *
 */

@JsonAutoDetect(getterVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, isGetterVisibility = Visibility.NONE)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseDTO {
	
	
	@JsonProperty("errorCode")
	private int errorCode;
	
	@JsonProperty("errorMessage")
	private String errorMessage;
	
	@JsonProperty("desc")
	private String desc;
	
	@JsonProperty("productCode")
	private String productCode;
	
	@JsonProperty("effDate")
	private String effDate;
	
	@JsonProperty("outPut")
	private String outPut;
	
	@JsonProperty("tenantcode")
	private String tenantcode;
	
	
	public String getTenantcode() {
		return tenantcode;
	}
	public void setTenantcode(String tenantcode) {
		this.tenantcode = tenantcode;
	}
	public String getOutPut() {
		return outPut;
	}
	public void setOutPut(String outPut) {
		this.outPut = outPut;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String string) {
		this.productCode = string;
	}
	public String getEffDate() {
		return effDate;
	}
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	@Override
	public String toString() {
		return "ResponceVo [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", desc=" + desc
				+ ", productCode=" + productCode + ", effDate=" + effDate + ", outPut=" + outPut + ", tenantcode="
				+ tenantcode + "]";
	}
	
}
