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
public class ParamsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ParamsVO1> paramsList1;

	public List<ParamsVO1> getParamsList1() {
		return paramsList1;
	}

	public void setParamsList1(List<ParamsVO1> paramsList1) {
		this.paramsList1 = paramsList1;
	}
	
}
