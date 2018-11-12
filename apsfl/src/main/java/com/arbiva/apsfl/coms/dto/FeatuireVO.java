/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Arbiva
 *
 */
public class FeatuireVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String featureCode;
	
	private List<ParamsVO1> paramsList;
	
	public List<ParamsVO1> getParamsList() {
		return paramsList;
	}

	public void setParamsList(List<ParamsVO1> paramsList) {
		this.paramsList = paramsList;
	}

	public String getFeatureCode() {
		return featureCode;
	}

	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}

}
