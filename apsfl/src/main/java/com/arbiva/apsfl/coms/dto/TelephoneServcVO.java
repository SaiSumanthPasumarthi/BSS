/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

public class TelephoneServcVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String featurecode;
	
	private String prmcode;
	
	private String prmvalue;

	public String getFeaturecode() {
		return featurecode;
	}

	public void setFeaturecode(String featurecode) {
		this.featurecode = featurecode;
	}

	public String getPrmcode() {
		return prmcode;
	}

	public void setPrmcode(String prmcode) {
		this.prmcode = prmcode;
	}

	public String getPrmvalue() {
		return prmvalue;
	}

	public void setPrmvalue(String prmvalue) {
		this.prmvalue = prmvalue;
	}
	
	
}
