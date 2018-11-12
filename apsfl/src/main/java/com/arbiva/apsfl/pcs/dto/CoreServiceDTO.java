package com.arbiva.apsfl.pcs.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "coresrvcs")
public class CoreServiceDTO {

	

	private String servCode;
	
	private Date effectivefrom;
	
	private Date effectiveto;

	private String servName;

	private String servProvCode;

	private String ispName;

	private String provTrgtType;

	private String provTrgtValue;
	
	private String glcde;
	
	private String multiple;
	
	private String oneTime;


	public String getServCode() {
		return servCode;
	}

	public void setServCode(String servCode) {
		this.servCode = servCode;
	}

	public String getServName() {
		return servName;
	}

	public void setServName(String servName) {
		this.servName = servName;
	}

	public String getServProvCode() {
		return servProvCode;
	}

	public void setServProvCode(String servProvCode) {
		this.servProvCode = servProvCode;
	}

	public String getIspName() {
		return ispName;
	}

	public void setIspName(String ispName) {
		this.ispName = ispName;
	}

	public String getProvTrgtType() {
		return provTrgtType;
	}

	public void setProvTrgtType(String provTrgtType) {
		this.provTrgtType = provTrgtType;
	}

	public String getProvTrgtValue() {
		return provTrgtValue;
	}

	public void setProvTrgtValue(String provTrgtValue) {
		this.provTrgtValue = provTrgtValue;
	}

	/*public GlCode getGlCode() {
		return glCode;
	}*/

	/*public void setGlCode(GlCode glCode) {
		this.glCode = glCode;
	}*/

	public String getGlcde() {
		return glcde;
	}

	public void setGlcde(String glcde) {
		this.glcde = glcde;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	public Date getEffectivefrom() {
		return effectivefrom;
	}

	public void setEffectivefrom(Date effectivefrom) {
		this.effectivefrom = effectivefrom;
	}

	public Date getEffectiveto() {
		return effectiveto;
	}

	public void setEffectiveto(Date effectiveto) {
		this.effectiveto = effectiveto;
	}

	public String getOneTime() {
		return oneTime;
	}

	public void setOneTime(String oneTime) {
		this.oneTime = oneTime;
	}
	
	

}
