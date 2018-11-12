package com.arbiva.apsfl.scs.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="srvcfeatures")
public class SrvcFeatures {
	
	
	private String coresrvccode;
	
	private String featureid;
	
	private String featurecode;
	
	private String featurename;
	
	private String genre;

	private String broadcaster;
	
	private String lang;
	
	private String selectedVal;
	
	
	


	public String getSelectedVal() {
		return selectedVal;
	}


	public void setSelectedVal(String selectedVal) {
		this.selectedVal = selectedVal;
	}


	public String getCoresrvccode() {
		return coresrvccode;
	}


	public void setCoresrvccode(String coresrvccode) {
		this.coresrvccode = coresrvccode;
	}


	public String getFeatureid() {
		return featureid;
	}


	public void setFeatureid(String featureid) {
		this.featureid = featureid;
	}


	public String getFeaturecode() {
		return featurecode;
	}


	public void setFeaturecode(String featurecode) {
		this.featurecode = featurecode;
	}


	public String getFeaturename() {
		return featurename;
	}


	public void setFeaturename(String featurename) {
		this.featurename = featurename;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getBroadcaster() {
		return broadcaster;
	}


	public void setBroadcaster(String broadcaster) {
		this.broadcaster = broadcaster;
	}


	public String getLang() {
		return lang;
	}


	public void setLang(String lang) {
		this.lang = lang;
	}


	

}
