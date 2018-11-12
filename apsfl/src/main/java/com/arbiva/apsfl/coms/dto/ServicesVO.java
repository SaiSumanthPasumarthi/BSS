/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class ServicesVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String srvcName;
	
	private String coreSrvcCode;

	public String getSrvcName() {
		return srvcName;
	}

	public void setSrvcName(String srvcName) {
		this.srvcName = srvcName;
	}

	public String getCoreSrvcCode() {
		return coreSrvcCode;
	}

	public void setCoreSrvcCode(String coreSrvcCode) {
		this.coreSrvcCode = coreSrvcCode;
	}

}
