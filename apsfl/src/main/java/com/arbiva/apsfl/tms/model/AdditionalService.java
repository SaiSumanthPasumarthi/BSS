package com.arbiva.apsfl.tms.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author srinivasa
 *
 */

public class AdditionalService implements Serializable{


	private static final long serialVersionUID = 1L;

	
	private String servCode;

	private String effectivefrom;
	
	private String effectiveto;

	private String servName;

	private String coreServCode;
	
	private String featurecodes;
	
	private String addChnls;
	
	private String delChnls;
	
	private String mspCode;
	
	private int ftaFlag;
	

	
	public String getMspCode() {
		return mspCode;
	}

	public void setMspCode(String mspCode) {
		this.mspCode = mspCode;
	}

	public String getAddChnls() {
		return addChnls;
	}

	public void setAddChnls(String addChnls) {
		this.addChnls = addChnls;
	}

	public String getDelChnls() {
		return delChnls;
	}

	public void setDelChnls(String delChnls) {
		this.delChnls = delChnls;
	}

	public int getFtaFlag() {
		return ftaFlag;
	}

	public void setFtaFlag(int ftaFlag) {
		this.ftaFlag = ftaFlag;
	}

	public String getFeaturecodes() {
		return featurecodes;
	}

	public void setFeaturecodes(String featurecodes) {
		this.featurecodes = featurecodes;
	}

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

	public String getCoreServCode() {
		return coreServCode;
	}

	public void setCoreServCode(String coreServCode) {
		this.coreServCode = coreServCode;
	}

	public String getEffectivefrom() {
		return effectivefrom;
	}

	public void setEffectivefrom(String effectivefrom) {
		this.effectivefrom = effectivefrom;
	}

	public String getEffectiveto() {
		return effectiveto;
	}

	public void setEffectiveto(String effectiveto) {
		this.effectiveto = effectiveto;
	}

}
