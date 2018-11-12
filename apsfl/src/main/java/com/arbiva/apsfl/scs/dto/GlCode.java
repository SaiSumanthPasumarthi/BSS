package com.arbiva.apsfl.scs.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author srinivasa
 *
 */
@Entity
@Table(name = "GLCODES")
public class GlCode extends Base {
	
	public GlCode() {
	}
	
	public GlCode(String glCode, String glName) {
		this.glCode = glCode;
		this.glName = glName;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "GLCODE")
	private String glCode;

	@Column(name = "GLNAME")
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
