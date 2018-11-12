package com.arbiva.apsfl.pcs.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author srinivasa
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GlCodeDTO{
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private String glCode;

	private String glName;
	
	public String getGlCode() {
		return glCode;
	}

	public void setGlCode(String glCode) {
		this.glCode = glCode;
	}

	public String getGlName() {
		return glName;
	}

	public void setGlName(String glName) {
		this.glName = glName;
	}
}
